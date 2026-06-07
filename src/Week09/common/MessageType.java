package Week09.common;

/**
 * The set of legal message kinds. Lives inside {@link MessageHeader} so every
 * message can declare its type, and the server can decide how to handle it.
 */
public enum MessageType {
    REGISTER,
    REGISTER_ACK,
    PRIVATE_MESSAGE,
    BROADCAST_MESSAGE,
    ERROR,
    DISCONNECT
}
