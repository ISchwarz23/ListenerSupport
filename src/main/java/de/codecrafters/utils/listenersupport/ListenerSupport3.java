package de.codecrafters.utils.listenersupport;

import java.util.concurrent.Executor;

/**
 * Created by Ingo on 12.11.2016.
 */
public final class ListenerSupport3<L, A1, A2, A3> extends ListenerSupportBase<L> {

    public interface ListenerNotifier<L, A1, A2, A3> {
        void notifyListener(L listener, A1 arg1, A2 arg2, A3 arg3);
    }

    private final ListenerNotifier<L, A1, A2, A3> notifier;

    ListenerSupport3(final ListenerNotifier<L, A1, A2, A3> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    public void notifyListeners(final A1 arg1, final A2 arg2, final A3 arg3) {
        getListeners().forEach(listener -> notifyListener(listener, arg1, arg2, arg3));
    }

    private void notifyListener(L listener, A1 arg1, A2 arg2, A3 arg3) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener, arg1, arg2, arg3));
    }
}
