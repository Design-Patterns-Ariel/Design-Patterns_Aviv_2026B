package Week09.client;

import Week09.common.Message;
import Week09.common.MessageParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * The only class on the client side that knows about {@link Socket}, readers and writers.
 * It uses a {@link MessageParser} to convert messages to/from text. By hiding the
 * networking details here, the rest of the client stays free of socket code (SRP + DIP).
 */
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
        try {
            socket = new Socket(config.getHost(), config.getPort());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to " + config.getHost() + ":" + config.getPort(), e);
        }
    }

    @Override
    public void send(Message message) {
        if (writer == null) {
            return;
        }
        writer.print(parser.serialize(message));
        writer.print('\n');                         // terminate the body line
        writer.println(MessageParser.END_OF_MESSAGE); // frame the end of the message
        writer.flush();
    }

    @Override
    public Message read() {
        if (reader == null) {
            return null;
        }
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
            return null; // stream closed by server
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean isConnected() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    @Override
    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ignored) {
            // closing – nothing useful to do
        }
        if (writer != null) {
            writer.close();
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ignored) {
            // closing – nothing useful to do
        }
    }
}
