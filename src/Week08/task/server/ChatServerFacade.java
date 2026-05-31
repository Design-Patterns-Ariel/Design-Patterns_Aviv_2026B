package Week08.task.server;

import Week08.task.common.MessageParser;
import Week08.task.common.MessagePrototypeRegistry;

import java.net.ServerSocket;

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
        // TODO
    }

    public void stop() {
        // TODO
    }

    private void acceptClients() {
        // TODO
    }
}
