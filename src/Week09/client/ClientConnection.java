package Week09.client;

import Week09.common.Message;

/**
 * Contract for the client's connection to the server. The rest of the client
 * depends on this interface, not on {@link java.net.Socket} directly (DIP).
 */
public interface ClientConnection {
    void connect();

    void send(Message message);

    /** Reads the next message from the server, or {@code null} if the stream is closed. */
    Message read();

    boolean isConnected();

    void close();
}
