package com.anodot.worldtemperature.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertiesLoader {

    private static final String APPLICATION_PROPERTIES_FILE = "application.properties";

    private PropertiesLoader() {
        // Private constructor to prevent instantiation
    }

    public static Properties loadProperties() {
        log.info("Loading properties from '{}' file", APPLICATION_PROPERTIES_FILE);
        return loadProperties(APPLICATION_PROPERTIES_FILE);
    }

    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            log.error("Failed to load properties {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return properties;
    }
}

