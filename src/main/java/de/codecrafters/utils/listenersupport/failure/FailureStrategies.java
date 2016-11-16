package de.codecrafters.utils.listenersupport.failure;

import org.apache.logging.log4j.Level;

/**
 * Created by Ingo on 16.11.2016.
 */
public final class FailureStrategies {

    private FailureStrategies() {
        //no instance
    }

    public FailureStrategy<Object> createLog4jFailureStrategy() {
        return new Log4jFailureStrategy(Level.ERROR);
    }

    public FailureStrategy<Object> createLog4jFailureStrategy(final Level logLevel) {
        return new Log4jFailureStrategy(logLevel);
    }

}
