package Week08.task.server;

import Week08.task.observer.MessageObserver;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryClientRegistry implements ClientRegistry {
    private final ConcurrentMap<String, MessageObserver> clients = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String identifier) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void add(MessageObserver observer) {
        // TODO
    }

    @Override
    public void remove(String identifier) {
        // TODO
    }

    @Override
    public Optional<MessageObserver> find(String identifier) {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public Collection<MessageObserver> findAll() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
