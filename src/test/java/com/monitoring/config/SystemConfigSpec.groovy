package com.monitoring.config

import com.monitoring.config.SystemConfig

import spock.lang.Specification

/**
 * Tests for SystemConfig class
 */
class SystemConfigSpec extends Specification {
    private static final String PROPERTY_KEY = "jdbc_driver"
    private static final String EXPECTED_PROPERTY_VALUE = "com.mysql.jdbc.Driver"

    def "We can read the config properties correctly from the config file"() {
        expect:
        SystemConfig.getStringProperty(PROPERTY_KEY) == EXPECTED_PROPERTY_VALUE
    }
}
