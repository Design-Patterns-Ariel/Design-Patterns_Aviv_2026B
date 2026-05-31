package Week08.task.server;

import Week08.task.common.Message;
import Week08.task.common.MessageParser;
import Week08.task.observer.MessageObserver;
import Week08.task.observer.MessageSubject;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSession implements Runnable, MessageObserver {
    private final Socket socket;
    private final MessageParser parser;
    private final RegistrationService registrationService;
    private final MessageSubject subject;
    private final MessageRouter router;
    private BufferedReader reader;
    private PrintWriter writer;
    private String identifier;
    private volatile boolean running = true;

    public ClientSession(
            Socket socket,
            MessageParser parser,
            RegistrationService registrationService,
            MessageSubject subject,
            MessageRouter router
    ) {
        this.socket = socket;
        this.parser = parser;
        this.registrationService = registrationService;
        this.subject = subject;
        this.router = router;
    }

    @Override
    public void run() {
        // TODO
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void update(Message message) {
        // TODO
    }

    private boolean registerClient() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    private Message readMessage() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    private void sendMessage(Message message) {
        // TODO
    }

    private void close() {
        // TODO
    }
}
