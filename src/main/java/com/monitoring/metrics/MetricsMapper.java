package com.monitoring.metrics;

import com.monitoring.models.CPU;
import com.monitoring.models.Memory;

/**
 * An interface containing classes to map a metric string to the POJO describing the metric
 */
public interface MetricsMapper {

    /**
     * Given a string representing a cpu metric, map it to CPU object
     *
     * @param CPU  String representing CPU metric
     * @return CPU object generated from the given string
     * @throws Exception if the string cannot be parsed into a CPU object
     */
    public CPU mapCPUMetric(String CPU) throws Exception;

    /**
     * Given a String representing a memory metric, map it to Memory Object
     *
     * @param memory  String representing memory metric
     * @return Memory object generated from the given string
     * @throws Exception if the given string cannot be parsed into a Memory object
     */
    public Memory mapMemoryMetric(String memory) throws Exception;
}
