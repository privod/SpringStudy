package ru.home.study.tkach.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.home.study.tkach.spring.core.beans.Event;
import ru.home.study.tkach.spring.core.loggers.ConsoleEventLogger;
import ru.home.study.tkach.spring.core.loggers.EventLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Леонид on 02.01.2017.
 */

@Aspect
@Component
public class StatisticsAspect {
    private final int CONSOLE_EVENT_MAX = 3;
    private Map<Class<?>, Integer> callCountMap = new HashMap<Class<?>, Integer>();

    @AfterReturning("LoggingAspect.allLogEventMethods()")
    public void eventLoggerCallCount(JoinPoint joinPoint) {

        Class<?> c =  joinPoint.getTarget().getClass();

        if (!callCountMap.containsKey(c)) {
            callCountMap.put(c, 0);
        }

        callCountMap.put(c, callCountMap.get(c) + 1);
    }

    @Around("consoleLoggerMethods() && args(event)")
    public  void aroundLogEvent(ProceedingJoinPoint pjp, Event event) {
        if (callCountMap.get(ConsoleEventLogger.class) > CONSOLE_EVENT_MAX) {
            pjp.proceed(new Event[]{event});
        } else {

        }

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry entry: callCountMap.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return result.toString();
    }
}
