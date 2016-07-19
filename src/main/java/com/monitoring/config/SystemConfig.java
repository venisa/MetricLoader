package com.monitoring.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.net.URL;

//TODO refactor
//TODO add unit tests

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.net.URL;

//TODO add unit tests
/**
 * Class to hold and fetch system configuration values.
 */
public class SystemConfig {

    /**
     * The resource path for local user override of application and default properties.
     */
    private static final String CONFIG_FILE_NAME = "/config.properties";

    static final CompositeConfiguration CONFIGURATION;
    private static Configuration userFileConfiguration;

    static {
        CONFIGURATION = new CompositeConfiguration();

        try {
            URL userConfigURL = SystemConfig.class.getResource(CONFIG_FILE_NAME);
            if (userConfigURL != null) {
                userFileConfiguration = new PropertiesConfiguration(userConfigURL);
                CONFIGURATION.addConfiguration(userFileConfiguration);
            }

            CONFIGURATION.setThrowExceptionOnMissing(true);
        } catch (ConfigurationException e) {
            //TODO Implement Logging
            System.out.println(e);
        }
    }

    public static String getStringProperty(String key) {
        return CONFIGURATION.getString(key);
    }
}
