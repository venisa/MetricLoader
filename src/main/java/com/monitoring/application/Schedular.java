package com.monitoring.application;

import com.monitoring.database.MetricStorer;

import java.util.TimerTask;

/**
 * Created by venisac on 7/17/16.
 */
public class Schedular implements Runnable{

    private final MetricStorer metricStorer;

    public Schedular(final MetricStorer metricStorer) {
        this.metricStorer = metricStorer;
    }

    public void run() {
        metricStorer.storeMetrics();
    }
}
