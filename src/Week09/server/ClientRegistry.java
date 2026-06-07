package Week09.server;

import Week09.observer.MessageObserver;

import java.util.Collection;
import java.util.Optional;

/**
 * Contract for keeping track of the currently connected clients (as observers).
 * Depending on this interface (not a concrete map) lets us swap the storage
 * later – e.g. an in-memory map today, a database tomorrow (DIP).
 */
public interface ClientRegistry {
    boolean contains(String identifier);

    void add(MessageObserver observer);

    void remove(String identifier);

    Optional<MessageObserver> find(String identifier);

    Collection<MessageObserver> findAll();
}
