package de.codecrafters.listenersupport.failure;

/**
 * A {@link FailureStrategy} implementation that will log thrown errors using Slf4j.
 *
 * @author ISchwarz
 */
public class Slf4jFailureStrategy implements FailureStrategy {

    @Override
    public void onFailure(final Object listener, final Throwable throwable) {

    }

}
