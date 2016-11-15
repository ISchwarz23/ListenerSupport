package de.codecrafters.utils.listenersupport;

import java.util.concurrent.Executor;

/**
 * A ListenerSupport factory that supports listeners with methods up to three arguments.
 *
 * @author ISchwarz
 */
public class ListenerSupport {

    private ListenerSupport() {
        //no instance
    }

    public static <L> ListenerSupport0<L> createFor(
            final ListenerSupport0.ListenerNotifier<L> notifier) {

        return createFor(notifier, NotifyExecutors.createCurrentThreadExecutor());
    }

    public static <L> ListenerSupport0<L> createFor(
            final ListenerSupport0.ListenerNotifier<L> notifier,
            final Executor executor) {

        return new ListenerSupport0<>(notifier, executor);
    }

    public static <L, D> ListenerSupport1<L, D> createFor(
            final ListenerSupport1.ListenerNotifier<L, D> notifier) {

        return createFor(notifier, NotifyExecutors.createCurrentThreadExecutor());
    }

    public static <L, D> ListenerSupport1<L, D> createFor(
            final ListenerSupport1.ListenerNotifier<L, D> notifier,
            final Executor executor) {

        return new ListenerSupport1<>(notifier, executor);
    }

    public static <L, D1, D2> ListenerSupport2<L, D1, D2> createFor(
            final ListenerSupport2.ListenerNotifier<L, D1, D2> notifier) {

        return createFor(notifier, NotifyExecutors.createCurrentThreadExecutor());
    }

    public static <L, D1, D2> ListenerSupport2<L, D1, D2> createFor(
            final ListenerSupport2.ListenerNotifier<L, D1, D2> notifier,
            final Executor executor) {

        return new ListenerSupport2<>(notifier, executor);
    }

    public static <L, D1, D2, D3> ListenerSupport3<L, D1, D2, D3> createFor(
            final ListenerSupport3.ListenerNotifier<L, D1, D2, D3> notifier) {

        return createFor(notifier, NotifyExecutors.createCurrentThreadExecutor());
    }

    public static <L, D1, D2, D3> ListenerSupport3<L, D1, D2, D3> createFor(
            final ListenerSupport3.ListenerNotifier<L, D1, D2, D3> notifier,
            final Executor executor) {

        return new ListenerSupport3<>(notifier, executor);
    }

}
