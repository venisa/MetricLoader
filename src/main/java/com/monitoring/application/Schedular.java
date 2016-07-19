package com.monitoring.application;

import com.monitoring.database.MetricStorer;

/**
 * Schedular that runs the task of collecting and storing performance metrics in the database at the start of every hour.
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
