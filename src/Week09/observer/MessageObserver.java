package Week09.observer;

import Week09.common.Message;

/**
 * Observer side of the Observer pattern. Anything that wants to receive messages
 * implements this. On the server, every connected client is represented by a
 * ClientSession which is exactly such an observer.
 */
public interface MessageObserver {

    /** Unique identifier of the observer (e.g. a phone number). */
    String getIdentifier();

    /** Called by the subject when a message is delivered to this observer. */
    void update(Message message);

    /**
     * Whether this observer joined the "update service" (errors, news, software updates).
     * Broadcast messages are delivered only to observers that return {@code true}.
     */
    boolean wantsUpdates();
}
