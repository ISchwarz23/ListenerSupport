package de.codecrafters.listenersupport;

import java.util.concurrent.Executor;

/**
 * Implementation of ListenerSupport for callback method with five arguments.
 *
 * @param <L>  The type of the listener that shall be notified.
 * @param <A1> The type of the first argument that shall be passed to the callback method.
 * @param <A2> The type of the second argument that shall be passed to the callback method.
 * @param <A3> The type of the third argument that shall be passed to the callback method.
 * @param <A4> The type of the forth argument that shall be passed to the callback method.
 * @param <A5> The type of the fifth argument that shall be passed to the callback method.
 * @author ISchwarz
 */
public final class ListenerSupport5<L, A1, A2, A3, A4, A5> extends ListenerSupportBase<L> {

    /**
     * Interface for a listener notifiers with a callback methods with two arguments.
     *
     * @param <L>  The type of the listener that shall be notified.
     * @param <A1> The type of the first argument that shall be passed to the callback method.
     * @param <A2> The type of the second argument that shall be passed to the callback method.
     * @param <A3> The type of the third argument that shall be passed to the callback method.
     * @param <A4> The type of the fourth argument that shall be passed to the callback method.
     * @param <A5> The type of the fifth argument that shall be passed to the callback method.
     */
    public interface ListenerNotifier<L, A1, A2, A3, A4, A5> {
        void notifyListener(final L listener, final A1 arg1, final A2 arg2, final A3 arg3, final A4 arg4, final A5 arg5);
    }

    private final ListenerNotifier<L, A1, A2, A3, A4, A5> notifier;

    /**
     * Creates a new ListenerSupport that uses the given {@link ListenerNotifier} and the given {@link Executor}.
     *
     * @param notifier       The {@link ListenerNotifier} that shall be used to notify the listeners.
     * @param notifyExecutor The {@link Executor} that shall be used to trigger the notification.
     */
    ListenerSupport5(final ListenerNotifier<L, A1, A2, A3, A4, A5> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    /**
     * Notifies the registered listeners with the given arguments.
     *
     * @param arg1 The fist argument that shall be passed to the listeners.
     * @param arg2 The second argument that shall be passed to the listeners.
     * @param arg3 The third argument that shall be passed to the listeners.
     * @param arg4 The fourth argument that shall be passed to the listeners.
     * @param arg5 The fifth argument that shall be passed to the listeners.
     */
    public void notifyListeners(final A1 arg1, final A2 arg2, final A3 arg3, final A4 arg4, final A5 arg5) {
        getListeners().forEach(listener -> notifyListener(listener, arg1, arg2, arg3, arg4, arg5));
    }

    private void notifyListener(final L listener, final A1 arg1, final A2 arg2, final A3 arg3, final A4 arg4, final A5 arg5) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener, arg1, arg2, arg3, arg4, arg5));
    }
}
