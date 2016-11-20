package de.codecrafters.listenersupport;

import de.codecrafters.listenersupport.failure.FailureStrategy;
import de.codecrafters.listenersupport.failure.FailureStrategyPlugin;
import de.codecrafters.listenersupport.failure.SystemErrorFailureStrategyPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Loader for all FailureStrategies in the classpath.
 *
 * @author ISchwarz
 */
public final class FailureStrategyLoader {

    private static final Map<String, FailureStrategyPlugin> FAILURE_STRATEGY_PLUGINS = new HashMap<>();
    private static FailureStrategyPlugin currentPlugin;

    static {
        loadPlugins();
        setInitialPlugin();
    }

    private FailureStrategyLoader() {
        //no instance
    }

    private static void loadPlugins() {
        for (final FailureStrategyPlugin failureStrategyPlugin : ServiceLoader.load(FailureStrategyPlugin.class)) {
            addFailureStrategyPlugin(failureStrategyPlugin);
        }
    }

    private static void setInitialPlugin() {
        final FailureStrategyPlugin[] failureStrategyPlugins =
                FAILURE_STRATEGY_PLUGINS.values().toArray(new FailureStrategyPlugin[FAILURE_STRATEGY_PLUGINS.size()]);

        // TODO load props and apply configured

        if (failureStrategyPlugins.length > 0) {
            currentPlugin = failureStrategyPlugins[0];
        } else {
            currentPlugin = new SystemErrorFailureStrategyPlugin();
        }
    }

    public static FailureStrategy createNewFailureStrategy() {
        return currentPlugin.createNewInstance();
    }

    public static void addFailureStrategyPlugin(final FailureStrategyPlugin plugin) {
        FAILURE_STRATEGY_PLUGINS.put(plugin.getName(), plugin);
    }

    public static boolean setFailureStrategyByName(final String failureStrategyName) {
        if (FAILURE_STRATEGY_PLUGINS.containsKey(failureStrategyName)) {
            currentPlugin = FAILURE_STRATEGY_PLUGINS.get(failureStrategyName);
            return true;
        }
        return false;
    }

}
