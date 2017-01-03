package ru.home.study.tkach.spring.core.loggers;

import ru.home.study.tkach.spring.core.beans.Event;

/**
 * Created by Леонид on 30.12.2016.
 */
public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
