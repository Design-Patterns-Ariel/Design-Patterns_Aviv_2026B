package Week08.task.common;

public interface MessageParser {
    String serialize(Message message); // TODO

    Message parse(String rawMessage); // TODO
}
