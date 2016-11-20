package de.codecrafters.listenersupport;

import de.codecrafters.listenersupport.failure.FailureStrategy;
import de.codecrafters.listenersupport.failure.FailureStrategyPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * Loader for all FailureStrategies in the classpath.
 *
 * @author ISchwarz
 */
public final class FailureStrategyLoader {

    private final static Logger LOGGER = LoggerFactory.getLogger(FailureStrategyLoader.class);
    private static final String FAILURE_STRATEGY_LOADER_PROPERTIES = "failurestrategyloader.properties";

    private static final Map<String, FailureStrategyPlugin> FAILURE_STRATEGY_PLUGINS = new HashMap<>();
    private static FailureStrategyPlugin currentPlugin = new NoOperationFailureStrategy();

    static {
        loadPlugins();
        setInitialPlugin();
    }

    private FailureStrategyLoader() {
        //no instance
    }

    private static void loadPlugins() {
        final ServiceLoader<FailureStrategyPlugin> serviceLoader = ServiceLoader.load(FailureStrategyPlugin.class);

        for (final FailureStrategyPlugin failureStrategyPlugin : serviceLoader) {
            addFailureStrategyPlugin(failureStrategyPlugin);
        }
    }

    private static void setInitialPlugin() {
        final FailureStrategyPlugin[] failureStrategyPlugins =
                FAILURE_STRATEGY_PLUGINS.values().toArray(new FailureStrategyPlugin[FAILURE_STRATEGY_PLUGINS.size()]);

        final String failureStrategyName = loadFailureStrategyNameFromProperties();
        boolean success = false;
        if (failureStrategyName != null) {
            success = setFailureStrategyByName(failureStrategyName);
        }

        if (!success && failureStrategyPlugins.length > 0) {
            currentPlugin = failureStrategyPlugins[0];
        }
        LOGGER.info("Using FailureStrategy '{}'", currentPlugin.getName());
    }

    private static String loadFailureStrategyNameFromProperties() {
        try {
            final Properties prop = new Properties();
            prop.load(new FileInputStream(FAILURE_STRATEGY_LOADER_PROPERTIES));
            if (prop.containsKey("strategyName")) {
                return prop.getProperty("strategyName");
            } else {
                LOGGER.info("No 'strategyName' defined in 'failurestrategyloader.properties' file.");
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                LOGGER.info("Cannot find FailureStrategy configuration file");
            } else {
                LOGGER.error("Error on reading properties file", e);
            }
        }
        return null;
    }

    public static FailureStrategy createNewFailureStrategy() {
        return currentPlugin.createNewInstance();
    }

    public static void addFailureStrategyPlugin(final FailureStrategyPlugin plugin) {
        FAILURE_STRATEGY_PLUGINS.put(plugin.getName(), plugin);
        LOGGER.info("Loaded '{}'", plugin.getName());
    }

    public static boolean setFailureStrategyByName(final String failureStrategyName) {
        if (FAILURE_STRATEGY_PLUGINS.containsKey(failureStrategyName)) {
            currentPlugin = FAILURE_STRATEGY_PLUGINS.get(failureStrategyName);
            LOGGER.info("Set '{}' as FailureStrategy", failureStrategyName);
            return true;
        }
        LOGGER.warn("Cannot set '{}' as FailureStrategy as it is unknown", failureStrategyName);
        return false;
    }

    private static final class NoOperationFailureStrategy implements FailureStrategyPlugin {

        @Override
        public String getName() {
            return NoOperationFailureStrategy.class.getSimpleName();
        }

        @Override
        public FailureStrategy createNewInstance() {
            return (listener, throwable) -> {
            };
        }
    }

    ;

}
