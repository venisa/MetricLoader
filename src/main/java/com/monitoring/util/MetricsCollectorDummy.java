package com.monitoring.util;

import com.monitoring.config.SystemConfig;
import com.monitoring.models.CPU;
import com.monitoring.models.Memory;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by venisac on 7/17/16.
 */
//TODO remove me after integration
public class MetricsCollectorDummy {

    private static final String HOST_NAME = SystemConfig.getStringProperty("host_name");

    public static CPU getCPUStatistics() throws Exception {

        String allCPUMetric = "09:40:13 PM  all    1.08    0.02    0.77    0.05    0.00    0.00    0.16    0.00   97.92";
        String[] result = allCPUMetric.replaceAll(",", ".").split("\\s+");

        CPU cpu = new CPU(
                new Timestamp(new Date().getTime()),
                HOST_NAME,
                result[2],
                Double.parseDouble(result[3]),
                Double.parseDouble(result[4]),
                Double.parseDouble(result[5]),
                Double.parseDouble(result[6])
        );

        return cpu;
    }

    public static Memory getMemoryStatistics() throws Exception {

        String memoryMetric = "Mem:       4054800    2484764    1570036          0     447804     851808";
        String[] result = memoryMetric.split("\\s+");

        Memory memory = new Memory(
                new Timestamp(new Date().getTime()),
                Double.parseDouble(result[1]),
                Double.parseDouble(result[2]),
                Double.parseDouble(result[3]),
                HOST_NAME
        );

        return memory;
    }
}
