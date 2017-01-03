package ru.home.study.tkach.spring.core.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.home.study.tkach.spring.core.beans.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Леонид on 31.12.2016.
 */

@Component("fileEventLogger")
public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    @Autowired
    public FileEventLogger(@Value("log.txt") String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    @PostConstruct
    public void init() throws IOException {
        if (!this.file.canWrite()) {
            throw new IOException(String.format("File %s is not writable", filename));
        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
