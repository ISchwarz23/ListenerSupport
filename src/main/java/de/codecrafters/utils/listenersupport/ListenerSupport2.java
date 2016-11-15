package de.codecrafters.utils.listenersupport;

import java.util.concurrent.Executor;

/**
 * Created by Ingo on 12.11.2016.
 */
public class ListenerSupport2<L, D1, D2> extends ListenerSupportBase<L> {

    public interface ListenerNotifier<L, D1, D2> {
        void notifyListener(L listener, D1 data1, D2 data2);
    }

    private final ListenerNotifier<L, D1, D2> notifier;

    ListenerSupport2(final ListenerNotifier<L, D1, D2> notifier) {
        this(notifier, NotifyExecutors.createCurrentThreadExecutor());
    }

    ListenerSupport2(final ListenerNotifier<L, D1, D2> notifier, final Executor notifyExecutor) {
        super(notifyExecutor);
        this.notifier = notifier;
    }

    public void notifyListeners(final D1 data1, D2 data2) {
        getListeners().forEach(listener -> notifyListener(listener, data1, data2));
    }

    private void notifyListener(L listener, D1 data1, D2 data2) {
        notifyListenerInExecutor(listener, () -> notifier.notifyListener(listener, data1, data2));
    }
}
