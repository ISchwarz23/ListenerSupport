package de.codecrafters.utils.listenersupport.failure;


/**
 * A {@link FailureStrategy} implementation that will log thrown errors using Log4j.
 *
 * @author ISchwarz
 */
final class SystemErrorFailureStrategy implements FailureStrategy<Object> {

    /**
     * Creates a new {@link SystemErrorFailureStrategy} that will write the caught {@link Throwable}s using
     * {@code System.err::println}.
     */
    SystemErrorFailureStrategy() {
    }

    @Override
    public void onFailure(final Object listener, final Throwable t) {
        System.err.println("Exception thrown by Listener '" + listener.getClass() + "':");
        t.printStackTrace(System.err);
    }
}
