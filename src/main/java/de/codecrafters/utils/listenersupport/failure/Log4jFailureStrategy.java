package de.codecrafters.utils.listenersupport.failure;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * A {@link FailureStrategy} implementation that will log exceptions using Log4j.
 *
 * @author ISchwarz
 */
class Log4jFailureStrategy implements FailureStrategy<Object> {

    private final Level logLevel;

    Log4jFailureStrategy(final Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void onFailure(final Object listener, final Throwable t) {
        LogManager.getLogger(listener.getClass()).log(logLevel, "Exception thrown by Listener", t);
    }
}
