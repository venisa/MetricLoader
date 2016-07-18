package com.monitoring.util;

import com.monitoring.config.SystemConfig;
import com.monitoring.models.CPU;
import com.monitoring.models.Memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by venisac on 7/17/16.
 */
public class MetricsCollector {

    private static final String HOST_NAME = SystemConfig.getStringProperty("host_name");

    public static CPU getCPUStatistics() throws Exception {

        BufferedReader metricsReader = null;
        CPU cpu = null;

        try {
            Runtime runtime = Runtime.getRuntime();
            //Run the mpstat command on the Linux box
            Process metricsReaderProcess = runtime.exec("mpstat -P ALL");

            metricsReader = new BufferedReader(new InputStreamReader(metricsReaderProcess.getInputStream()));

            metricsReader.readLine();
            metricsReader.readLine();
            metricsReader.readLine();

            //Read the first line of the output of mpstat command. i.e. Read the output for CPU 'all'.
            String allCPUMetric = metricsReader.readLine();
            if (allCPUMetric == null) {
                throw new Exception("mpstat command did not work correctly");
            }

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

        } catch (IOException e) {
            //TODO Add logging
            throw e;
        } finally {
            if (metricsReader != null) try {
                metricsReader.close();
            } catch (IOException e) {

            }
        }

        return cpu;
    }

    public static Memory getMemoryStatistics() throws Exception {

        BufferedReader metricsReader = null;
        Memory memory = null;

        try {
            Runtime runtime = Runtime.getRuntime();
            //Run the free -k command on the Linux box
            Process metricsReaderProcess = runtime.exec("free -k");

            metricsReader = new BufferedReader(new InputStreamReader(metricsReaderProcess.getInputStream()));
            metricsReader.readLine(); //Discard first line

            String memoryMetric = metricsReader.readLine();

            if(memoryMetric == null) {
                throw new Exception("free -k command did not work properly");
            }

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
        } finally {
            if (metricsReader != null) try {
                metricsReader.close();
            } catch (IOException e) {

            }
        }

        return memory;
    }
}
