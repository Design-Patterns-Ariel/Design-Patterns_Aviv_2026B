package Week09.server;

import Week09.common.Message;
import Week09.common.MessageParser;
import Week09.common.MessageType;
import Week09.observer.MessageObserver;
import Week09.observer.MessageSubject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Represents a single connected client on the server side. Each session runs on
 * its own thread (Runnable) and is also a {@link MessageObserver}: when the server
 * needs to deliver a message to this client, it calls {@link #update(Message)}.
 *
 * Responsibilities are deliberately narrow: manage one socket, register the user,
 * read messages and hand them to the {@link MessageRouter}. It does NOT decide
 * routing rules and does NOT store the global client list.
 */
public class ClientSession implements Runnable, MessageObserver {
    private final Socket socket;
    private final MessageParser parser;
    private final RegistrationService registrationService;
    private final MessageSubject subject;
    private final MessageRouter router;
    private BufferedReader reader;
    private PrintWriter writer;
    private String identifier;
    private boolean subscribedToUpdates = false;
    private volatile boolean running = true;

    public ClientSession(
            Socket socket,
            MessageParser parser,
            RegistrationService registrationService,
            MessageSubject subject,
            MessageRouter router
    ) {
        this.socket = socket;
        this.parser = parser;
        this.registrationService = registrationService;
        this.subject = subject;
        this.router = router;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            if (!registerClient()) {
                close();
                return;
            }

            // The session is an Observer: once registered, the server can notify it.
            subject.registerObserver(this);
            System.out.println("[Server] Registered: " + identifier
                    + (subscribedToUpdates ? " (update service: ON)" : " (update service: OFF)"));

            while (running) {
                Message message = readMessage();
                if (message == null) {
                    break;
                }
                if (message.getHeader().getType() == MessageType.DISCONNECT) {
                    break;
                }
                router.route(message);
            }
        } catch (IOException e) {
            // I/O failure on this client – just clean up below.
        } finally {
            if (identifier != null) {
                subject.unregisterObserver(identifier);
                System.out.println("[Server] Disconnected: " + identifier);
            }
            close();
        }
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean wantsUpdates() {
        return subscribedToUpdates;
    }

    @Override
    public void update(Message message) {
        // Called by the subject (possibly from another client's thread) -> send to socket.
        sendMessage(message);
    }

    /**
     * Registration handshake. For each attempt the client sends a REGISTER message
     * with its desired identifier. If the id is free we ask about the update service
     * and finish; otherwise we tell the client to retry. After the configured number
     * of failed attempts the connection is closed.
     */
    private boolean registerClient() {
        int maxAttempts = registrationService.getMaxAttempts();
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            Message request = readMessage();
            if (request == null) {
                return false; // client disconnected
            }
            String requestedId = request.getHeader().getFrom();
            if (registrationService.canRegister(requestedId)) {
                this.subscribedToUpdates = askForSubscription();
                this.identifier = requestedId;
                sendControl(MessageType.REGISTER_ACK, "OK", "Registration succeeded");
                return true;
            }
            sendControl(MessageType.REGISTER_ACK, "RETRY", "Identifier already exists. Try again.");
        }
        sendControl(MessageType.REGISTER_ACK, "FAILED",
                "Registration failed after " + maxAttempts + " attempts. Closing connection.");
        return false;
    }

    /**
     * Asks the client whether to join the update service. The client must answer
     * yes/no. On an unexpected answer the question is asked again, up to 3 times;
     * after 3 unexpected answers the client is registered WITHOUT the update service.
     */
    private boolean askForSubscription() {
        for (int i = 0; i < 3; i++) {
            sendControl(MessageType.REGISTER_ACK, "ASK_SUBSCRIBE",
                    "Do you want to join the update service (errors, news, software updates)? [yes/no]");
            Message answer = readMessage();
            if (answer == null) {
                return false; // disconnected mid-handshake
            }
            String text = answer.getBody() == null ? "" : answer.getBody().trim().toLowerCase();
            if (text.equals("yes") || text.equals("y")) {
                return true;
            }
            if (text.equals("no") || text.equals("n")) {
                return false;
            }
            // unexpected answer -> loop and ask again
        }
        return false; // 3 unexpected answers -> register without the update service
    }

    /** Sends a small control message to this client, tagging it with a status. */
    private void sendControl(MessageType type, String status, String text) {
        Message message = new Message();
        message.getHeader().setType(type);
        message.getHeader().setFrom("SERVER");
        message.getHeader().getMetadata().put("status", status);
        message.setBody(text);
        sendMessage(message);
    }

    private Message readMessage() {
        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(MessageParser.END_OF_MESSAGE)) {
                    return parser.parse(sb.toString());
                }
                if (sb.length() > 0) {
                    sb.append('\n');
                }
                sb.append(line);
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private void sendMessage(Message message) {
        if (writer == null) {
            return;
        }
        // update() can be invoked by several threads; serialize writes to this socket.
        synchronized (this) {
            writer.print(parser.serialize(message));
            writer.print('\n');
            writer.println(MessageParser.END_OF_MESSAGE);
            writer.flush();
        }
    }

    private void close() {
        running = false;
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ignored) {
            // closing
        }
        if (writer != null) {
            writer.close();
        }
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ignored) {
            // closing
        }
    }
}
