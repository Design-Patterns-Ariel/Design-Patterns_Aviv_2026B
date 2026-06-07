package Week09.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Prototype pattern in action: holds one ready template message per {@link MessageType}.
 * Instead of constructing a message from scratch each time, callers ask the registry
 * for a fresh COPY of a template and then fill in the specific fields.
 */
public class MessagePrototypeRegistry {
    private final Map<MessageType, Message> prototypes = new HashMap<>();

    public MessagePrototypeRegistry() {
        // Register a default template for every type. Each template carries its type
        // in the header, so a copy already "knows" what kind of message it is.
        for (MessageType type : MessageType.values()) {
            MessageHeader header = new MessageHeader();
            header.setType(type);
            registerPrototype(type, new Message(header, ""));
        }
    }

    public void registerPrototype(MessageType type, Message prototype) {
        prototypes.put(type, prototype);
    }

    public Message createMessage(MessageType type) {
        Message prototype = prototypes.get(type);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype registered for type: " + type);
        }
        // Never hand out the prototype itself – always a fresh clone.
        return prototype.copy();
    }
}
