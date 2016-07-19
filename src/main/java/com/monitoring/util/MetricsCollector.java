package com.monitoring.util;

import com.monitoring.config.SystemConfig;
import com.monitoring.models.CPU;
import com.monitoring.models.Memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class to execute collect performance metrics from a linux box by running the appropriate linux command.
 */
public class MetricsCollector {

    private static final String HOST_NAME = SystemConfig.getStringProperty("host_name");

    /**
     * Collect CPU performance information
     *
     * @return  CPU pojo containing information about the CPU usage of a box
     * @throws Exception
     */
    public static CPU getCPUStatistics() throws Exception {
        CPU cpu;

        try {

            String allCPUMetric = getCPUStatsString();

            //split the command line output on whitespace
            String[] result = allCPUMetric.replaceAll(",", ".").split("\\s+");

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
            throw e;
        }
        return cpu;
    }

    /**
     * Execute <pre>mpstat -P ALL</pre> command and return the output for cpu 'all' in the form of String
     *
     * @return  String containing cpu performance information.
     * @throws Exception
     */
    public static String getCPUStatsString() throws Exception {
        BufferedReader metricsReader = null;
        String allCPUMetric = "";

        try {
            Runtime runtime = Runtime.getRuntime();

            //Run the mpstat command on the Linux box
            Process metricsReaderProcess = runtime.exec("mpstat -P ALL");

            metricsReader = new BufferedReader(new InputStreamReader(metricsReaderProcess.getInputStream()));

            //Ignore the first few lines containing header and other info
            metricsReader.readLine();
            metricsReader.readLine();
            metricsReader.readLine();

            //Read the output for CPU 'all'.
            allCPUMetric = metricsReader.readLine();
            if (allCPUMetric == null) {
                throw new Exception("mpstat command did not work correctly");
            }

        } catch (IOException e) {
            //TODO Add logging
            throw e;
        } finally {
            if (metricsReader != null) try {
                metricsReader.close();
            } catch (IOException e) {

            }
        }
        return allCPUMetric;
    }

    /**
     * Collect Memory information
     *
     * @return  Memory pojo containing information about the total, used and free memory on the host.
     * @throws Exception
     */
    public static Memory getMemoryStatistics() throws Exception {

        Memory memory = null;

        try {

            String memoryMetric = getMemoryStatsString();

            //split the command line output on whitespace
            String[] result = memoryMetric.split("\\s+");

            memory = new Memory(
                    DateUtil.getCurrentTimestamp(),
                    Double.parseDouble(result[1]),
                    Double.parseDouble(result[2]),
                    Double.parseDouble(result[3]),
                    HOST_NAME
            );

        } catch (IOException e) {
            //TODO Add logging
            throw e;
        }

        return memory;
    }

    /**
     * Execute <pre>free -k</pre></> to get free, total and used memory
     *
     * @return String containing memory information
     * @Exception
     */
    public static String getMemoryStatsString() throws Exception{
        BufferedReader metricsReader = null;
        String memoryMetric = "";

        try {
            Runtime runtime = Runtime.getRuntime();
            //Run the free -k command on the Linux box
            Process metricsReaderProcess = runtime.exec("free -k");

            metricsReader = new BufferedReader(new InputStreamReader(metricsReaderProcess.getInputStream()));
            metricsReader.readLine(); //Discard first line containing header info

            memoryMetric = metricsReader.readLine();

            if(memoryMetric == null) {
                throw new Exception("free -k command did not work correctly");
            }

        } catch (IOException e) {
            //TODO Add logging
            throw e;
        } finally {
            if (metricsReader != null) try {
                metricsReader.close();
            } catch (IOException e) {

            }
        }
        return memoryMetric;
    }

}
