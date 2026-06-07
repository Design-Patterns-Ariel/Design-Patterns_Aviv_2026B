package Week09.common;

/**
 * A full message: a {@link MessageHeader} plus a textual body.
 * Implements {@link Prototype} so a message (header + body) can be cloned.
 */
public class Message implements Prototype<Message> {
    private MessageHeader header;
    private String body;

    public Message() {
        this.header = new MessageHeader();
        this.body = "";
    }

    public Message(MessageHeader header, String body) {
        this.header = header;
        this.body = body;
    }

    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public Message copy() {
        // The header is deep-copied (not shared by reference) so two messages
        // cloned from the same prototype don't step on each other.
        MessageHeader headerCopy = (header == null) ? new MessageHeader() : header.copy();
        return new Message(headerCopy, body);
    }
}
