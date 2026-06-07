package Week09.client;

import Week09.common.Message;
import Week09.common.MessageHeader;

/**
 * Runs on its own thread on the client side and listens for messages coming FROM the server.
 * A separate thread is needed because the main thread is busy reading the user's keyboard
 * input; without it, incoming messages would only show up after the user pressed Enter.
 */
public class ServerListener implements Runnable {
    private final ClientConnection connection;
    private volatile boolean running = true;

    public ServerListener(ClientConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (running && connection.isConnected()) {
            Message message = connection.read();
            if (message == null) {
                running = false;
                System.out.println("\n[Disconnected from server]");
                break;
            }
            handleIncomingMessage(message);
        }
    }

    public void stop() {
        running = false;
    }

    private void handleIncomingMessage(Message message) {
        MessageHeader header = message.getHeader();
        switch (header.getType()) {
            case PRIVATE_MESSAGE:
                System.out.println("\n[Private] from " + header.getFrom()
                        + " | " + header.getTitle() + ": " + message.getBody());
                break;
            case BROADCAST_MESSAGE:
                System.out.println("\n[Update] " + header.getTitle() + ": " + message.getBody());
                break;
            case ERROR:
                System.out.println("\n[Error] " + message.getBody());
                break;
            default:
                System.out.println("\n[Server] " + message.getBody());
        }
        System.out.print("> ");
    }
}
