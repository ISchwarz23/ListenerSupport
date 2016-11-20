package de.codecrafters.listenersupport.failure;

/**
 * Interface of a failure handler.
 *
 * @author ISchwarz
 */
public interface FailureStrategy<L> {

    /**
     * Callback method that is called when an {@link Throwable} is thrown during listener notification.
     *
     * @param listener  The listener that have been notified.
     * @param throwable The {@link Throwable} that has been thrown.
     */
    void onFailure(final L listener, final Throwable throwable);

}
