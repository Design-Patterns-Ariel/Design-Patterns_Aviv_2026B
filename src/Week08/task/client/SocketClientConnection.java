package Week08.task.client;

import Week08.task.common.Message;
import Week08.task.common.MessageParser;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientConnection implements ClientConnection {
    private final ClientConfig config;
    private final MessageParser parser;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public SocketClientConnection(ClientConfig config, MessageParser parser) {
        this.config = config;
        this.parser = parser;
    }

    @Override
    public void connect() {
        // TODO
    }

    @Override
    public void send(Message message) {
        // TODO
    }

    @Override
    public Message read() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean isConnected() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void close() {
        // TODO
    }
}
