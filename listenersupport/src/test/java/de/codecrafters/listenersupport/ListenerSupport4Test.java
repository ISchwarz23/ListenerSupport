package de.codecrafters.listenersupport;

import de.codecrafters.listenersupport.failure.FailureStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ListenerSupport4}.
 *
 * @author ISchwarz
 */
public class ListenerSupport4Test {

    private interface ListenerWithFourArgs {
        void listenerMethod(String arg1, Integer arg2, String arg3, Character arg4);
    }

    private ListenerSupport4<ListenerWithFourArgs, String, Integer, String, Character> cut;

    @Before
    public void setUp() throws Exception {
        cut = ListenerSupport.createFor(ListenerWithFourArgs::listenerMethod);
    }

    @After
    public void tearDown() throws Exception {
        cut = null;
    }

    @Test
    public void shouldNotifyListener() throws Exception {
        // given
        ListenerWithFourArgs listenerMock = mock(ListenerWithFourArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world", '!');

        // then
        verify(listenerMock).listenerMethod("hello", 2, "world", '!');
    }

    @Test
    public void shouldNotifyAllListeners() throws Exception {
        // given
        ListenerWithFourArgs listenerMock1 = mock(ListenerWithFourArgs.class);
        ListenerWithFourArgs listenerMock2 = mock(ListenerWithFourArgs.class);
        ListenerWithFourArgs listenerMock3 = mock(ListenerWithFourArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2, "world", '!');

        // then
        verify(listenerMock1).listenerMethod("hello", 2, "world", '!');
        verify(listenerMock2).listenerMethod("hello", 2, "world", '!');
        verify(listenerMock3).listenerMethod("hello", 2, "world", '!');
    }

    @Test
    public void shouldNotifyAllListenersIfOneListenerThrowsException() throws Exception {
        // given
        final ListenerWithFourArgs listenerMock1 = mock(ListenerWithFourArgs.class);
        final ListenerWithFourArgs listenerMock2 = mock(ListenerWithFourArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock2)
                .listenerMethod(anyString(), anyInt(), anyString(), anyChar());
        final ListenerWithFourArgs listenerMock3 = mock(ListenerWithFourArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2, "world", '!');

        // then
        verify(listenerMock1).listenerMethod("hello", 2, "world", '!');
        verify(listenerMock2).listenerMethod("hello", 2, "world", '!');
        verify(listenerMock3).listenerMethod("hello", 2, "world", '!');
    }

    @Test
    public void shouldUseGivenExecutorForNotification() throws Exception {
        // given
        final Executor executorMock = mock(Executor.class);
        final ListenerSupport4<ListenerWithFourArgs, String, Integer, String, Character> cut =
                ListenerSupport.createFor(ListenerWithFourArgs::listenerMethod, executorMock);

        final ListenerWithFourArgs listenerMock = mock(ListenerWithFourArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world", '!');

        // then
        verify(executorMock).execute(any(Runnable.class));
    }

    @Test
    public void shouldUseTheGivenFailureStrategy() throws Exception {
        // given
        final FailureStrategy failureStrategyMock = mock(FailureStrategy.class);
        cut.setFailureStrategy(failureStrategyMock);

        final ListenerWithFourArgs listenerMock = mock(ListenerWithFourArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock)
                .listenerMethod(anyString(), anyInt(), anyString(), anyChar());
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world", '!');

        // then
        verify(failureStrategyMock).onFailure(any(), any());
    }
}