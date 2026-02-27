package cc.chensoul.ai;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

public class LangChainChatDemo {

    public static void main(String[] args) {
//        ChatModel model = OpenAiChatModel.builder()
//                .apiKey(System.getenv("OPENAI_API_KEY"))
//                .build();

//        ChatModel model = OpenAiChatModel.builder()
//                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
//                .apiKey(System.getenv("DASHSCOPE_API_KEY"))
//                .modelName("qwen-turbo")
//                .build();

        ChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .apiKey(System.getenv("DASHSCOPE_API_KEY"))
                .modelName("qwen-turbo")
                .temperature(0.3)
                .maxRetries(1)
                .timeout(Duration.ofSeconds(60))
                .listeners(List.of(new TestChatModelListener()))
                .logRequests(true)
                .logResponses(true)
                .build();

        String answer = model.chat("Who are you");
        System.out.println(answer);
    }

    @Slf4j
    public static class TestChatModelListener implements ChatModelListener {
        @Override
        public void onRequest(ChatModelRequestContext requestContext) {
            requestContext.attributes().put("test", "test");
            log.info("请求参数:{}", requestContext.attributes());
        }

        @Override
        public void onResponse(ChatModelResponseContext responseContext) {
            Object object = responseContext.attributes().get("test");

            log.info("返回结果:{}", object);
        }

        @Override
        public void onError(ChatModelErrorContext errorContext) {
            log.error("请求异常:{}", errorContext);
        }
    }
}