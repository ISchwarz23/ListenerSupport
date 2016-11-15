package de.codecrafters.utils.listenersupport.failure;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A {@link FailureStrategy} implementation that will log exceptions using Log4j.
 *
 * @author ISchwarz
 */
public class Log4jFailureStrategy implements FailureStrategy<Object> {

    @Override
    public void onFailure(Object listener, Throwable t) {
        final Logger logger = LogManager.getLogger(listener.getClass());
        logger.error("Exception thrown by Listener", t);
    }
}
