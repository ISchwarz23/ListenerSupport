package de.codecrafters.listenersupport.failure;

/**
 * A collection of predefined {@link FailureStrategy}s.
 *
 * @author ISchwarz
 */
public final class FailureStrategies {

    private FailureStrategies() {
        //no instance
    }

    /**
     * Creates a new {@link FailureStrategy} that uses the System.err writer to print the caught {@link Throwable}s.
     *
     * @return The created {@link FailureStrategy}.
     */
    public static FailureStrategy<Object> newSystemErrorWriter() {
        return new SystemErrorFailureStrategy();
    }

    /**
     * Creates a new {@link FailureStrategy} that uses the Log4j {@link org.apache.logging.log4j.Logger} to log the
     * thrown errors in {@code ERROR} level.
     *
     * @return The created {@link FailureStrategy}.
     */
    public static FailureStrategy<Object> newLog4jLogger() {
        return newLog4jLogger(org.apache.logging.log4j.Level.ERROR);
    }

    /**
     * Creates a new {@link FailureStrategy} that uses the Log4j {@link org.apache.logging.log4j.Logger} to log the
     * thrown errors in the given log level.
     *
     * @return The created {@link FailureStrategy}.
     */
    public static FailureStrategy<Object> newLog4jLogger(final org.apache.logging.log4j.Level logLevel) {
        return new Log4jFailureStrategy(logLevel);
    }

    /**
     * Creates a new {@link FailureStrategy} that uses the Java {@link java.util.logging.Logger} to log the
     * thrown errors in the {@code FINEST} log level.
     *
     * @return The created {@link FailureStrategy}.
     */
    public static FailureStrategy<Object> newJavaLogger() {
        return newJavaLogger(java.util.logging.Level.FINEST);
    }

    /**
     * Creates a new {@link FailureStrategy} that uses the Java {@link java.util.logging.Logger} to log the
     * thrown errors in the given log level.
     *
     * @return The created {@link FailureStrategy}.
     */
    public static FailureStrategy<Object> newJavaLogger(final java.util.logging.Level logLevel) {
        return new JavaLoggingFailureStrategy(logLevel);
    }
}
