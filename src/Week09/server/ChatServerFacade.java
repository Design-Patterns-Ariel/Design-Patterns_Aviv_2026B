package Week09.server;

import Week09.common.MessageParser;
import Week09.common.MessagePrototypeRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Facade for the whole server side. Wires together the registry, subject and router,
 * opens the {@link ServerSocket}, accepts clients and spawns a {@link ClientSession}
 * thread per client. {@code ServerMain} therefore stays tiny.
 *
 * This class manages server start-up/acceptance only; it never talks to an individual
 * client – that is {@link ClientSession}'s job.
 */
public class ChatServerFacade {
    private final ServerConfig config;
    private final MessageParser parser;
    private final MessagePrototypeRegistry prototypes;
    private final ClientRegistry registry;
    private final ServerMessageSubject subject;
    private final MessageRouter router;
    private volatile boolean running = true;
    private ServerSocket serverSocket;

    public ChatServerFacade(ServerConfig config, MessageParser parser, MessagePrototypeRegistry prototypes) {
        this.config = config;
        this.parser = parser;
        this.prototypes = prototypes;
        this.registry = new InMemoryClientRegistry();
        this.subject = new ServerMessageSubject(registry);
        this.router = new MessageRouter(subject, prototypes);
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(config.getPort());
            System.out.println("Server started on port " + config.getPort());
            acceptClients();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException ignored) {
            // shutting down
        }
    }

    private void acceptClients() {
        RegistrationService registrationService =
                new RegistrationService(registry, config.getMaxRegistrationAttempts());

        while (running) {
            try {
                Socket socket = serverSocket.accept();

                if (registry.findAll().size() >= config.getMaxClients()) {
                    System.out.println("[Server] Max clients reached, rejecting connection.");
                    socket.close();
                    continue;
                }

                ClientSession session = new ClientSession(socket, parser, registrationService, subject, router);
                Thread thread = new Thread(session, "client-session");
                thread.start();
            } catch (IOException e) {
                if (running) {
                    System.out.println("Accept error: " + e.getMessage());
                }
                break;
            }
        }
    }
}
