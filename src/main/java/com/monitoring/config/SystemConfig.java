package com.monitoring.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.net.URL;

//TODO refactor
//TODO add unit tests

/**
 * Created by venisac
 */
public class SystemConfig {

    /**
     * The resource path for local user override of application and default properties.
     */
    private static final String CONFIG_FILE_NAME = "/config.properties";

    /**
     * The composite configuration containing the layered properties and values.
     */
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

    /**
     * Get a package scoped variable name.
     *
     * @param suffix  The variable name of the configuration variable without the package prefix
     * @return variable name
     */
    public static String getPackageVariableName(String suffix) {
        String packageName = getStringProperty("package_name");
        return packageName + "__" + suffix;
    }

    /**
     * Get property value for a key.
     *
     * @param key  The key for which value needs to be fetched, not null
     *
     * @return requestedPropertyValue The value for the requested key
     */
    public static String getStringProperty(String key) {
        return CONFIGURATION.getString(key);
    }

    /**
     * Get property value for a key.
     *
     * @param key  The key for which value needs to be fetched, not null
     *
     * @return requestedPropertyValue The value for the requested key
     */
    public static int getIntProperty(String key) {
        return CONFIGURATION.getInt(key);
    }

    /**
     * Get property value for a key.
     *
     * @param key  The key for which value needs to be fetched, not null
     *
     * @return requestedPropertyValue The value for the requested key, false if null
     */
    public static boolean getBooleanProperty(String key) {
        return CONFIGURATION.getBoolean(key);
    }

    /**
     * Get property value for a key.
     *
     * @param key  The key for which value needs to be fetched, not null
     *
     * @return The value for the requested key
     */
    public static float getFloatProperty(String key) {
        return CONFIGURATION.getFloat(key);
    }

    /**
     * Get property value for a key.
     *
     * @param key  The key for which value needs to be fetched, not null
     *
     * @return The value for the requested key
     */
    public static double getDoubleProperty( String key) {
        return CONFIGURATION.getDouble(key);
    }

    /**
     * Get property value for a key.
     *
     * @param key  The key for which value needs to be fetched, not null
     *
     * @return The value for the requested key
     */
    public static long getLongProperty(String key) {
        return CONFIGURATION.getLong(key);

    }
}
