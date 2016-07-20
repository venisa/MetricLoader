package com.monitoring.application;

import com.monitoring.config.AbstractBinder;
import com.monitoring.config.LinuxBinder;

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
        //TODO use h2k dependency injection
        AbstractBinder binder = new LinuxBinder();

        Schedular schedular = new Schedular(binder.getMetricManager());

        Calendar calendar = Calendar.getInstance();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //Schedular to run the job to collect and store performance metrics in the database at the start of every hour
        scheduledExecutorService.scheduleAtFixedRate(
                schedular,
                DateUtil.millisToNextHour(calendar), 60 * 60 * 1000, TimeUnit.MILLISECONDS
        );
    }
}
