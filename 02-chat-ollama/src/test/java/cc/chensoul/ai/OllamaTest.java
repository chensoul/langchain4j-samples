package cc.chensoul.ai;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.junit.jupiter.api.Test;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static cc.chensoul.ai.util.AbstractOllamaInfrastructure.ollama;
import static cc.chensoul.ai.util.AbstractOllamaInfrastructure.ollamaBaseUrl;
import static cc.chensoul.ai.util.OllamaImage.OLLAMA_IMAGE;
import static cc.chensoul.ai.util.OllamaImage.OLLAMA_MODEL;

public class OllamaTest {

    @Test
    void test_with_ollama_tinyllama() {
        try (OllamaContainer ollama = new OllamaContainer(DockerImageName.parse(OLLAMA_IMAGE)
                .asCompatibleSubstituteFor("ollama/ollama"))) {
            ollama.start();

            ChatModel model = OllamaChatModel.builder()
                    .baseUrl(ollama.getEndpoint())
                    .modelName(OLLAMA_MODEL)
                    .timeout(Duration.ofSeconds(60))
                    .build();

            String answer = model.chat("Who are you");
            System.out.println(answer);
        }
    }

    @Test
    void test_with_AbstractOllamaInfrastructure() {
        ChatModel chatModel = OllamaChatModel.builder()
                .baseUrl(ollamaBaseUrl(ollama))
                .modelName(OLLAMA_MODEL)
                .logRequests(true)
                .timeout(Duration.ofSeconds(60))
                .build();

        String answer = chatModel.chat("Who are you");
        System.out.println(answer);
    }
}
