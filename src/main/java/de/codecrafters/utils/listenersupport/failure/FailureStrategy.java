package de.codecrafters.utils.listenersupport.failure;

/**
 * Interface of a failure handler.
 *
 * @author ISchwarz
 */
public interface FailureStrategy<L> {

    /**
     * Callback method that is called when an {@link Throwable} is thrown during listener notification.
     *
     * @param listener The listener that have been notified.
     * @param t        The {@link Throwable} that has been catched.
     */
    void onFailure(L listener, Throwable t);

}
