package Week08.task.server;

import Week08.task.common.Message;
import Week08.task.common.MessagePrototypeRegistry;
import Week08.task.observer.MessageSubject;

public class MessageRouter {
    private final MessageSubject subject;
    private final MessagePrototypeRegistry prototypes;

    public MessageRouter(MessageSubject subject, MessagePrototypeRegistry prototypes) {
        this.subject = subject;
        this.prototypes = prototypes;
    }

    public void route(Message message) {
        // TODO
    }

    private void routePrivateMessage(Message message) {
        // TODO
    }

    private void routeBroadcastMessage(Message message) {
        // TODO
    }

    private Message createErrorMessage(String targetIdentifier, String errorText) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
