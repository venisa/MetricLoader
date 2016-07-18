package com.monitoring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by venisac on 7/17/16.
 */
//TODO delete me after integration
public class MetricsCollectorLinux extends TimerTask{

    public static String getCPUStatistics() throws Exception {

        BufferedReader metricsReader = null;
        String[] result = null;
        String allCPUMetric = "";

        try {
            Runtime runtime = Runtime.getRuntime();
            //Run the mpstat command on the Linux box
            Process metricsReaderProcess = runtime.exec("mpstat -P ALL");

            metricsReader = new BufferedReader(new InputStreamReader(metricsReaderProcess.getInputStream()));

            metricsReader.readLine();
            metricsReader.readLine();
            metricsReader.readLine();

            //Read the first line of the output of mpstat command. i.e. Read the output for CPU 'all'.
            allCPUMetric = metricsReader.readLine();
            if (allCPUMetric == null) {
                throw new Exception("mpstat command did not work correctly");
            }

            //split the command line output on whitespace
            result = allCPUMetric.replaceAll(",", ".").split("\\s+");

            System.out.println("printing cpu metrics");
            for (int i = 0; i<result.length; i++) {
                System.out.println(result[i]);
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

    public static String getMemoryStatistics() throws Exception {

        BufferedReader metricsReader = null;
        String[] result = null;
        String memoryMetric = "";

        try {
            Runtime runtime = Runtime.getRuntime();
            //Run the free -k command on the Linux box
            Process metricsReaderProcess = runtime.exec("free -k");

            metricsReader = new BufferedReader(new InputStreamReader(metricsReaderProcess.getInputStream()));
            metricsReader.readLine(); //Discard first line

            memoryMetric = metricsReader.readLine();

            if(memoryMetric == null) {
                throw new Exception("free -k command did not work properly");
            }

            //split the command line output on whitespace
            result = memoryMetric.split("\\s+");

            System.out.println("printing memory metrics");
            for (int i = 0; i<result.length; i++) {
                System.out.println(result[i]);
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

    @Override
    public void run() {
        System.out.println("See you after 10 sec");
        try {
            System.out.println(getCPUStatistics());
            System.out.println(getMemoryStatistics());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception{
        Timer t = new Timer();
        MetricsCollectorLinux mcl = new MetricsCollectorLinux();
        t.scheduleAtFixedRate(mcl, 0, 10000);
    }
}
