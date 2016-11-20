package de.codecrafters.listenersupport.failure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link FailureStrategy} implementation that will log thrown errors using Slf4j.
 *
 * @author ISchwarz
 */
public class Slf4jFailureStrategy implements FailureStrategy {

    public enum Level {
        ERROR,
        WARN,
        INFO,
        TRACE,
        DEBUG
    }

    private final Level logLevel;

    public Slf4jFailureStrategy(final Level logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void onFailure(final Object listener, final Throwable throwable) {
        final Logger logger = LoggerFactory.getLogger(listener.getClass());
        switch (logLevel) {
            case ERROR:
                logger.error("Error thrown during listener notification. ", throwable);
                break;
            case WARN:
                logger.warn("Error thrown during listener notification. ", throwable);
                break;
            case INFO:
                logger.info("Error thrown during listener notification. ", throwable);
                break;
            case TRACE:
                logger.trace("Error thrown during listener notification. ", throwable);
                break;
            case DEBUG:
                logger.debug("Error thrown during listener notification. ", throwable);
                break;
        }
    }

}
