package de.codecrafters.utils.listenersupport;

import java.util.concurrent.Executor;

import static de.codecrafters.utils.listenersupport.NotifyExecutors.currentThreadExecutor;

/**
 * A ListenerSupport factory that supports listeners with methods up to three arguments.
 *
 * @author ISchwarz
 */
public class ListenerSupport {

    private ListenerSupport() {
        //no instance
    }

    /**
     * Creates a ListenerSupport for a callback method without arguments. The notification will be done in the
     * thread from which the notifyListeners() method is called.
     *
     * @param notifier The {@link ListenerSupport0.ListenerNotifier} that shall be used to notify the listener.
     * @param <L>      The type of the listener that shall be notified.
     * @return The created {@link ListenerSupport0}.
     */
    public static <L> ListenerSupport0<L> createFor(
            final ListenerSupport0.ListenerNotifier<L> notifier) {

        return createFor(notifier, currentThreadExecutor());
    }

    /**
     * Creates a ListenerSupport for a callback method without arguments. The notification will be done using the
     * given {@link Executor}.
     *
     * @param notifier The {@link ListenerSupport0.ListenerNotifier} that shall be used to notify the listener.
     * @param executor The {@link Executor} that will be used to notify the listeners.
     * @param <L>      The type of the listener that shall be notified.
     * @return The created {@link ListenerSupport0}.
     */
    public static <L> ListenerSupport0<L> createFor(
            final ListenerSupport0.ListenerNotifier<L> notifier,
            final Executor executor) {

        return new ListenerSupport0<>(notifier, executor);
    }

    /**
     * Creates a ListenerSupport for a callback method with one argument. The notification will be done in the
     * thread from which the notifyListeners() method is called.
     *
     * @param notifier The {@link ListenerSupport1.ListenerNotifier} that shall be used to notify the listener.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D>      The type of the argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport1}.
     */
    public static <L, D> ListenerSupport1<L, D> createFor(
            final ListenerSupport1.ListenerNotifier<L, D> notifier) {

        return createFor(notifier, currentThreadExecutor());
    }

    /**
     * Creates a ListenerSupport for a callback method with one argument. The notification will be done using the
     * given {@link Executor}.
     *
     * @param notifier The {@link ListenerSupport1.ListenerNotifier} that shall be used to notify the listener.
     * @param executor The {@link Executor} that will be used to notify the listeners.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D>      The type of the argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport1}.
     */
    public static <L, D> ListenerSupport1<L, D> createFor(
            final ListenerSupport1.ListenerNotifier<L, D> notifier,
            final Executor executor) {

        return new ListenerSupport1<>(notifier, executor);
    }

    /**
     * Creates a ListenerSupport for a callback method with two arguments. The notification will be done in the
     * thread from which the notifyListeners() method is called.
     *
     * @param notifier The {@link ListenerSupport2.ListenerNotifier} that shall be used to notify the listener.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport2}.
     */
    public static <L, D1, D2> ListenerSupport2<L, D1, D2> createFor(
            final ListenerSupport2.ListenerNotifier<L, D1, D2> notifier) {

        return createFor(notifier, currentThreadExecutor());
    }

    /**
     * Creates a ListenerSupport for a callback method with two arguments. The notification will be done using the
     * given {@link Executor}.
     *
     * @param notifier The {@link ListenerSupport2.ListenerNotifier} that shall be used to notify the listener.
     * @param executor The {@link Executor} that will be used to notify the listeners.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport2}.
     */
    public static <L, D1, D2> ListenerSupport2<L, D1, D2> createFor(
            final ListenerSupport2.ListenerNotifier<L, D1, D2> notifier,
            final Executor executor) {

        return new ListenerSupport2<>(notifier, executor);
    }

    /**
     * Creates a ListenerSupport for a callback method with three arguments. The notification will be done in the
     * thread from which the notifyListeners() method is called.
     *
     * @param notifier The {@link ListenerSupport3.ListenerNotifier} that shall be used to notify the listener.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @param <D3>     The type of the third argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport3}.
     */
    public static <L, D1, D2, D3> ListenerSupport3<L, D1, D2, D3> createFor(
            final ListenerSupport3.ListenerNotifier<L, D1, D2, D3> notifier) {

        return createFor(notifier, currentThreadExecutor());
    }

