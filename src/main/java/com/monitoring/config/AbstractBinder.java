package com.monitoring.config;

import com.monitoring.database.Datasource;
import com.monitoring.database.MetricStorer;
import com.monitoring.metrics.CommandExecutor;
import com.monitoring.metrics.LinuxCommandExecutor;
import com.monitoring.metrics.LinuxMetricsMapper;
import com.monitoring.metrics.MetricsManager;
import com.monitoring.metrics.MetricsMapper;

/**
 * Extend this class to bind custom CommandExecutor, CustomMetricsMapper and Custom Datasource
 */
public abstract class AbstractBinder {

    public abstract Datasource getDatasource();

    public abstract CommandExecutor getCommandExecutor();

    public abstract MetricsMapper getMetricsMapper();

    public MetricsManager getMetricManager() {
        MetricStorer metricStorer = new MetricStorer(getDatasource());
        return new MetricsManager(getCommandExecutor(), getMetricsMapper(), metricStorer);
    }
}
