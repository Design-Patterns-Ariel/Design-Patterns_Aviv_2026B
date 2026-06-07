package Week09.common;

import java.util.Map;

/**
 * Simple text implementation of {@link MessageParser}, using an HTTP-like format:
 * header lines, a blank line, then the body. Custom header fields are written as
 * {@code X-<key>: <value>} lines.
 *
 * Example (one serialized message):
 * <pre>
 * TYPE: PRIVATE_MESSAGE
 * FROM: 0501111111
 * TO: 0502222222
 * TITLE: hello
 * CONTENT_LENGTH: 16
 *
 * Hello from David
 * </pre>
 *
 * Body newlines are escaped to {@code \n} so a body is always a single logical line,
 * which keeps line-based socket framing simple.
 */
public class TextMessageParser implements MessageParser {

    @Override
    public String serialize(Message message) {
        MessageHeader header = message.getHeader();
        String body = message.getBody() == null ? "" : message.getBody();

        StringBuilder sb = new StringBuilder();
        sb.append("TYPE: ").append(header.getType()).append('\n');
        sb.append("FROM: ").append(nullToEmpty(header.getFrom())).append('\n');
        sb.append("TO: ").append(nullToEmpty(header.getTo())).append('\n');
        sb.append("TITLE: ").append(nullToEmpty(header.getTitle())).append('\n');
        sb.append("CONTENT_LENGTH: ").append(body.length()).append('\n');
        for (Map.Entry<String, String> entry : header.getMetadata().entrySet()) {
            sb.append("X-").append(entry.getKey()).append(": ").append(entry.getValue()).append('\n');
        }
        sb.append('\n');            // blank line separates header from body
        sb.append(escape(body));
        return sb.toString();
    }

    @Override
    public Message parse(String rawMessage) {
        Message message = new Message();
        MessageHeader header = message.getHeader();

        String[] lines = rawMessage.split("\n", -1);
        int i = 0;

        // 1. Header lines, until the first blank line.
        for (; i < lines.length; i++) {
            String line = lines[i];
            if (line.isEmpty()) {
                i++;                // skip the blank separator; body starts next
                break;
            }
            int idx = line.indexOf(": ");
            if (idx < 0) {
                continue;
            }
            String key = line.substring(0, idx);
            String value = line.substring(idx + 2);
            switch (key) {
                case "TYPE":
                    header.setType(parseType(value));
                    break;
                case "FROM":
                    header.setFrom(value);
                    break;
                case "TO":
                    header.setTo(value);
                    break;
                case "TITLE":
                    header.setTitle(value);
                    break;
                case "CONTENT_LENGTH":
                    header.setContentLength(parseIntSafe(value));
                    break;
                default:
                    if (key.startsWith("X-")) {
                        header.getMetadata().put(key.substring(2), value);
                    }
            }
        }

        // 2. Everything after the blank line is the body.
        StringBuilder bodyBuilder = new StringBuilder();
        for (; i < lines.length; i++) {
            if (bodyBuilder.length() > 0) {
                bodyBuilder.append('\n');
            }
            bodyBuilder.append(lines[i]);
        }
        message.setBody(unescape(bodyBuilder.toString()));
        return message;
    }

    private static String escape(String text) {
        return text.replace("\n", "\\n");
    }

    private static String unescape(String text) {
        return text.replace("\\n", "\n");
    }

    private static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    private static MessageType parseType(String value) {
        try {
            return MessageType.valueOf(value);
        } catch (IllegalArgumentException e) {
            return MessageType.ERROR;
        }
    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
