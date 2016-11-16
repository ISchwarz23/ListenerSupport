package de.codecrafters.utils.listenersupport;

import de.codecrafters.utils.listenersupport.failure.FailureStrategies;
import de.codecrafters.utils.listenersupport.failure.FailureStrategy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/**
 * The base for the ListenerSupport implementations, which is managing the registration and unregistration of listeners.
 *
 * @param <L> The type of the listener that shall be notified.
 * @author ISchwarz
 */
abstract class ListenerSupportBase<L> {

    private final Set<L> listeners = new HashSet<>();
    private final Executor notifyExecutor;

    private FailureStrategy<? super L> failureStrategy = FailureStrategies.createLog4jLogger();

    /**
     * Creates a new {@link ListenerSupportBase} that will notify the registered listeners using the given {@link Executor}.
     *
     * @param notifyExecutor The {@link Executor} that shall be used to notify the registered listeners.
     */
    ListenerSupportBase(final Executor notifyExecutor) {
        this.notifyExecutor = notifyExecutor;
    }

    /**
     * Adds a listener to this support. The listener will be notified, when calling the "notifyListeners" method.
     *
     * @param listenerToAdd The listener which shall be added.
     * @return Whether or nor the adding of the listener was successful.
     */
    public boolean addListener(final L listenerToAdd) {
        return listeners.add(listenerToAdd);
    }

    /**
     * Removes a listener from this support. The listener will not be notified anymore, when calling the "notifyListeners"
     * method.
     *
     * @param listenerToRemove The listener that shall be removed.
     * @return Whether or not the removing of the listener was successful.
     */
    public boolean removeListener(final L listenerToRemove) {
        return listeners.remove(listenerToRemove);
    }

    /**
     * Sets a {@link FailureStrategy} to this ListenerSupport.
     *
     * @param failureStrategy The {@link FailureStrategy} that shall be used.
     */
    public void setFailureStrategy(FailureStrategy<? super L> failureStrategy) {
        this.failureStrategy = failureStrategy;
    }

    /**
     * Gives all registered listeners from this support.
     *
     * @return All listeners from this support.
     */
    Set<L> getListeners() {
        return listeners;
    }

    /**
     * Executes the runnable for listener notification. If a {@link Throwable} is thrown by the listener,
     * it is forwarded to the {@link FailureStrategy}.
     *
     * @param listener     The listener that is notified.
     * @param notifyAction The action that shall be executed to notify the listener.
     */
    void notifyListenerInExecutor(L listener, final Runnable notifyAction) {
        notifyExecutor.execute(() -> {
            try {
                notifyAction.run();
            } catch (Throwable t) {
                try {
                    getFailureStrategy().onFailure(listener, t);
                } catch (Throwable t2) {
                    // ignore errors in FailureStrategy
                }
            }
        });
    }

    /**
     * Gives the current {@link FailureStrategy}.
     *
     * @return The {@link FailureStrategy} that is currently used.
     */
    private FailureStrategy<? super L> getFailureStrategy() {
        return failureStrategy;
    }
}
