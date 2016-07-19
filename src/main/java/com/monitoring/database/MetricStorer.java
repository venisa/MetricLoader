package com.monitoring.database;

import com.monitoring.config.SystemConfig;
import com.monitoring.models.CPU;
import com.monitoring.models.Memory;
import com.monitoring.util.MetricsCollector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class containing methods to store metrics into database
 */
public class MetricStorer {

    private static final String DATABASE = SystemConfig.getStringProperty("database");
    private static final String CPU_TABLE = SystemConfig.getStringProperty("cpu_table");
    private static final String MEMORY_TABLE = SystemConfig.getStringProperty("memory_table");

    private final Connection connection;

    public MetricStorer(Datasource datasource) {
        this.connection = datasource.getDatabaseConnection();
    }

    /**
     * Collect all the performance statistics and store it in the system
     */
    public void storeMetrics() {

        //Collect cpu metrics
        CPU cpu = null;
        try {
            cpu = MetricsCollector.getCPUStatistics();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO add logging
        }

        //store cpu Metrics
        storeCPUMetrics(cpu);

        //collect memory metrics
        Memory memory = null;
        try {
            memory = MetricsCollector.getMemoryStatistics();
        } catch (Exception e) {
            e.printStackTrace();
            //TODO add logging
        }

        //store memory metrics
        storeMemoryMetrics(memory);

    }

    /**
     * Store the CPU metric information from the cpu POJO to database.
     *
     * @param cpu  The cpu object containing cpu performance info.
     */
    protected void storeCPUMetrics(CPU cpu) {
        String insertQuery = "INSERT INTO " + DATABASE + "." + CPU_TABLE + " (dateTime, host_name, per_usr, per_nice, " +
                "per_sys, per_io_wait, cpu) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setTimestamp(1, cpu.getDateTime());
            preparedStatement.setString(2, cpu.getHost_name());
            preparedStatement.setDouble(3, cpu.getPer_usr());
            preparedStatement.setDouble(4, cpu.getPer_nice());
            preparedStatement.setDouble(5, cpu.getPer_sys());
            preparedStatement.setDouble(6, cpu.getPer_io_wait());
            preparedStatement.setString(7, cpu.getCpu());

            preparedStatement.executeUpdate();
            //TODO add logging

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO add logging
        }
    }

    /**
     * Store the Memory metric information from the Memory POJO into database
     * @param memory
     */
    protected void storeMemoryMetrics(Memory memory) {
        String insertQuery = "INSERT INTO " + DATABASE + "." + MEMORY_TABLE + " (dateTime, total, used, free, host_name) " +
                "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setTimestamp(1, memory.getDateTime());
            preparedStatement.setDouble(2, memory.getTotal());
            preparedStatement.setDouble(3, memory.getUsed());
            preparedStatement.setDouble(4, memory.getFree());
            preparedStatement.setString(5, memory.getHost_name());

            preparedStatement.executeUpdate();
            //TODO add logging

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO add logging
        }

    }
}
