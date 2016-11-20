package de.codecrafters.listenersupport.failure;

/**
 * A {@link FailureStrategyPlugin} implementation that provides the {@link Slf4jFailureStrategy}.
 *
 * @author ISchwarz
 */
public class Slf4jFailureStrategyPlugin implements FailureStrategyPlugin {

    @Override
    public String getName() {
        return Slf4jFailureStrategy.class.getSimpleName();
    }

    @Override
    public FailureStrategy createNewInstance() {
        return new Slf4jFailureStrategy(Slf4jFailureStrategy.Level.ERROR);
    }
}
