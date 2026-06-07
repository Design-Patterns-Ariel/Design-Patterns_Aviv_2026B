package Week09.server;

import Week09.common.MessagePrototypeRegistry;
import Week09.common.TextMessageParser;

/**
 * Entry point for the server. Stays tiny: build a {@link ServerConfig} with the
 * Builder, create the parser + prototype registry, then start the
 * {@link ChatServerFacade}. No socket / routing / registration logic here.
 */
public class ServerMain {
    public static void main(String[] args) {
        ServerConfig config = ServerConfig.builder()
                .port(8010)
                .maxClients(10)
                .maxRegistrationAttempts(5)
                .build();

        ChatServerFacade server = new ChatServerFacade(
                config,
                new TextMessageParser(),
                new MessagePrototypeRegistry()
        );

        server.start();
    }
}
