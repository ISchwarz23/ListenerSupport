package de.codecrafters.listenersupport.failure;


/**
 * A {@link FailureStrategy} implementation that will log thrown errors using Log4j.
 *
 * @author ISchwarz
 */
public final class SystemErrorFailureStrategy implements FailureStrategy {

    /**
     * Creates a new {@link SystemErrorFailureStrategy} that will write the caught {@link Throwable}s using
     * {@code System.err::println}.
     */
    public SystemErrorFailureStrategy() {
    }

    @Override
    public void onFailure(final Object listener, final Throwable t) {
        System.err.println("Exception thrown by Listener '" + listener.getClass() + "':");
        t.printStackTrace(System.err);
    }
}
