package de.codecrafters.utils.listenersupport;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A collection of predefined {@link Executor}s that can be used for listener notification.
 *
 * @author ISchwarz
 */
public final class NotifyExecutor {

    private NotifyExecutor() {
        //no instance
    }

    /**
     * Creates a new {@link Executor} that will run all actions using the {@link EventQueue}.
     *
     * @return The created {@link Executor} using the {@link EventQueue}.
     */
    public static Executor uiThreadExecutor() {
        return EventQueue::invokeLater;
    }

    /**
     * Creates a new {@link Executor} that will run all actions in the current {@link Thread}.
     *
     * @return The created {@link Executor} using the current {@link Thread}.
     */
    public static Executor currentThreadExecutor() {
        return Runnable::run;
    }

    /**
     * Creates a new {@link Executor} that will manage a separate thread that will be used to execute
     * the actions.
     *
     * @return The created {@link Executor} using a separate {@link Thread}.
     */
    public static Executor separateThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    /**
     * Creates a new {@link Executor} that will manage a pool of threads that will be used to execute
     * the actions. The size of the thread pool can be defined by the parameter.
     *
     * @param numberOfThreads The size of the thread pool.
     * @return The created {@link Executor} using a ThreadPool.
     */
    public static Executor separateThreadsExecutor(final int numberOfThreads) {
        return Executors.newFixedThreadPool(numberOfThreads);
    }

}
