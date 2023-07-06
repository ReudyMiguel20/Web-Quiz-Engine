package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WebQuizEngine {

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

    @Bean
    public Quiz defaultQuiz() {
        return new Quiz("The Java Logo",
                        "What is depicted on the Java logo?",
                List.of("Robot", "Tea leaf", "Cup of coffee", "Bug"));
    }

}
