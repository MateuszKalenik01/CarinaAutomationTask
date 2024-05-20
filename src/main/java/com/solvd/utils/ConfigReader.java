package com.solvd.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigReader.class);

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                LOGGER.error("Sorry, unable to find config.properties");
            } else {
                properties.load(input);
                LOGGER.info("Configuration properties loaded successfully.");
            }
        } catch (IOException ex) {
            LOGGER.error("Error loading configuration properties", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
