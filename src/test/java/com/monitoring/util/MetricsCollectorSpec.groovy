package com.monitoring.util

import com.monitoring.metrics.LinuxCommandExecutor
import com.monitoring.metrics.LinuxMetricsMapper
import com.monitoring.models.CPU

import spock.lang.Specification

/**
 * Tests for MetricCollector class
 */
class MetricsCollectorSpec extends Specification {

    private static final String CPU = "06:33:35 PM  all    1.08    0.02    0.77    0.05    0.00    0.00    0.16    0.00   97.92"

    def setup() {
    }

    def "getCPUStatistics returns a CPU object containing the correct fields from the mpstat command"() {
        setup:
        LinuxCommandExecutor commandExecutor = Mock(LinuxCommandExecutor)
        commandExecutor.getCPUStatistics() >> CPU
        CPU cpu = LinuxMetricsMapper.mapCPUMetric()

        expect:
        cpu.cpu == "all"
    }

    def "getCPUStatsString returns the 'cpu all' line from the result of mpstat command"() {

    }

    def "getMemoryStatistics returns a Memory object containing the correct fields from the free -k command output"() {

    }

    def "getMemoryStatsString returns information about 'Mem:' from the result of the free -k command" () {

    }

    //TODO write tests for sad path

}
