package de.codecrafters.utils.listenersupport;

import de.codecrafters.utils.listenersupport.failure.FailureStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ListenerSupport3}.
 *
 * @author ISchwarz
 */
public class ListenerSupport3Test {

    private interface ListenerWithThreeArgs {
        void listenerMethod(String arg1, Integer arg2, String arg3);
    }

    private ListenerSupport3<ListenerWithThreeArgs, String, Integer, String> cut;

    @Before
    public void setUp() throws Exception {
        cut = ListenerSupport.createFor(ListenerWithThreeArgs::listenerMethod);
    }

    @After
    public void tearDown() throws Exception {
        cut = null;
    }

    @Test
    public void shouldNotifyListener() throws Exception {
        // given
        ListenerWithThreeArgs listenerMock = mock(ListenerWithThreeArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world");

        // then
        verify(listenerMock).listenerMethod("hello", 2, "world");
    }

    @Test
    public void shouldNotifyAllListeners() throws Exception {
        // given
        ListenerWithThreeArgs listenerMock1 = mock(ListenerWithThreeArgs.class);
        ListenerWithThreeArgs listenerMock2 = mock(ListenerWithThreeArgs.class);
        ListenerWithThreeArgs listenerMock3 = mock(ListenerWithThreeArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2, "world");

        // then
        verify(listenerMock1).listenerMethod("hello", 2, "world");
        verify(listenerMock2).listenerMethod("hello", 2, "world");
        verify(listenerMock3).listenerMethod("hello", 2, "world");
    }

    @Test
    public void shouldNotifyAllListenersIfOneListenerThrowsException() throws Exception {
        // given
        final ListenerWithThreeArgs listenerMock1 = mock(ListenerWithThreeArgs.class);
        final ListenerWithThreeArgs listenerMock2 = mock(ListenerWithThreeArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock2).listenerMethod(anyString(), anyInt(), anyString());
        final ListenerWithThreeArgs listenerMock3 = mock(ListenerWithThreeArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2, "world");

        // then
        verify(listenerMock1).listenerMethod("hello", 2, "world");
        verify(listenerMock2).listenerMethod("hello", 2, "world");
        verify(listenerMock3).listenerMethod("hello", 2, "world");
    }

    @Test
    public void shouldUseGivenExecutorForNotification() throws Exception {
        // given
        final Executor executorMock = mock(Executor.class);
        final ListenerSupport3<ListenerWithThreeArgs, String, Integer, String> cut =
                ListenerSupport.createFor(ListenerWithThreeArgs::listenerMethod, executorMock);

        final ListenerWithThreeArgs listenerMock = mock(ListenerWithThreeArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world");

        // then
        verify(executorMock).execute(any(Runnable.class));
    }

    @Test
    public void shouldUseTheGivenFailureStrategy() throws Exception {
        // given
        final FailureStrategy<Object> failureStrategyMock = mock(FailureStrategy.class);
        cut.setFailureStrategy(failureStrategyMock);

        final ListenerWithThreeArgs listenerMock = mock(ListenerWithThreeArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock).listenerMethod(anyString(), anyInt(), anyString());
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2, "world");

        // then
        verify(failureStrategyMock).onFailure(any(), any());
    }
}