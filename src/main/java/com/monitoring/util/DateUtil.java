package com.monitoring.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class containing utility methods for formatting date, converting to SQL date etc.
 */
public class DateUtil {

    /**
     * For commands like mpstat -P ALL that return the time at which the state of the system is reported, get the
     * current date and calculate the SQL timestamp
     *
     * @param time String representing time for eg: <pre> 06:33:35 PM</>
     * @return  SQL Timestamp of the command
     */
    public static Timestamp getCPUTimestamp(String time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String currDate = dateFormat.format(new Date());

        String timestamp = currDate + " " + time;

        return getSQLTimestamp(timestamp);
    }

    /**
     * For commands like free -k that do not give timestamp, take the current time as timestamp for the metric.
     *
     * @return SQL Timestamp
     */
    public static Timestamp getCurrentTimestamp() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
        String currTimestamp = dateFormat.format(new Date());

        return getSQLTimestamp(currTimestamp);
    }

    /**
     * Given a string representing timestamp in the format <pre>yyyy.MM.dd hh:mm:ss a</>, return the SQL Timestamp
     * @param timestamp
     * @return
     */
    public static Timestamp getSQLTimestamp(String timestamp) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
        Date date = null;
        try {
            date = sdf.parse(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO add logging
        }

        return new Timestamp(date.getTime());
    }

    /**
     * Given a Calendar object return the number of milliseconds to the next hour.
     * ref: http://stackoverflow.com/questions/10204246/how-to-schedule-task-for-start-of-every-hour
     *
     * @param calendar
     * @return
     */
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