    /**
     * Creates a ListenerSupport for a callback method with three arguments. The notification will be done using the
     * given {@link Executor}.
     *
     * @param notifier The {@link ListenerSupport3.ListenerNotifier} that shall be used to notify the listener.
     * @param executor The {@link Executor} that will be used to notify the listeners.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @param <D3>     The type of the third argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport3}.
     */
    public static <L, D1, D2, D3> ListenerSupport3<L, D1, D2, D3> createFor(
            final ListenerSupport3.ListenerNotifier<L, D1, D2, D3> notifier,
            final Executor executor) {

        return new ListenerSupport3<>(notifier, executor);
    }

    /**
     * Creates a ListenerSupport for a callback method with four arguments. The notification will be done in the
     * thread from which the notifyListeners() method is called.
     *
     * @param notifier The {@link ListenerSupport4.ListenerNotifier} that shall be used to notify the listener.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @param <D3>     The type of the third argument that will be passed to the listener on notify.
     * @param <D4>     The type of the fourth argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport4}.
     */
    public static <L, D1, D2, D3, D4> ListenerSupport4<L, D1, D2, D3, D4> createFor(
            final ListenerSupport4.ListenerNotifier<L, D1, D2, D3, D4> notifier) {

        return createFor(notifier, currentThreadExecutor());
    }

    /**
     * Creates a ListenerSupport for a callback method with four arguments. The notification will be done using the
     * given {@link Executor}.
     *
     * @param notifier The {@link ListenerSupport4.ListenerNotifier} that shall be used to notify the listener.
     * @param executor The {@link Executor} that will be used to notify the listeners.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @param <D3>     The type of the third argument that will be passed to the listener on notify.
     * @param <D4>     The type of the fourth argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport4}.
     */
    public static <L, D1, D2, D3, D4> ListenerSupport4<L, D1, D2, D3, D4> createFor(
            final ListenerSupport4.ListenerNotifier<L, D1, D2, D3, D4> notifier,
            final Executor executor) {

        return new ListenerSupport4<>(notifier, executor);
    }

    /**
     * Creates a ListenerSupport for a callback method with five arguments. The notification will be done in the
     * thread from which the notifyListeners() method is called.
     *
     * @param notifier The {@link ListenerSupport5.ListenerNotifier} that shall be used to notify the listener.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @param <D3>     The type of the third argument that will be passed to the listener on notify.
     * @param <D4>     The type of the fourth argument that will be passed to the listener on notify.
     * @param <D5>     The type of the fifth argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport5}.
     */
    public static <L, D1, D2, D3, D4, D5> ListenerSupport5<L, D1, D2, D3, D4, D5> createFor(
            final ListenerSupport5.ListenerNotifier<L, D1, D2, D3, D4, D5> notifier) {

        return createFor(notifier, currentThreadExecutor());
    }

    /**
     * Creates a ListenerSupport for a callback method with five arguments. The notification will be done using the
     * given {@link Executor}.
     *
     * @param notifier The {@link ListenerSupport5.ListenerNotifier} that shall be used to notify the listener.
     * @param executor The {@link Executor} that will be used to notify the listeners.
     * @param <L>      The type of the listener that shall be notified.
     * @param <D1>     The type of the first argument that will be passed to the listener on notify.
     * @param <D2>     The type of the second argument that will be passed to the listener on notify.
     * @param <D3>     The type of the third argument that will be passed to the listener on notify.
     * @param <D4>     The type of the fourth argument that will be passed to the listener on notify.
     * @param <D5>     The type of the fifth argument that will be passed to the listener on notify.
     * @return The created {@link ListenerSupport5}.
     */
    public static <L, D1, D2, D3, D4, D5> ListenerSupport5<L, D1, D2, D3, D4, D5> createFor(
            final ListenerSupport5.ListenerNotifier<L, D1, D2, D3, D4, D5> notifier,
            final Executor executor) {

        return new ListenerSupport5<>(notifier, executor);
    }
}
