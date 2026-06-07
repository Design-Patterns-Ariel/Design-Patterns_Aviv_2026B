package Week09.server;

import Week09.observer.MessageObserver;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * In-memory {@link ClientRegistry}. Uses a {@link ConcurrentHashMap} because
 * several client threads can add/remove/look up clients concurrently.
 */
public class InMemoryClientRegistry implements ClientRegistry {
    private final ConcurrentMap<String, MessageObserver> clients = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String identifier) {
        return identifier != null && clients.containsKey(identifier);
    }

    @Override
    public void add(MessageObserver observer) {
        if (observer != null && observer.getIdentifier() != null) {
            clients.put(observer.getIdentifier(), observer);
        }
    }

    @Override
    public void remove(String identifier) {
        if (identifier != null) {
            clients.remove(identifier);
        }
    }

    @Override
    public Optional<MessageObserver> find(String identifier) {
        if (identifier == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(clients.get(identifier));
    }

    @Override
    public Collection<MessageObserver> findAll() {
        return clients.values();
    }
}
