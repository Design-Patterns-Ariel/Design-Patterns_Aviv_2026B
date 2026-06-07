package Week09.common;

/**
 * Prototype pattern – contract for objects that can clone a fresh copy of themselves.
 * Implemented by {@link Message} and {@link MessageHeader} so that instead of building
 * every message from scratch we can keep ready templates and duplicate them.
 */
public interface Prototype<T> {
    T copy();
}
