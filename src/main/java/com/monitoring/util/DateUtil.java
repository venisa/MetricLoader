package com.monitoring.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by venisac on 7/17/16.
 */
public class DateUtil {

    public static Timestamp getCPUTimestamp(String time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String currDate = dateFormat.format(new Date());

        String timestamp = currDate + " " + time;

        return getSQLTimestamp(timestamp);
    }

    /**
     * For commands like free -k that do not give timestamp, calculate the approximate timestamp at which the
     * command was run
     *
     * @return SQL Timestamp
     */
    public static Timestamp getCurrentTimestamp() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a zzzz");
        String currTimestamp = dateFormat.format(new Date());

        return getSQLTimestamp(currTimestamp);
    }

    public static Timestamp getSQLTimestamp(String timestamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a zzzz");
        Date date = null;
        try {
            date = sdf.parse(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO add logging
        }

        return new Timestamp(date.getTime());
    }

    //TODO ref add to readme
    //ref: http://stackoverflow.com/questions/10204246/how-to-schedule-task-for-start-of-every-hour
    public static long millisToNextHour(Calendar calendar) {
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        int millis = calendar.get(Calendar.MILLISECOND);
        int minutesToNextHour = 60 - minutes;
        int secondsToNextHour = 60 - seconds;
        int millisToNextHour = 1000 - millis;
        return minutesToNextHour*60*1000 + secondsToNextHour*1000 + millisToNextHour;
    }
}
