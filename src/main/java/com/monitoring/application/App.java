package com.monitoring.application;

import com.monitoring.database.Datasource;
import com.monitoring.database.MetricStorer;
import com.monitoring.util.DateUtil;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
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

        /*
        scheduledExecutorService.scheduleAtFixedRate(
                schedular,
                DateUtil.millisToNextHour(calendar), 60 * 60 * 1000, TimeUnit.MILLISECONDS
        );
        */



        scheduledExecutorService.scheduleAtFixedRate(
                schedular,
                (long) 0, 10, TimeUnit.SECONDS
        );

    }
}
