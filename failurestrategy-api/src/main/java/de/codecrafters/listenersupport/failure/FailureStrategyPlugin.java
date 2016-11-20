package de.codecrafters.listenersupport.failure;

/**
 * Interface of a Plugin that provides a {@link FailureStrategy}.
 *
 * @author ISchwarz
 */
public interface FailureStrategyPlugin {

    /**
     * Gives the name of the managed {@link FailureStrategy}.
     *
     * @return The name of the managed {@link FailureStrategy}.
     */
    String getName();

    /**
     * Creates a new instance of the managed {@link FailureStrategy}.
     *
     * @return The newly created instance of the managed {@link FailureStrategy}.
     */
    FailureStrategy<?> createNewInstance();

}
