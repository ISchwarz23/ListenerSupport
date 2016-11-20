package de.codecrafters.listenersupport;

import de.codecrafters.listenersupport.failure.FailureStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ListenerSupport5}.
 *
 * @author ISchwarz
 */
public class ListenerSupport5Test {

    private interface ListenerWithFiveArgs {
        void listenerMethod(String arg1, Integer arg2, String arg3, Character arg4, Boolean arg5);
    }

    private ListenerSupport5<ListenerWithFiveArgs, String, Integer, String, Character, Boolean> cut;

    @Before
    public void setUp() throws Exception {
        cut = ListenerSupport.createFor(ListenerWithFiveArgs::listenerMethod);
    }

    @After
    public void tearDown() throws Exception {
        cut = null;
    }

    @Test
    public void shouldNotifyListener() throws Exception {
        // given
        ListenerWithFiveArgs listenerMock = mock(ListenerWithFiveArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world", '!', false);

        // then
        verify(listenerMock).listenerMethod("hello", 2, "world", '!', false);
    }

    @Test
    public void shouldNotifyAllListeners() throws Exception {
        // given
        ListenerWithFiveArgs listenerMock1 = mock(ListenerWithFiveArgs.class);
        ListenerWithFiveArgs listenerMock2 = mock(ListenerWithFiveArgs.class);
        ListenerWithFiveArgs listenerMock3 = mock(ListenerWithFiveArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2, "world", '!', false);

        // then
        verify(listenerMock1).listenerMethod("hello", 2, "world", '!', false);
        verify(listenerMock2).listenerMethod("hello", 2, "world", '!', false);
        verify(listenerMock3).listenerMethod("hello", 2, "world", '!', false);
    }

    @Test
    public void shouldNotifyAllListenersIfOneListenerThrowsException() throws Exception {
        // given
        final ListenerWithFiveArgs listenerMock1 = mock(ListenerWithFiveArgs.class);
        final ListenerWithFiveArgs listenerMock2 = mock(ListenerWithFiveArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock2)
                .listenerMethod(anyString(), anyInt(), anyString(), anyChar(), anyBoolean());
        final ListenerWithFiveArgs listenerMock3 = mock(ListenerWithFiveArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2, "world", '!', false);

        // then
        verify(listenerMock1).listenerMethod("hello", 2, "world", '!', false);
        verify(listenerMock2).listenerMethod("hello", 2, "world", '!', false);
        verify(listenerMock3).listenerMethod("hello", 2, "world", '!', false);
    }

    @Test
    public void shouldUseGivenExecutorForNotification() throws Exception {
        // given
        final Executor executorMock = mock(Executor.class);
        final ListenerSupport5<ListenerWithFiveArgs, String, Integer, String, Character, Boolean> cut =
                ListenerSupport.createFor(ListenerWithFiveArgs::listenerMethod, executorMock);

        final ListenerWithFiveArgs listenerMock = mock(ListenerWithFiveArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world", '!', false);

        // then
        verify(executorMock).execute(any(Runnable.class));
    }

    @Test
    public void shouldUseTheGivenFailureStrategy() throws Exception {
        // given
        final FailureStrategy failureStrategyMock = mock(FailureStrategy.class);
        cut.setFailureStrategy(failureStrategyMock);

        final ListenerWithFiveArgs listenerMock = mock(ListenerWithFiveArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock)
                .listenerMethod(anyString(), anyInt(), anyString(), anyChar(), anyBoolean());
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world", '!', false);

        // then
        verify(failureStrategyMock).onFailure(any(), any());
    }
}