package Week08.task.client;

import Week08.task.common.MessagePrototypeRegistry;
import Week08.task.common.TextMessageParser;

public class ClientMain {
    public static void main(String[] args) {
        ClientConfig config = ClientConfig.builder()
                .host("localhost")
                .port(8010)
                .maxRegistrationAttempts(5)
                .build();

        ChatClientFacade client = new ChatClientFacade(
                config,
                new TextMessageParser(),
                new MessagePrototypeRegistry()
        );

        client.start();
    }
}
