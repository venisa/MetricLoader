package com.monitoring.metrics

import com.monitoring.models.CPU
import com.monitoring.models.Memory

import spock.lang.Specification

/**
 * Test LinuxMetricsMapper
 */
class LinuxMetricsMapperSpec extends Specification {

    private static final String CPU_STRING = "06:33:35 PM  all    1.08    0.02    0.77    0.05    0.00    0.00    0.16    0.00   97.92"
    private static final String MEMORY_STRING = "Mem:       4054800    3010056    1044744          0     457640    1250500"
    LinuxMetricsMapper linuxMetricsMapper = new LinuxMetricsMapper()

    def "mapCPUMetrics maps the given string to CPU object correctly"() {
        setup:
        CPU cpu = linuxMetricsMapper.mapCPUMetric(CPU_STRING)

        expect:
        cpu.cpu == "all"
        cpu.host_name == "host3"
        cpu.per_usr == 1.08
        cpu.per_io_wait == 0.05
        cpu.per_nice == 0.02
        cpu.per_sys == 0.77
    }

    def "mapMemoryMetrics maps the given string to Memory object correctly"() {
     setup:
     Memory memory = linuxMetricsMapper.mapMemoryMetric(MEMORY_STRING)

     expect:
     memory.host_name == "host3"
     memory.free == 1044744
     memory.used == 3010056
     memory.total == 4054800
    }

    //TODO write tests for sad path(Malformed strings)
}
