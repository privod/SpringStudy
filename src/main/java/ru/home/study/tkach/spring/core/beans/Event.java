package ru.home.study.tkach.spring.core.beans;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Леонид on 31.12.2016.
 */

public class Event {
    private static int counter;

    private static int getNewId() {
        counter ++;
        return counter;
    }

    public static boolean isDay() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour > 8 && hour < 17) {
            return true;
        }

        return false;
    }

    private int id;
    private EventType type;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df, EventType type) {
        this.id = getNewId();
        this.date = date;
        this.df = df;
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EventType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s [%d]: %s", df.format(date), id, msg);
    }
}
