package de.codecrafters.listenersupport.failure;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A {@link FailureStrategy} implementation that logs the thrown error using the
 * Java {@link Logger}.
 *
 * @author ISchwarz
 */
public final class JavaLoggingFailureStrategy implements FailureStrategy<Object> {

    private final Level logLevel;

    /**
     * Creates a new {@link JavaLoggingFailureStrategy} that uses the given log {@link Level}.
     *
     * @param logLevel The log {@link Level} that shall be used.
     */
    public JavaLoggingFailureStrategy(final Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void onFailure(final Object listener, final Throwable t) {
        Logger.getLogger(listener.getClass().getName()).log(logLevel, "Exception thrown by Listener", t);
    }
}
