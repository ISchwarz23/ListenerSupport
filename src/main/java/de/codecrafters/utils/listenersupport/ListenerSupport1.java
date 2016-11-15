package de.codecrafters.utils.listenersupport;


import java.util.concurrent.Executor;

/**
 * Implementation of ListenerSupport for callback method with one argument.
 *
 * @author ISchwarz
 */
public class ListenerSupport1<L, D> extends ListenerSupportBase<L> {

    /**
     * Interface for a listener notifiers with a callback methods with one argument.
     *
     * @param <L> The type of the listener that shall be notified.
     * @param <D> The type of the argument that shall be passed to the callback method.
     */
    public interface ListenerNotifier<L, D> {

        /**
         * Passes the given data to the given listener.
         *
         * @param listener The listener that shall be notified.
         * @param data     The data that shall be passed to the listener.
         */
        void notifyListener(L listener, D data);
    }

    private final ListenerNotifier<L, D> notifier;

    /**
     * Creates a new ListenerSupport that uses the given {@link ListenerNotifier}. It will execute the notification in
     * the current thread.
     *
     * @param notifier The {@link ListenerNotifier} that shall be used to notify the listeners.
     */
    ListenerSupport1(final ListenerNotifier<L, D> notifier) {
        this(notifier, NotifyExecutors.createCurrentThreadExecutor());
    }

    /**
     * Creates a new ListenerSupport that uses the given {@link ListenerNotifier} and the given {@link Executor}.
     *
     * @param notifier       The {@link ListenerNotifier} that shall be used to notify the listeners.
     * @param notifyExecutor The {@link Executor} that shall be used to trigger the notification.
     */
    ListenerSupport1(final ListenerNotifier<L, D> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    /**
     * Notifies the registered listeners with the given data.
     *
     * @param data The data that shall be passed to the listeners.
     */
    public void notifyListeners(final D data) {
        getListeners().forEach(listener -> notifyListener(listener, data));
    }

    private void notifyListener(L listener, D data) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener, data));
    }
}
