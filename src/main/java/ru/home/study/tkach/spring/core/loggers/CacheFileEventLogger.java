package ru.home.study.tkach.spring.core.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.home.study.tkach.spring.core.beans.Event;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Леонид on 31.12.2016.
 */

@Component("cacheFileEventLogger")
public class CacheFileEventLogger extends FileEventLogger {
    private List<Event> cache;
    private int cacheSize;

    @Autowired
    public CacheFileEventLogger(@Value("log.txt") String filename, @Value("3") int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>();
    }

    private void writeEventFromCache() {
        for (Event event: cache) {
            super.logEvent(event);
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() >= cacheSize) {
            writeEventFromCache();
            cache.clear();
        }
    }

    @PreDestroy
    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventFromCache();
        }
    }
}
