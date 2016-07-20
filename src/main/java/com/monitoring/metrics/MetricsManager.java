package com.monitoring.metrics;

import com.monitoring.database.MetricStorer;
import com.monitoring.models.CPU;
import com.monitoring.models.Memory;

/**
 * Class to manage metrics gathering, mapping and storing
 */
public class MetricsManager {

    private final CommandExecutor commandExecutor;
    private final MetricsMapper metricsMapper;
    private final MetricStorer metricStorer;

    public MetricsManager(CommandExecutor commandExecutor, MetricsMapper metricsMapper, MetricStorer metricStorer) {
        this.commandExecutor = commandExecutor;
        this.metricsMapper = metricsMapper;
        this.metricStorer = metricStorer;
    }

    /**
     * Method to collect, map and store cpu and memory metric
     */
    public void manageMetrics() {

        try {
            //collect cpu metrics
            String cpuString = commandExecutor.getCPUStatistics();

            //map cpu metrics
            CPU cpu = metricsMapper.mapCPUMetric(cpuString);

            //store cpu metrics
            metricStorer.storeCPUMetrics(cpu);

            //Collect memory metrics
            String memoryString = commandExecutor.getMemoryStatistics();

            //map memory metrics
            Memory memory = metricsMapper.mapMemoryMetric(memoryString);

            //store memory string
            metricStorer.storeMemoryMetrics(memory);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO add logging
        }

    }
}
