package de.codecrafters.listenersupport.failure;

import org.apache.logging.log4j.Level;

/**
 * A {@link FailureStrategyPlugin} implementation that provides the {@link Log4jFailureStrategy}.
 *
 * @author ISchwarz
 */
public final class Log4jFailureStrategyPlugin implements FailureStrategyPlugin {

    @Override
    public String getName() {
        return Log4jFailureStrategy.class.getSimpleName();
    }

    @Override
    public FailureStrategy createNewInstance() {
        return new Log4jFailureStrategy(Level.ERROR);
    }
}
