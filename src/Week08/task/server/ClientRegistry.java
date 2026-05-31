package Week08.task.server;

import Week08.task.observer.MessageObserver;

import java.util.Collection;
import java.util.Optional;

public interface ClientRegistry {
    boolean contains(String identifier); // TODO

    void add(MessageObserver observer); // TODO

    void remove(String identifier); // TODO

    Optional<MessageObserver> find(String identifier); // TODO

    Collection<MessageObserver> findAll(); // TODO
}
