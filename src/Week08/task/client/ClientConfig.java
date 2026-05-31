package Week08.task.client;

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
        private int maxRegistrationAttempts;

        public Builder host(String host) {
            this.host = host; // TODO
            return this;
        }

        public Builder port(int port) {
            this.port = port; // TODO
            return this;
        }

        public Builder maxRegistrationAttempts(int maxRegistrationAttempts) {
            this.maxRegistrationAttempts = maxRegistrationAttempts; // TODO
            return this;
        }

        public ClientConfig build() {
            // TODO
            return new ClientConfig(this);
        }
    }
}
