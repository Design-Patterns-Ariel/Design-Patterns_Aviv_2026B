package Week09.common;

import java.util.HashMap;
import java.util.Map;

/**
 * The "header" part of a message, similar in spirit to HTTP headers.
 * Holds the routing/control information (who, to whom, type, ...) separately
 * from the body. Implements {@link Prototype} so a header can be deep-copied.
 */
public class MessageHeader implements Prototype<MessageHeader> {
    private MessageType type;
    private String from;
    private String to;
    private String title;
    private int contentLength;
    private final Map<String, String> metadata = new HashMap<>();

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public MessageHeader copy() {
        MessageHeader clone = new MessageHeader();
        clone.type = this.type;
        clone.from = this.from;
        clone.to = this.to;
        clone.title = this.title;
        clone.contentLength = this.contentLength;
        // Copy the entries into a NEW map – not just the reference – so that
        // changing the copy's metadata never affects the original prototype.
        clone.metadata.putAll(this.metadata);
        return clone;
    }
}
