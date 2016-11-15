package de.codecrafters.utils.listenersupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        cut.notifyListeners("Hello");

        // then
        verify(listenerMock).listenerMethod("Hello");
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
        cut.notifyListeners("Hello");

        // then
        verify(listenerMock1).listenerMethod("Hello");
        verify(listenerMock2).listenerMethod("Hello");
        verify(listenerMock3).listenerMethod("Hello");
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
        cut.notifyListeners("Hello");

        // then
        verify(listenerMock1).listenerMethod("Hello");
        verify(listenerMock2).listenerMethod("Hello");
        verify(listenerMock3).listenerMethod("Hello");
    }

}