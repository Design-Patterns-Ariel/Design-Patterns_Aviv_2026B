package Week08.task.client;

import Week08.task.common.Message;

public class ServerListener implements Runnable {
    private final ClientConnection connection;
    private volatile boolean running = true;

    public ServerListener(ClientConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        // TODO
    }

    public void stop() {
        // TODO
    }

    private void handleIncomingMessage(Message message) {
        // TODO
    }
}
