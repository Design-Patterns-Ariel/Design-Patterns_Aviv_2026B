package Week09.client;

import Week09.common.Message;
import Week09.common.MessageParser;
import Week09.common.MessagePrototypeRegistry;

import java.util.Scanner;

/**
 * Facade for the whole client side. Hides the multi-step flow (connect, register,
 * start a listener thread, read keyboard input, send messages, close) behind a
 * single {@link #start()} call so that {@code ClientMain} stays tiny.
 *
 * It depends on the {@link ClientConnection} abstraction and never touches a socket directly.
 */
public class ChatClientFacade {
    private final ClientConfig config;
    private final ClientConnection connection;
    private final ClientMessageFactory messageFactory;
    private final Scanner scanner = new Scanner(System.in);

    private ServerListener listener;
    private Thread listenerThread;

    public ChatClientFacade(ClientConfig config, MessageParser parser, MessagePrototypeRegistry prototypes) {
        this.config = config;
        this.connection = new SocketClientConnection(config, parser);
        this.messageFactory = new ClientMessageFactory(prototypes);
    }

    public void start() {
        try {
            connection.connect();
            System.out.println("Connected to " + config.getHost() + ":" + config.getPort());

            String identifier = registerUser();
            if (identifier == null) {
                System.out.println("Registration failed. Closing client.");
                return;
            }

            startServerListener();
            readAndSendMessages(identifier);
        } catch (RuntimeException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            close();
        }
    }

    /**
     * Drives the registration handshake. The flow is steered by the server's
     * REGISTER_ACK replies (carried in the "status" metadata field):
     * OK -> done, RETRY -> ask the user for another id, ASK_SUBSCRIBE -> ask the
     * user yes/no for the update service, FAILED -> give up.
     */
    private String registerUser() {
        int attempts = 0;
        String identifier = promptIdentifier();
        connection.send(messageFactory.createRegisterMessage(identifier));

        while (true) {
            Message response = connection.read();
            if (response == null) {
                System.out.println("Disconnected during registration.");
                return null;
            }
            String status = response.getHeader().getMetadata().getOrDefault("status", "");
            String text = response.getBody();

            switch (status) {
                case "OK":
                    System.out.println(text);
                    return identifier;
                case "ASK_SUBSCRIBE":
                    System.out.println(text);
                    System.out.print("> ");
                    String answer = scanner.nextLine().trim();
                    connection.send(messageFactory.createRegisterAnswer(identifier, answer));
                    break;
                case "RETRY":
                    System.out.println(text);
                    attempts++;
                    if (attempts >= config.getMaxRegistrationAttempts()) {
                        System.out.println("Too many registration attempts. Giving up.");
                        return null;
                    }
                    identifier = promptIdentifier();
                    connection.send(messageFactory.createRegisterMessage(identifier));
                    break;
                case "FAILED":
                default:
                    System.out.println(text);
                    return null;
            }
        }
    }

    private String promptIdentifier() {
        System.out.print("Enter your phone number: ");
        return scanner.nextLine().trim();
    }

    private void startServerListener() {
        listener = new ServerListener(connection);
        listenerThread = new Thread(listener, "server-listener");
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    private void readAndSendMessages(String identifier) {
        System.out.println("You can now send messages. Target 'ALL' broadcasts to update-subscribers; type 'exit' to quit.");
        System.out.print("> ");
        while (connection.isConnected()) {
            String to = readLine();
            if (to == null) {
                break;
            }
            to = to.trim();
            if (to.isEmpty()) {
                System.out.print("> ");
                continue;
            }
            if (to.equalsIgnoreCase("exit")) {
                connection.send(messageFactory.createDisconnectMessage(identifier));
                break;
            }

            System.out.print("Enter title: ");
            String title = readLine();
            System.out.print("Enter message: ");
            String body = readLine();
            if (title == null || body == null) {
                break;
            }

            Message message = to.equalsIgnoreCase("ALL")
                    ? messageFactory.createBroadcastMessage(identifier, title, body)
                    : messageFactory.createPrivateMessage(identifier, to, title, body);
            connection.send(message);
            System.out.print("Enter target phone (or ALL / exit): ");
        }
    }

    private String readLine() {
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    private void close() {
        if (listener != null) {
            listener.stop();
        }
        connection.close();
        System.out.println("Client closed.");
    }
}
