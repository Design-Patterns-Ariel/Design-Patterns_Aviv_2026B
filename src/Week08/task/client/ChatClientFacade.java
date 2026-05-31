package Week08.task.client;

import Week08.task.common.MessageParser;
import Week08.task.common.MessagePrototypeRegistry;

import java.util.Scanner;

public class ChatClientFacade {
    private final ClientConfig config;
    private final ClientConnection connection;
    private final ClientMessageFactory messageFactory;
    private final Scanner scanner = new Scanner(System.in);

    public ChatClientFacade(ClientConfig config, MessageParser parser, MessagePrototypeRegistry prototypes) {
        this.config = config;
        this.connection = new SocketClientConnection(config, parser);
        this.messageFactory = new ClientMessageFactory(prototypes);
    }

    public void start() {
        // TODO
    }

    private String registerUser() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    private void startServerListener() {
        // TODO
    }

    private void readAndSendMessages(String identifier) {
        // TODO
    }

    private void close() {
        // TODO
    }
}
