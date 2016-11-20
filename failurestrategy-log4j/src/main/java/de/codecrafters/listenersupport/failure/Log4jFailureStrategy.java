package de.codecrafters.listenersupport.failure;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * A {@link FailureStrategy} implementation that will log thrown errors using Log4j.
 *
 * @author ISchwarz
 */
public final class Log4jFailureStrategy implements FailureStrategy {

    private final Level logLevel;

    /**
     * Creates a new {@link Log4jFailureStrategy} using the given log {@link Level}.
     *
     * @param logLevel The log {@link Level} that shall be used.
     */
    public Log4jFailureStrategy(final Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void onFailure(final Object listener, final Throwable t) {
        LogManager.getLogger(listener.getClass()).log(logLevel, "Exception thrown by Listener", t);
    }
}
