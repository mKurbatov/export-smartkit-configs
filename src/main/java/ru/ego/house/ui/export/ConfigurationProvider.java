package ru.ego.house.ui.export;

import static org.apache.commons.lang3.StringUtils.*;

import javax.annotation.Nonnull;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mkurbatov
 */
/*package*/ class ConfigurationProvider {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationProvider.class);

    private static final String PROJECT_PROPERTIES = "project.properties";

    private static final ConfigurationProvider INSTANCE = new ConfigurationProvider();

    private final CompositeConfiguration configuration;

    @Nonnull
    public static ConfigurationProvider get() {
        return INSTANCE;
    }

    @Nonnull
    public String applicationUrl() {
        return requiredProperty("smart-kit.url");
    }

    @Nonnull
    public String login() {
        return requiredProperty("smart-kit.login");
    }

    @Nonnull
    public String password() {
        return requiredProperty("smart-kit.password");
    }

    @Nonnull
    private String requiredProperty(@Nonnull String propertyName) {
        String value = this.configuration.getString(propertyName);
        return stripEnd(value, "/");
    }

    private ConfigurationProvider() {
        this.configuration = new CompositeConfiguration();
        this.configuration.addConfiguration(new SystemConfiguration());
        try {
            this.configuration.addConfiguration(new PropertiesConfiguration(PROJECT_PROPERTIES));
        } catch (ConfigurationException ex) {
            logger.error("Не найден файл конфигурации {}: {}", PROJECT_PROPERTIES, ex.getLocalizedMessage());
            throw new IllegalStateException("Не найден файл конфигурации " + PROJECT_PROPERTIES, ex);
        }
    }

}
