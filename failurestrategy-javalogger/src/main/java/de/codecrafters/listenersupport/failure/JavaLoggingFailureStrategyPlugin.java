package de.codecrafters.listenersupport.failure;

import java.util.logging.Level;

/**
 * A {@link FailureStrategyPlugin} implementation that provides the {@link JavaLoggingFailureStrategy}.
 *
 * @author ISchwarz
 */
public final class JavaLoggingFailureStrategyPlugin implements FailureStrategyPlugin {

    @Override
    public String getName() {
        return JavaLoggingFailureStrategy.class.getSimpleName();
    }

    @Override
    public FailureStrategy createNewInstance() {
        return new JavaLoggingFailureStrategy(Level.FINEST);
    }
}
