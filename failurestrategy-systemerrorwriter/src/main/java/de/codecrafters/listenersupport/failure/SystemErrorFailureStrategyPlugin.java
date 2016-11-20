package de.codecrafters.listenersupport.failure;

/**
 * A {@link FailureStrategyPlugin} implementation that provides the {@link SystemErrorFailureStrategy}.
 *
 * @author ISchwarz
 */
public final class SystemErrorFailureStrategyPlugin implements FailureStrategyPlugin {

    @Override
    public String getName() {
        return SystemErrorFailureStrategy.class.getSimpleName();
    }

    @Override
    public FailureStrategy createNewInstance() {
        return new SystemErrorFailureStrategy();
    }
}
