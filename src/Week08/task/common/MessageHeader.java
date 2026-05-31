package Week08.task.common;

import java.util.HashMap;
import java.util.Map;

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
        this.type = type; // TODO
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from; // TODO
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to; // TODO
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title; // TODO
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength; // TODO
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public MessageHeader copy() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
