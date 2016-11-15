package de.codecrafters.utils.listenersupport.failure;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Ingo on 13.11.2016.
 */
public class JavaLoggingFailureStrategy implements FailureStrategy<Object> {

    @Override
    public void onFailure(Object listener, Throwable t) {
        Logger logger = Logger.getLogger(listener.getClass().getName());
        logger.log(Level.ALL, "Exception thrown by Listener", t);
    }
}
