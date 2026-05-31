package Week08.task.server;

public class RegistrationService {
    private final ClientRegistry registry;
    private final int maxAttempts;

    public RegistrationService(ClientRegistry registry, int maxAttempts) {
        this.registry = registry;
        this.maxAttempts = maxAttempts;
    }

    public boolean canRegister(String identifier) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
