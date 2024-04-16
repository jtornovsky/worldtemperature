package com.anodot.worldtemperature.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesLoader {

    private static final String APPLICATION_PROPERTIES_FILE = "application.properties";

    private PropertiesLoader() {
        // Private constructor to prevent instantiation
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            log.info("Loading properties from '{}' file", APPLICATION_PROPERTIES_FILE);
            InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES_FILE);
            properties.load(input);
        } catch (IOException e) {
            log.error("Failed to load properties {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return properties;
    }
}

