package com.monitoring.application;

import com.monitoring.metrics.MetricsManager;

/**
 * Schedular that runs the task of collecting and storing performance metrics in the database at the start of every hour.
 */
public class Schedular implements Runnable{

    private final MetricsManager metricsManager;

    public Schedular(MetricsManager metricsManager) {
        this.metricsManager = metricsManager;
    }

    public void run() {
        metricsManager.manageMetrics();
    }
}
