package ru.home.study.tkach.spring.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.home.study.tkach.spring.core.aspects.StatisticsAspect;
import ru.home.study.tkach.spring.core.beans.Client;
import ru.home.study.tkach.spring.core.beans.Event;
import ru.home.study.tkach.spring.core.beans.EventType;
import ru.home.study.tkach.spring.core.loggers.EventLogger;

import java.util.Map;

/**
 * Created by Леонид on 30.12.2016.
 */
public class App {
    public static void main(String[] args) {
//        ru.home.study.tkach.spring.core.App app = new ru.home.study.tkach.spring.core.App(new Client("1", "Nikolay"), new ru.home.study.tkach.spring.core.loggers.ConsoleEventLogger());

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = ctx.getBean(App.class);
        Event event = (Event) ctx.getBean("eventInfo");
        app.logEvent(event, "Information event for user 1");
        event = (Event) ctx.getBean("eventError");
        app.logEvent(event, "Error event for user 1");
        event = (Event) ctx.getBean("eventInfo");
        app.logEvent(event, "Information event for user 2");
        event = (Event) ctx.getBean("eventError");
        app.logEvent(event, "Some event for user Dan");
        event = (Event) ctx.getBean("event");
        app.logEvent(event, "Error event for user Uwasya");

        StatisticsAspect aspect = ctx.getBean(StatisticsAspect.class);
        System.out.print(aspect.toString());

        ctx.close();
    }

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    private App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        System.out.println(client.getGreeting());
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    private void logEvent(Event event, String msg) {
        event.setMsg(msg.replaceAll(client.getId(), client.getFullname()));

        EventLogger logger = loggers.get(event.getType());
        if (logger == null) {
            logger = this.defaultLogger;
        }
        logger.logEvent(event);
    }
}
