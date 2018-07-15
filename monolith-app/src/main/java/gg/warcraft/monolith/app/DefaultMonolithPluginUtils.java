package gg.warcraft.monolith.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import gg.warcraft.monolith.api.MonolithPluginUtils;
import gg.warcraft.monolith.api.config.service.ConfigurationCommandService;
import gg.warcraft.monolith.api.config.service.ConfigurationQueryService;

import java.io.IOException;
import java.util.logging.Logger;

public class DefaultMonolithPluginUtils implements MonolithPluginUtils {
    private final ConfigurationQueryService configurationQueryService;
    private final ConfigurationCommandService configurationCommandService;
    private final Logger logger;
    private final ObjectMapper yamlMapper;

    @Inject
    public DefaultMonolithPluginUtils(ConfigurationQueryService configurationQueryService,
                                      ConfigurationCommandService configurationCommandService,
                                      Logger logger, ObjectMapper yamlMapper) {
        this.configurationQueryService = configurationQueryService;
        this.configurationCommandService = configurationCommandService;
        this.logger = logger;
        this.yamlMapper = yamlMapper;
    }

    @Override
    public <T> T loadLocalConfiguration(String configurationYaml, Class<T> configurationClass) {
        try {
            return yamlMapper.readValue(configurationYaml, configurationClass);
        } catch (IOException ex) {
            logger.warning("Exception loading local " + configurationClass.getSimpleName() + ": " + ex.getMessage());
            return null;
        }
    }

    @Override
    public <T> T loadRemoteConfiguration(String configurationFileName, Class<T> configurationClass) {
        T configuration = configurationQueryService.getConfiguration(configurationClass);
        if (configuration == null) {
            logger.info("Remote " + configurationClass.getSimpleName() + " missing from cache, attempting to load...");
            try {
                configurationCommandService.reloadConfiguration(configurationFileName, configurationClass);
                configuration = configurationQueryService.getConfiguration(configurationClass);
                logger.info("Successfully loaded remote " + configurationClass.getSimpleName());
            } catch (IOException ex) {
                logger.warning("Exception while loading remote " + configurationClass.getSimpleName() + ": " + ex.getMessage());
                return null;
            }
        }
        return configuration;
    }

    @Override
    public <T> T loadConfiguration(String configurationType, String configurationFileName, String configurationYaml,
                                   Class<T> configurationClass) {
        switch (configurationType) {
            case "REMOTE":
                T configuration = loadRemoteConfiguration(configurationFileName, configurationClass);
                if (configuration != null) {
                    return configuration;
                } else {
                    logger.warning("Failed to load remote " + configurationClass.getSimpleName() + ", falling back to LOCAL.");
                }
            case "LOCAL":
                return loadLocalConfiguration(configurationYaml, configurationClass);
            default:
                logger.warning("Illegal configurationType " + configurationType + " in " +
                        configurationClass.getSimpleName() + ", falling back to LOCAL.");
                return loadLocalConfiguration(configurationYaml, configurationClass);
        }
    }
}
