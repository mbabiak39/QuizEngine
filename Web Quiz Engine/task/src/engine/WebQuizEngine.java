package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebQuizEngine extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

}
