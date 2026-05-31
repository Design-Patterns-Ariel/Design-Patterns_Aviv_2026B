package Week08.task.client;

import Week08.task.common.Message;

public interface ClientConnection {
    void connect(); // TODO

    void send(Message message); // TODO

    Message read(); // TODO

    boolean isConnected(); // TODO

    void close(); // TODO
}
