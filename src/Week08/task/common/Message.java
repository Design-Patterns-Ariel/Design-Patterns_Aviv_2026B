package Week08.task.common;

public class Message implements Prototype<Message> {
    private MessageHeader header;
    private String body;

    public Message() {
        // TODO
    }

    public Message(MessageHeader header, String body) {
        this.header = header; // TODO
        this.body = body; // TODO
    }

    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header; // TODO
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body; // TODO
    }

    @Override
    public Message copy() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
