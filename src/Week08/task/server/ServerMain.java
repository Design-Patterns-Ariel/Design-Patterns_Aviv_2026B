package Week08.task.server;

import Week08.task.common.MessagePrototypeRegistry;
import Week08.task.common.TextMessageParser;

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
