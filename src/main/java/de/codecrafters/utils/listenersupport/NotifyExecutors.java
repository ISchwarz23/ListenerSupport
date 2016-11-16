package de.codecrafters.utils.listenersupport;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A collection of predefined {@link Executor}s that can be used for listener notification.
 *
 * @author ISchwarz
 */
public final class NotifyExecutors {

    private NotifyExecutors() {
        //no instance
    }

    /**
     * Creates a new {@link Executor} that will run all actions using the {@link EventQueue}.
     *
     * @return The created EventQueue-{@link Executor}.
     */
    public static Executor createEventQueueExecutor() {
        return EventQueue::invokeLater;
    }

    /**
     * Creates a new {@link Executor} that will run all actions in the current Thread.
     *
     * @return The created CurrentThread-{@link Executor}.
     */
    public static Executor createCurrentThreadExecutor() {
        return Runnable::run;
    }

    /**
     * Creates a new {@link Executor} that will manage a pool of threads that will be used to execute
     * the actions. The size of the thread pool can be defined by the parameter.
     *
     * @param concurrentThreads The size of the thread pool.
     * @return The created ConcurrentThread-{@link Executor}.
     */
    public static Executor createConcurrentThreadExecutor(final int concurrentThreads) {
        return Executors.newFixedThreadPool(concurrentThreads);
    }

}
