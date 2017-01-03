package ru.home.study.tkach.spring.core.loggers;

import ru.home.study.tkach.spring.core.beans.Event;

import java.util.Collection;

/**
 * Created by Леонид on 31.12.2016.
 */
public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger logger: loggers) {
            logger.logEvent(event);
        }
    }
}
