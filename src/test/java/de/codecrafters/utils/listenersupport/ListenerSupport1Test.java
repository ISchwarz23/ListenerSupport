package de.codecrafters.utils.listenersupport;

import de.codecrafters.utils.listenersupport.failure.FailureStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ListenerSupport1}.
 *
 * @author ISchwarz
 */
public class ListenerSupport1Test {

    private interface ListenerWithOneArg {
        void listenerMethod(String arg);
    }

    private ListenerSupport1<ListenerWithOneArg, String> cut;

    @Before
    public void setUp() throws Exception {
        cut = ListenerSupport.createFor(ListenerWithOneArg::listenerMethod);
    }

    @After
    public void tearDown() throws Exception {
        cut = null;
    }

    @Test
    public void shouldNotifyListener() throws Exception {
        // given
        ListenerWithOneArg listenerMock = mock(ListenerWithOneArg.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello");

        // then
        verify(listenerMock).listenerMethod("hello");
    }

    @Test
    public void shouldNotifyAllListeners() throws Exception {
        // given
        ListenerWithOneArg listenerMock1 = mock(ListenerWithOneArg.class);
        ListenerWithOneArg listenerMock2 = mock(ListenerWithOneArg.class);
        ListenerWithOneArg listenerMock3 = mock(ListenerWithOneArg.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello");

        // then
        verify(listenerMock1).listenerMethod("hello");
        verify(listenerMock2).listenerMethod("hello");
        verify(listenerMock3).listenerMethod("hello");
    }

    @Test
    public void shouldNotifyAllListenersIfOneListenerThrowsException() throws Exception {
        // given
        final ListenerWithOneArg listenerMock1 = mock(ListenerWithOneArg.class);
        final ListenerWithOneArg listenerMock2 = mock(ListenerWithOneArg.class);
        doThrow(new IllegalArgumentException()).when(listenerMock2).listenerMethod(anyString());
        final ListenerWithOneArg listenerMock3 = mock(ListenerWithOneArg.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello");

        // then
        verify(listenerMock1).listenerMethod("hello");
        verify(listenerMock2).listenerMethod("hello");
        verify(listenerMock3).listenerMethod("hello");
    }

    @Test
    public void shouldUseGivenExecutorForNotification() throws Exception {
        // given
        final Executor executorMock = mock(Executor.class);
        final ListenerSupport1<ListenerWithOneArg, String> cut =
                ListenerSupport.createFor(ListenerWithOneArg::listenerMethod, executorMock);

        final ListenerWithOneArg listenerMock = mock(ListenerWithOneArg.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello");

        // then
        verify(executorMock).execute(any(Runnable.class));
    }

    @Test
    public void shouldUseTheGivenFailureStrategy() throws Exception {
        // given
        final FailureStrategy<Object> failureStrategyMock = mock(FailureStrategy.class);
        cut.setFailureStrategy(failureStrategyMock);

        final ListenerWithOneArg listenerMock = mock(ListenerWithOneArg.class);
        doThrow(new IllegalArgumentException()).when(listenerMock).listenerMethod(anyString());
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello");

        // then
        verify(failureStrategyMock).onFailure(any(), any());
    }

}