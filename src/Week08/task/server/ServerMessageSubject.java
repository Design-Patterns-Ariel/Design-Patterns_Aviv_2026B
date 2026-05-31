package Week08.task.server;

import Week08.task.common.Message;
import Week08.task.observer.MessageObserver;
import Week08.task.observer.MessageSubject;

public class ServerMessageSubject implements MessageSubject {
    private final ClientRegistry registry;

    public ServerMessageSubject(ClientRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void registerObserver(MessageObserver observer) {
        // TODO
    }

    @Override
    public void unregisterObserver(String identifier) {
        // TODO
    }

    @Override
    public void notifyObserver(String identifier, Message message) {
        // TODO
    }

    @Override
    public void notifyAllObservers(Message message) {
        // TODO
    }
}
