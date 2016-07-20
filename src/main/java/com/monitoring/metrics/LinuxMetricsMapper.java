package com.monitoring.metrics;

import com.monitoring.config.SystemConfig;
import com.monitoring.models.CPU;
import com.monitoring.models.Memory;
import com.monitoring.util.DateUtil;

import java.io.IOException;

/**
 * Class to execute collect performance metrics from a linux box by running the appropriate linux command.
 */
public class LinuxMetricsMapper implements MetricsMapper{

    private static final String HOST_NAME = SystemConfig.getStringProperty("host_name");

    @Override
    public CPU mapCPUMetric(String cpuMetric) throws Exception {
        CPU cpu;
        try {
            //split the command line output on whitespace
            String[] result = cpuMetric.replaceAll(",", ".").split("\\s+");

            cpu = new CPU(
                    DateUtil.getCPUTimestamp(result[0] + " " + result[1]),
                    HOST_NAME,
                    result[2],
                    Double.parseDouble(result[3]),
                    Double.parseDouble(result[4]),
                    Double.parseDouble(result[5]),
                    Double.parseDouble(result[6])
            );

        } catch (Exception e) {
            //TODO Add logging
            //TODO use custom exception
            throw e;
        }
        return cpu;
    }

    @Override
    public Memory mapMemoryMetric(String memoryMetric) throws Exception {

        Memory memory;
        try {
            //split the command line output on whitespace
            String[] result = memoryMetric.split("\\s+");

            memory = new Memory(
                    DateUtil.getCurrentTimestamp(),
                    Double.parseDouble(result[1]),
                    Double.parseDouble(result[2]),
                    Double.parseDouble(result[3]),
                    HOST_NAME
            );

        } catch (Exception e) {
            //TODO Add logging
            //TODO use custom exception
            throw e;
        }
        return memory;
    }
}
