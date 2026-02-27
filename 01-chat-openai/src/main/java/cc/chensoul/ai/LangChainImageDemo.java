package cc.chensoul.ai;

import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;

public class LangChainImageDemo {

    public static void main(String[] args) {
//        ImageModel model = OpenAiImageModel.builder()
//                .apiKey(System.getenv("OPENAI_API_KEY"))
//                .modelName(DALL_E_3)
//                .build();

        ImageModel model = WanxImageModel.builder()
                .apiKey(System.getenv("DASHSCOPE_API_KEY"))
                .modelName("qwen-image")
                .build();

        Response<Image> response = model.generate(
                "Swiss software developers with cheese fondue, a parrot and a cup of coffee");

        System.out.println(response.content().url());
    }
}