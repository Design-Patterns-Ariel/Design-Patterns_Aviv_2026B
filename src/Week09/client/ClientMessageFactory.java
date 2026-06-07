package Week09.client;

import Week09.common.Message;
import Week09.common.MessageHeader;
import Week09.common.MessagePrototypeRegistry;
import Week09.common.MessageType;

/**
 * Centralizes building client-side messages. Instead of spreading header-filling
 * logic all over the client, every message is created here – and always starts
 * from a Prototype copy obtained from the {@link MessagePrototypeRegistry}.
 */
public class ClientMessageFactory {
    private final MessagePrototypeRegistry prototypes;

    public ClientMessageFactory(MessagePrototypeRegistry prototypes) {
        this.prototypes = prototypes;
    }

    public Message createRegisterMessage(String identifier) {
        Message message = prototypes.createMessage(MessageType.REGISTER);
        message.getHeader().setFrom(identifier);
        message.getHeader().setTitle("register");
        message.setBody("");
        return message;
    }

    /** Reply sent during the registration handshake (the yes/no subscription answer). */
    public Message createRegisterAnswer(String identifier, String answer) {
        Message message = prototypes.createMessage(MessageType.REGISTER);
        message.getHeader().setFrom(identifier);
        message.getHeader().setTitle("register-answer");
        message.setBody(answer);
        return message;
    }

    public Message createPrivateMessage(String from, String to, String title, String body) {
        Message message = prototypes.createMessage(MessageType.PRIVATE_MESSAGE);
        MessageHeader header = message.getHeader();
        header.setFrom(from);
        header.setTo(to);
        header.setTitle(title);
        message.setBody(body);
        return message;
    }

    public Message createBroadcastMessage(String from, String title, String body) {
        Message message = prototypes.createMessage(MessageType.BROADCAST_MESSAGE);
        MessageHeader header = message.getHeader();
        header.setFrom(from);
        header.setTo("ALL");
        header.setTitle(title);
        message.setBody(body);
        return message;
    }

    public Message createDisconnectMessage(String identifier) {
        Message message = prototypes.createMessage(MessageType.DISCONNECT);
        message.getHeader().setFrom(identifier);
        message.setBody("");
        return message;
    }
}
