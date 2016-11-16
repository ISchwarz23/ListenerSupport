package de.codecrafters.utils.listenersupport;

import java.util.concurrent.Executor;

/**
 * Implementation of ListenerSupport for callback method with one argument.
 *
 * @param <L>  The type of the listener that shall be notified.
 * @param <D1> The type of the first argument that shall be passed to the callback method.
 * @param <D2> The type of the second argument that shall be passed to the callback method.
 * @author ISchwarz
 */
public final class ListenerSupport2<L, D1, D2> extends ListenerSupportBase<L> {

    /**
     * Interface for a listener notifiers with a callback methods with one argument.
     *
     * @param <L>  The type of the listener that shall be notified.
     * @param <D1> The type of the first argument that shall be passed to the callback method.
     * @param <D2> The type of the second argument that shall be passed to the callback method.
     */
    public interface ListenerNotifier<L, D1, D2> {
        void notifyListener(L listener, D1 data1, D2 data2);
    }

    private final ListenerNotifier<L, D1, D2> notifier;

    /**
     * Creates a new ListenerSupport that uses the given {@link ListenerSupport2.ListenerNotifier} and the given {@link Executor}.
     *
     * @param notifier       The {@link ListenerSupport2.ListenerNotifier} that shall be used to notify the listeners.
     * @param notifyExecutor The {@link Executor} that shall be used to trigger the notification.
     */
    ListenerSupport2(final ListenerNotifier<L, D1, D2> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    /**
     * Notifies the registered listeners with the given arguments.
     *
     * @param data1 The fist argument that shall be passed to the listeners.
     * @param data2 The second argument that shall be passed to the listeners.
     */
    public void notifyListeners(final D1 data1, D2 data2) {
        getListeners().forEach(listener -> notifyListener(listener, data1, data2));
    }

    private void notifyListener(L listener, D1 data1, D2 data2) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener, data1, data2));
    }
}
