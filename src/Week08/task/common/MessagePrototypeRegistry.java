package Week08.task.common;

import java.util.HashMap;
import java.util.Map;

public class MessagePrototypeRegistry {
    private final Map<MessageType, Message> prototypes = new HashMap<>();

    public MessagePrototypeRegistry() {
        // TODO
    }

    public void registerPrototype(MessageType type, Message prototype) {
        // TODO
    }

    public Message createMessage(MessageType type) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
