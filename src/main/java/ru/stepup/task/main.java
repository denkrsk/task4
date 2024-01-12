package ru.stepup.task;

import ru.stepup.task.service.ProcessService;

import java.io.IOException;
import static ru.stepup.task.loadfile.LoadFile.getFileName;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

public class main {

    private final ProcessService processService;

    public static void main(String[] args) throws IOException {
           getFileName("C:/work/Java/task4/src/main/resources/files");
        processService.start();
    }



}
