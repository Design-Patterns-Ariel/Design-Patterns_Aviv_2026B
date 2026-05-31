package Week08.task.server;

public class ServerConfig {
    private final int port;
    private final int maxClients;
    private final int maxRegistrationAttempts;

    private ServerConfig(Builder builder) {
        this.port = builder.port;
        this.maxClients = builder.maxClients;
        this.maxRegistrationAttempts = builder.maxRegistrationAttempts;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getPort() {
        return port;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public int getMaxRegistrationAttempts() {
        return maxRegistrationAttempts;
    }

    public static class Builder {
        private int port;
        private int maxClients;
        private int maxRegistrationAttempts;

        public Builder port(int port) {
            this.port = port; // TODO
            return this;
        }

        public Builder maxClients(int maxClients) {
            this.maxClients = maxClients; // TODO
            return this;
        }

        public Builder maxRegistrationAttempts(int maxRegistrationAttempts) {
            this.maxRegistrationAttempts = maxRegistrationAttempts; // TODO
            return this;
        }

        public ServerConfig build() {
            // TODO
            return new ServerConfig(this);
        }
    }
}
