package Week08.task.observer;

import Week08.task.common.Message;

public interface MessageSubject {
    void registerObserver(MessageObserver observer); // TODO

    void unregisterObserver(String identifier); // TODO

    void notifyObserver(String identifier, Message message); // TODO

    void notifyAllObservers(Message message); // TODO
}
