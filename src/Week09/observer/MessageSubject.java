package Week09.observer;

import Week09.common.Message;

/**
 * Subject side of the Observer pattern. Manages observers and notifies them.
 * The subject works only against the {@link MessageObserver} interface – it does
 * not know how a particular observer actually delivers a message (e.g. via a socket).
 */
public interface MessageSubject {

    void registerObserver(MessageObserver observer);

    void unregisterObserver(String identifier);

    /**
     * Notify a single observer identified by {@code identifier}.
     *
     * @return {@code true} if an observer with that identifier existed and was notified.
     */
    boolean notifyObserver(String identifier, Message message);

    /**
     * Notify all observers subscribed to the update service (the broadcast channel).
     */
    void notifyAllObservers(Message message);
}
