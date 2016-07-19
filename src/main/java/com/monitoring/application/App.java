package com.monitoring.application;

import com.monitoring.database.Datasource;
import com.monitoring.database.MetricStorer;
import com.monitoring.util.DateUtil;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The main class of MetricLoader. This starts the schedular that collects performance metrics from the host and stores
 * it in the database.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Datasource datasource = new Datasource();
        MetricStorer metricStorer = new MetricStorer(datasource);

        Schedular schedular = new Schedular(metricStorer);

        Calendar calendar = Calendar.getInstance();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //Schedular to run the job to collect and store performance metrics in the database at the start of every hour
        scheduledExecutorService.scheduleAtFixedRate(
                schedular,
                DateUtil.millisToNextHour(calendar), 60 * 60 * 1000, TimeUnit.MILLISECONDS
        );
    }
}
