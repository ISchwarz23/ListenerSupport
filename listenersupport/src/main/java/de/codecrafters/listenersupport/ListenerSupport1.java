package de.codecrafters.listenersupport;


import java.util.concurrent.Executor;

/**
 * Implementation of ListenerSupport for callback method with one argument.
 *
 * @param <L> The type of the listener that shall be notified.
 * @param <A> The type of the argument that shall be passed to the callback method.
 * @author ISchwarz
 */
public final class ListenerSupport1<L, A> extends ListenerSupportBase<L> {

    /**
     * Interface for a listener notifiers with a callback methods with one argument.
     *
     * @param <L> The type of the listener that shall be notified.
     * @param <A> The type of the argument that shall be passed to the callback method.
     */
    public interface ListenerNotifier<L, A> {

        /**
         * Passes the given argument to the given listener.
         *
         * @param listener The listener that shall be notified.
         * @param arg      The argument that shall be passed to the listener.
         */
        void notifyListener(final L listener, final A arg);
    }

    private final ListenerNotifier<L, A> notifier;

    /**
     * Creates a new ListenerSupport that uses the given {@link ListenerNotifier} and the given {@link Executor}.
     *
     * @param notifier       The {@link ListenerNotifier} that shall be used to notify the listeners.
     * @param notifyExecutor The {@link Executor} that shall be used to trigger the notification.
     */
    ListenerSupport1(final ListenerNotifier<L, A> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    /**
     * Notifies the registered listeners with the given argument.
     *
     * @param arg The argument that shall be passed to the listeners.
     */
    public void notifyListeners(final A arg) {
        getListeners().forEach(listener -> notifyListener(listener, arg));
    }

    private void notifyListener(final L listener, final A arg) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener, arg));
    }
}
