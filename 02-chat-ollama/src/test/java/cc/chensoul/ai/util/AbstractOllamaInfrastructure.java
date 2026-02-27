package cc.chensoul.ai.util;

import static cc.chensoul.ai.util.OllamaImage.OLLAMA_IMAGE;
import static dev.langchain4j.internal.Utils.isNullOrEmpty;

public class AbstractOllamaInfrastructure {

    public static final String OLLAMA_BASE_URL = System.getenv("OLLAMA_BASE_URL");

    public static LangChain4jOllamaContainer ollama;

    static {
        if (isNullOrEmpty(OLLAMA_BASE_URL)) {
            ollama = new LangChain4jOllamaContainer(OllamaImage.resolve(OLLAMA_IMAGE, OLLAMA_IMAGE))
                    .withModel("tinyllama");
            ollama.start();
            ollama.commitToImage(OLLAMA_IMAGE);
        }
    }

    public static String ollamaBaseUrl(LangChain4jOllamaContainer ollama) {
        if (isNullOrEmpty(OLLAMA_BASE_URL)) {
            return ollama.getEndpoint();
        } else {
            return OLLAMA_BASE_URL;
        }
    }
}