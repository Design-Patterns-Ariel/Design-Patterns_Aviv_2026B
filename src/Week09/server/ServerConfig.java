package Week09.server;

/**
 * Server configuration, created via the Builder pattern. Immutable.
 */
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
        private int maxClients = 10;
        private int maxRegistrationAttempts = 5;

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder maxClients(int maxClients) {
            this.maxClients = maxClients;
            return this;
        }

        public Builder maxRegistrationAttempts(int maxRegistrationAttempts) {
            this.maxRegistrationAttempts = maxRegistrationAttempts;
            return this;
        }

        public ServerConfig build() {
            if (port < 1 || port > 65535) {
                throw new IllegalStateException("port must be in range 1..65535");
            }
            if (maxClients <= 0) {
                throw new IllegalStateException("maxClients must be positive");
            }
            if (maxRegistrationAttempts <= 0) {
                throw new IllegalStateException("maxRegistrationAttempts must be positive");
            }
            return new ServerConfig(this);
        }
    }
}
