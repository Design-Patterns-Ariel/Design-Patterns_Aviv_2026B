package Week09.common;

/**
 * Contract for converting a {@link Message} to/from the text that travels over the socket.
 * Client and server depend on this interface, not on a concrete format (DIP),
 * so the wire format could be swapped (e.g. to JSON) without touching them.
 */
public interface MessageParser {

    /** Marker line that frames the end of one serialized message on the wire. */
    String END_OF_MESSAGE = "<<<END>>>";

    String serialize(Message message);

    Message parse(String rawMessage);
}
