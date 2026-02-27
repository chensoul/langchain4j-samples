package cc.chensoul.ai;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatOpenAIApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatOpenAIApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ChatModel chatModel) {
        return args -> {
            String answer = chatModel.chat("Who are you");
            System.out.println(answer);
        };
    }
}