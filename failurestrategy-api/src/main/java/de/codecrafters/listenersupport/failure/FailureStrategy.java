package de.codecrafters.listenersupport.failure;

/**
 * Interface of a failure handler.
 *
 * @author ISchwarz
 */
public interface FailureStrategy {

    /**
     * Callback method that is called when an {@link Throwable} is thrown during listener notification.
     *
     * @param listener  The listener that has been notified.
     * @param throwable The {@link Throwable} that has been thrown.
     */
    void onFailure(final Object listener, final Throwable throwable);

}
