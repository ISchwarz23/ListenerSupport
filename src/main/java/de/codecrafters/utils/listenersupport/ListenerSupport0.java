package de.codecrafters.utils.listenersupport;


import java.util.concurrent.Executor;

/**
 * Implementation of ListenerSupport for callback method with one argument.
 *
 * @author ISchwarz
 */
public class ListenerSupport0<L> extends ListenerSupportBase<L> {

    /**
     * Interface for a listener notifiers with a callback methods with no argument.
     *
     * @param <L> The type of the listener that shall be notified.
     */
    public interface ListenerNotifier<L> {

        /**
         * Notifies the given listener.
         *
         * @param listener The listener that shall be notified.
         */
        void notifyListener(L listener);
    }

    private final ListenerNotifier<L> notifier;

    /**
     * Creates a new ListenerSupport that uses the given {@link ListenerNotifier} and the given {@link Executor}.
     *
     * @param notifier       The {@link ListenerNotifier} that shall be used to notify the listeners.
     * @param notifyExecutor The {@link Executor} that shall be used to trigger the notification.
     */
    ListenerSupport0(final ListenerNotifier<L> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    /**
     * Notifies the registered listeners.
     */
    public void notifyListeners() {
        getListeners().forEach(this::notifyListener);
    }

    private void notifyListener(L listener) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener));
    }
}
