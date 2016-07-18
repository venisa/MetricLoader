package com.monitoring.models;

import java.sql.Timestamp;

/**
 * Created by venisac on 7/17/16.
 */
public class CPU {

    private final Timestamp dateTime;
    private final String host_name;
    private final Double per_usr;
    private final Double per_nice;
    private final Double per_sys;
    private final Double per_io_wait;
    private final String cpu;


    public CPU(
            Timestamp dateTime,
            String host_name,
            String cpu,
            Double per_usr,
            Double per_nice,
            Double per_sys,
            Double per_io_wait

    ) {
        this.dateTime = dateTime;
        this.host_name = host_name;
        this.per_usr = per_usr;
        this.per_nice = per_nice;
        this.per_sys = per_sys;
        this.per_io_wait = per_io_wait;
        this.cpu = cpu;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public String getHost_name() {
        return host_name;
    }

    public Double getPer_usr() {
        return per_usr;
    }

    public Double getPer_nice() {
        return per_nice;
    }

    public Double getPer_sys() {
        return per_sys;
    }

    public Double getPer_io_wait() {
        return per_io_wait;
    }

    public String getCpu() {
        return cpu;
    }
}
