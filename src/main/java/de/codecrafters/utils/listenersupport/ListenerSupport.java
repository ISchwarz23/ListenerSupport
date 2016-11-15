package de.codecrafters.utils.listenersupport;

import java.util.concurrent.Executor;

/**
 * Created by Ingo on 12.11.2016.
 */
public class ListenerSupport {

    private ListenerSupport() {
        //no instance
    }

    public static <L, D> ListenerSupport1<L, D> createFor(
            final ListenerSupport1.ListenerNotifier<L, D> notifier) {

        return new ListenerSupport1<>(notifier);
    }

    public static <L, D> ListenerSupport1<L, D> createFor(
            final ListenerSupport1.ListenerNotifier<L, D> notifier,
            final Executor executor) {

        return new ListenerSupport1<>(notifier, executor);
    }

    public static <L, D1, D2> ListenerSupport2<L, D1, D2> createFor(
            final ListenerSupport2.ListenerNotifier<L, D1, D2> notifier) {

        return new ListenerSupport2<>(notifier);
    }

    public static <L, D1, D2> ListenerSupport2<L, D1, D2> createFor(
            final ListenerSupport2.ListenerNotifier<L, D1, D2> notifier,
            final Executor executor) {

        return new ListenerSupport2<>(notifier, executor);
    }

    public static <L, D1, D2, D3> ListenerSupport3<L, D1, D2, D3> createFor(
            final ListenerSupport3.ListenerNotifier<L, D1, D2, D3> notifier) {

        return new ListenerSupport3<>(notifier);
    }

    public static <L, D1, D2, D3> ListenerSupport3<L, D1, D2, D3> createFor(
            final ListenerSupport3.ListenerNotifier<L, D1, D2, D3> notifier,
            final Executor executor) {

        return new ListenerSupport3<>(notifier, executor);
    }

}
