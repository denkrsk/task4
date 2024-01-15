import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.test.context.SpringBootTest;
import ru.stepup.task.*;

@SpringBootTest
public class Test {
    @org.junit.jupiter.api.Test
    public static void main(String[] args) {
        System.out.println("Hallo");
        SpringApplication.run(ru.stepup.task.DemoApplication.class, args);
    }
}