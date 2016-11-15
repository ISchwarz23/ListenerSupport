package de.codecrafters.utils.listenersupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ListenerSupport0}.
 *
 * @author ISchwarz
 */
public class ListenerSupport0Test {

    private interface ListenerWithOneArg {
        void listenerMethod();
    }

    private ListenerSupport0<ListenerWithOneArg> cut;

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
        cut.notifyListeners();

        // then
        verify(listenerMock).listenerMethod();
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
        cut.notifyListeners();

        // then
        verify(listenerMock1).listenerMethod();
        verify(listenerMock2).listenerMethod();
        verify(listenerMock3).listenerMethod();
    }

    @Test
    public void shouldNotifyAllListenersIfOneListenerThrowsException() throws Exception {
        // given
        final ListenerWithOneArg listenerMock1 = mock(ListenerWithOneArg.class);
        final ListenerWithOneArg listenerMock2 = mock(ListenerWithOneArg.class);
        doThrow(new IllegalArgumentException()).when(listenerMock2).listenerMethod();
        final ListenerWithOneArg listenerMock3 = mock(ListenerWithOneArg.class);
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

}