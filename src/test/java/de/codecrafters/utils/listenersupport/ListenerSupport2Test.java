package de.codecrafters.utils.listenersupport;

import de.codecrafters.utils.listenersupport.failure.FailureStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ListenerSupport2}.
 *
 * @author ISchwarz
 */
public class ListenerSupport2Test {

    private interface ListenerWithTwoArgs {
        void listenerMethod(String arg1, Integer arg2);
    }

    private ListenerSupport2<ListenerWithTwoArgs, String, Integer> cut;

    @Before
    public void setUp() throws Exception {
        cut = ListenerSupport.createFor(ListenerWithTwoArgs::listenerMethod);
    }

    @After
    public void tearDown() throws Exception {
        cut = null;
    }

    @Test
    public void shouldNotifyListener() throws Exception {
        // given
        ListenerWithTwoArgs listenerMock = mock(ListenerWithTwoArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2);

        // then
        verify(listenerMock).listenerMethod("hello", 2);
    }

    @Test
    public void shouldNotifyAllListeners() throws Exception {
        // given
        ListenerWithTwoArgs listenerMock1 = mock(ListenerWithTwoArgs.class);
        ListenerWithTwoArgs listenerMock2 = mock(ListenerWithTwoArgs.class);
        ListenerWithTwoArgs listenerMock3 = mock(ListenerWithTwoArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2);

        // then
        verify(listenerMock1).listenerMethod("hello", 2);
        verify(listenerMock2).listenerMethod("hello", 2);
        verify(listenerMock3).listenerMethod("hello", 2);
    }

    @Test
    public void shouldNotifyAllListenersIfOneListenerThrowsException() throws Exception {
        // given
        final ListenerWithTwoArgs listenerMock1 = mock(ListenerWithTwoArgs.class);
        final ListenerWithTwoArgs listenerMock2 = mock(ListenerWithTwoArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock2).listenerMethod(anyString(), anyInt());
        final ListenerWithTwoArgs listenerMock3 = mock(ListenerWithTwoArgs.class);
        cut.addListener(listenerMock1);
        cut.addListener(listenerMock2);
        cut.addListener(listenerMock3);

        // when
        cut.notifyListeners("hello", 2);

        // then
        verify(listenerMock1).listenerMethod("hello", 2);
        verify(listenerMock2).listenerMethod("hello", 2);
        verify(listenerMock3).listenerMethod("hello", 2);
    }

    @Test
    public void shouldUseGivenExecutorForNotification() throws Exception {
        // given
        final Executor executorMock = mock(Executor.class);
        final ListenerSupport2<ListenerWithTwoArgs, String, Integer> cut =
                ListenerSupport.createFor(ListenerWithTwoArgs::listenerMethod, executorMock);

        final ListenerWithTwoArgs listenerMock = mock(ListenerWithTwoArgs.class);
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2);

        // then
        verify(executorMock).execute(any(Runnable.class));
    }

    @Test
    public void shouldUseTheGivenFailureStrategy() throws Exception {
        // given
        final FailureStrategy<Object> failureStrategyMock = mock(FailureStrategy.class);
        cut.setFailureStrategy(failureStrategyMock);

        final ListenerWithTwoArgs listenerMock = mock(ListenerWithTwoArgs.class);
        doThrow(new IllegalArgumentException()).when(listenerMock).listenerMethod(anyString(), anyInt());
        cut.addListener(listenerMock);

        // when
        cut.notifyListeners("hello", 2);

        // then
        verify(failureStrategyMock).onFailure(any(), any());
    }
}