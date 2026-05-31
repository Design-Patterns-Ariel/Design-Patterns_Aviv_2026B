package Week08.task.client;

import Week08.task.common.Message;
import Week08.task.common.MessagePrototypeRegistry;

public class ClientMessageFactory {
    private final MessagePrototypeRegistry prototypes;

    public ClientMessageFactory(MessagePrototypeRegistry prototypes) {
        this.prototypes = prototypes;
    }

    public Message createRegisterMessage(String identifier) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public Message createPrivateMessage(String from, String to, String title, String body) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public Message createDisconnectMessage(String identifier) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
