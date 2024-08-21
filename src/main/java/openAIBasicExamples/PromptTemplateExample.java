package openAIBasicExamples;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import keys.ApiKeys;

import java.util.HashMap;
import java.util.Map;

import static java.time.Duration.ofSeconds;

// From: https://github.com/langchain4j/langchain4j-examples
public class PromptTemplateExample {

    public static void main(String[] args) {

        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey(ApiKeys.API_KEY_OPEN_AI)
                .timeout(ofSeconds(60))
                .build();

        String template = "Create a recipe for a {{dishType}} with some of the following ingredients: {{ingredients}}";
        PromptTemplate promptTemplate = PromptTemplate.from(template);

        Map<String, Object> variables = new HashMap<>();
        variables.put("dishType", "Italian");
        variables.put("ingredients", "potato, onions, green pepper, green onions, cheese, olive oil");

        Prompt prompt = promptTemplate.apply(variables);

        String response = model.generate(prompt.text());

        System.out.println(response);
    }
}


