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

    public static Executor createEventQueueExecutor() {
        return EventQueue::invokeLater;
    }

    public static Executor createCurrentThreadExecutor() {
        return Runnable::run;
    }

    public static Executor createConcurrentThreadExecutor(final int concurrentThreads) {
        return Executors.newFixedThreadPool(concurrentThreads);
    }

}
