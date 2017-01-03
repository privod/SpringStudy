package ru.home.study.tkach.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Леонид on 02.01.2017.
 */

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    void allLogEventMethods() {
    }

    @Pointcut("allLogEventMethods() && within(*..*File*Logger)")
    private void logEventInsideFileLoggers() {
    }

    @Pointcut("allLogEventMethods() && within(*..*Console*Logger)")
    void consoleLoggerMethods() {}

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " + joinPoint.getTarget().getClass().getSimpleName()
                + ": " + joinPoint.getSignature().getName());
    }

    @AfterReturning("logEventInsideFileLoggers()")
    public void LogAfter(JoinPoint joinPoint) {
        System.out.println("AFTER: " + joinPoint.getTarget().getClass().getSimpleName());
    }
}
