package Week09.server;

/**
 * Owns the registration business logic only: deciding whether an identifier may
 * be registered (must be non-empty and not already in use) and how many attempts
 * are allowed. Routing/sending/reading messages is intentionally NOT here (SRP).
 */
public class RegistrationService {
    private final ClientRegistry registry;
    private final int maxAttempts;

    public RegistrationService(ClientRegistry registry, int maxAttempts) {
        this.registry = registry;
        this.maxAttempts = maxAttempts;
    }

    public boolean canRegister(String identifier) {
        return identifier != null && !identifier.isBlank() && !registry.contains(identifier);
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
