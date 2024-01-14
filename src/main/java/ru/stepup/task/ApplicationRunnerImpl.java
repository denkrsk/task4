package ru.stepup.task;

import ru.stepup.task.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final ProcessService processService;

    @Override
    public void run(ApplicationArguments args) throws IOException{
        String[] ar = args.getSourceArgs();
        String dirStr = "";
        for (int i = 0; i < ar.length; i++) {
            dirStr = ar[i];
        }
//        System.out.println(dirStr);
        processService.start(dirStr);
    }
}
