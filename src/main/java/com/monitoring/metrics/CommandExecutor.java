package com.monitoring.metrics;

/**
 * An interface containing methods to execute commands on a specific OS and return the result
 */
public interface CommandExecutor {

    /**
     * Method that executes a command to get CPU metric and returns the result in the form of a String
     *
     * @return  String representing CPU metric
     * @throws Exception If the command cannot be executed successfully
     */
    public String getCPUStatistics() throws Exception;

    /**
     * Method that executes a command to get Memory metric and returns the result in the form of a String
     *
     * @return  String representing Memory metric
     * @throws Exception If the memory command cannot be executed correctly
     */
    public String getMemoryStatistics() throws Exception;
}
