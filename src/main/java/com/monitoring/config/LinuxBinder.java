package com.monitoring.config;

import com.monitoring.database.Datasource;
import com.monitoring.metrics.CommandExecutor;
import com.monitoring.metrics.LinuxCommandExecutor;
import com.monitoring.metrics.LinuxMetricsMapper;
import com.monitoring.metrics.MetricsMapper;

/**
 * Class that binds objects to their Linux based implementation
 */
public class LinuxBinder extends AbstractBinder {

    public Datasource getDatasource() {
        return new Datasource();
    }

    public CommandExecutor getCommandExecutor() {
        return new LinuxCommandExecutor();
    }

    public MetricsMapper getMetricsMapper() {
        return new LinuxMetricsMapper();
    }
}
