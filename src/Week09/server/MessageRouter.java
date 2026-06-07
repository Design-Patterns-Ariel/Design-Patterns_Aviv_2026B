package Week09.server;

import Week09.common.Message;
import Week09.common.MessagePrototypeRegistry;
import Week09.common.MessageType;
import Week09.observer.MessageSubject;

/**
 * Decides where an incoming message should go, based on its header.
 * It never writes to a socket directly – it asks the {@link MessageSubject} to
 * notify the right observer(s). Error messages are built from a Prototype.
 */
public class MessageRouter {
    private final MessageSubject subject;
    private final MessagePrototypeRegistry prototypes;

    public MessageRouter(MessageSubject subject, MessagePrototypeRegistry prototypes) {
        this.subject = subject;
        this.prototypes = prototypes;
    }

    public void route(Message message) {
        MessageType type = message.getHeader().getType();
        if (type == null) {
            return;
        }
        switch (type) {
            case PRIVATE_MESSAGE:
                routePrivateMessage(message);
                break;
            case BROADCAST_MESSAGE:
                routeBroadcastMessage(message);
                break;
            default:
                // REGISTER / DISCONNECT etc. are handled by the ClientSession, not routed.
                break;
        }
    }

    private void routePrivateMessage(Message message) {
        String to = message.getHeader().getTo();
        String from = message.getHeader().getFrom();

        boolean delivered = subject.notifyObserver(to, message);
        if (!delivered && from != null) {
            // Target not connected -> report the error back to the sender.
            Message error = createErrorMessage(from, "User '" + to + "' is not connected.");
            subject.notifyObserver(from, error);
        }
    }

    private void routeBroadcastMessage(Message message) {
        // Goes to update-service subscribers only (handled inside the subject).
        subject.notifyAllObservers(message);
    }

    private Message createErrorMessage(String targetIdentifier, String errorText) {
        Message error = prototypes.createMessage(MessageType.ERROR);
        error.getHeader().setFrom("SERVER");
        error.getHeader().setTo(targetIdentifier);
        error.getHeader().setTitle("error");
        error.setBody(errorText);
        return error;
    }
}
