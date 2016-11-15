package de.codecrafters.utils.listenersupport;

import java.util.concurrent.Executor;

/**
 * Created by Ingo on 12.11.2016.
 */
public final class ListenerSupport3<L, D1, D2, D3> extends ListenerSupportBase<L> {

    public interface ListenerNotifier<L, D1, D2, D3> {
        void notifyListener(L listener, D1 data1, D2 data2, D3 data3);
    }

    private final ListenerNotifier<L, D1, D2, D3> notifier;

    ListenerSupport3(final ListenerNotifier<L, D1, D2, D3> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    public void notifyListeners(final D1 data1, final D2 data2, final D3 data3) {
        getListeners().forEach(listener -> notifyListener(listener, data1, data2, data3));
    }

    private void notifyListener(L listener, D1 data1, D2 data2, D3 data3) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener, data1, data2, data3));
    }
}
