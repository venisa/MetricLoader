package com.monitoring.models;

import java.sql.Timestamp;

/**
 * Pojo that maps to Memory table in the database
 */
public class Memory {
    private final Timestamp dateTime;
    private final Double total;
    private final Double used;
    private final Double free;
    private final String host_name;

    public Memory(
            final Timestamp dateTime,
            final Double total,
            final Double used,
            final Double free,
            final String host_name
    ) {
        this.dateTime = dateTime;
        this.total = total;
        this.used = used;
        this.free = free;
        this.host_name = host_name;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public Double getTotal() {
        return total;
    }

    public Double getUsed() {
        return used;
    }

    public Double getFree() {
        return free;
    }

    public String getHost_name() {
        return host_name;
    }
}
