package Week09.client;

/**
 * Client configuration, created via the Builder pattern.
 * Immutable: there is no public constructor and there are no setters.
 */
public class ClientConfig {
    private final String host;
    private final int port;
    private final int maxRegistrationAttempts;

    private ClientConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.maxRegistrationAttempts = builder.maxRegistrationAttempts;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getMaxRegistrationAttempts() {
        return maxRegistrationAttempts;
    }

    public static class Builder {
        private String host;
        private int port;
        private int maxRegistrationAttempts = 5;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder maxRegistrationAttempts(int maxRegistrationAttempts) {
            this.maxRegistrationAttempts = maxRegistrationAttempts;
            return this;
        }

        public ClientConfig build() {
            if (host == null || host.isBlank()) {
                throw new IllegalStateException("host must not be empty");
            }
            if (port < 1 || port > 65535) {
                throw new IllegalStateException("port must be in range 1..65535");
            }
            if (maxRegistrationAttempts <= 0) {
                throw new IllegalStateException("maxRegistrationAttempts must be positive");
            }
            return new ClientConfig(this);
        }
    }
}
