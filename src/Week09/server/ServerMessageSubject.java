package Week09.server;

import Week09.common.Message;
import Week09.observer.MessageObserver;
import Week09.observer.MessageSubject;

import java.util.Optional;

/**
 * Server-side implementation of the Observer pattern's Subject.
 * It manages observers through the {@link ClientRegistry} and notifies them,
 * without ever knowing how an observer actually delivers the message (e.g. socket).
 */
public class ServerMessageSubject implements MessageSubject {
    private final ClientRegistry registry;

    public ServerMessageSubject(ClientRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void registerObserver(MessageObserver observer) {
        registry.add(observer);
    }

    @Override
    public void unregisterObserver(String identifier) {
        registry.remove(identifier);
    }

    @Override
    public boolean notifyObserver(String identifier, Message message) {
        Optional<MessageObserver> observer = registry.find(identifier);
        observer.ifPresent(o -> o.update(message));
        return observer.isPresent();
    }

    @Override
    public void notifyAllObservers(Message message) {
        // Broadcast == the "update service": deliver only to clients that subscribed.
        for (MessageObserver observer : registry.findAll()) {
            if (observer.wantsUpdates()) {
                observer.update(message);
            }
        }
    }
}
