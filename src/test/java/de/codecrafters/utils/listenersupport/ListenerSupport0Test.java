package de.codecrafters.utils.listenersupport;

import de.codecrafters.utils.listenersupport.failure.FailureStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ListenerSupport0}.
 *
 * @author ISchwarz
 */
public class ListenerSupport0Test {

    private interface ListenerWithoutArg {
        void listenerMethod();
    }

    private ListenerSupport0<ListenerWithoutArg> cut;

    @Before
    public void setUp() throws Exception {
        cut = ListenerSupport.createFor(ListenerWithoutArg::listenerMethod);
    }

    @After
    public void tearDown() throws Exception {
        cut = null;
    }

    @Test
    public void shouldNotifyListener() throws Exception {
        // given
        ListenerWithoutArg listenerMock = mock(ListenerWithoutArg.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners();

        // then
        verify(listenerMock).listenerMethod();
    }

    @Test
    public void shouldNotifyAllListeners() throws Exception {
        // given
        ListenerWithoutArg listenerMock1 = mock(ListenerWithoutArg.class);
        ListenerWithoutArg listenerMock2 = mock(ListenerWithoutArg.class);
        ListenerWithoutArg listenerMock3 = mock(ListenerWithoutArg.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners();

        // then
        verify(listenerMock1).listenerMethod();
        verify(listenerMock2).listenerMethod();
        verify(listenerMock3).listenerMethod();
    }

    @Test
    public void shouldNotifyAllListenersIfOneListenerThrowsException() throws Exception {
        // given
        final ListenerWithoutArg listenerMock1 = mock(ListenerWithoutArg.class);
        final ListenerWithoutArg listenerMock2 = mock(ListenerWithoutArg.class);
        doThrow(new IllegalArgumentException()).when(listenerMock2).listenerMethod();
        final ListenerWithoutArg listenerMock3 = mock(ListenerWithoutArg.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners();

        // then
        verify(listenerMock1).listenerMethod();
        verify(listenerMock2).listenerMethod();
        verify(listenerMock3).listenerMethod();
    }

    @Test
    public void shouldUseGivenExecutorForNotification() throws Exception {
        // given
        final Executor executorMock = mock(Executor.class);
        final ListenerSupport0<ListenerWithoutArg> cut =
                ListenerSupport.createFor(ListenerWithoutArg::listenerMethod, executorMock);

        final ListenerWithoutArg listenerMock = mock(ListenerWithoutArg.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners();

        // then
        verify(executorMock).execute(any(Runnable.class));
    }

    @Test
    public void shouldUseTheGivenFailureStrategy() throws Exception {
        // given
        final FailureStrategy<Object> failureStrategyMock = mock(FailureStrategy.class);
        cut.setFailureStrategy(failureStrategyMock);

        final ListenerWithoutArg listenerMock = mock(ListenerWithoutArg.class);
        doThrow(new IllegalArgumentException()).when(listenerMock).listenerMethod();
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners();

        // then
        verify(failureStrategyMock).onFailure(any(), any());
    }
}