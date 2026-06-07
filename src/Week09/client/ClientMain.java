package Week09.client;

import Week09.common.MessagePrototypeRegistry;
import Week09.common.TextMessageParser;

/**
 * Entry point for the client. Stays tiny: build a {@link ClientConfig} with the
 * Builder, then hand everything to the {@link ChatClientFacade}. No socket / I/O
 * logic lives here.
 */
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
