package com.monitoring.metrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that contains methods that execute linux commands and return their results.
 */
public class LinuxCommandExecutor implements CommandExecutor{

    /**
     * Execute <pre>mpstat -P ALL</pre> command and return the output for cpu 'all' in the form of String
     *
     * @return  String containing cpu performance information.
     * @throws Exception
     */
    public String getCPUStatistics() throws Exception {

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
     * Execute <pre>free -k</pre></> to get free, total and used memory
     *
     * @return String containing memory information
     * @Exception
     */
    public String getMemoryStatistics() throws Exception{
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
