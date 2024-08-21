package openAIBasicExamples;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.structured.StructuredPrompt;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import dev.langchain4j.model.openai.OpenAiChatModel;
import keys.ApiKeys;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;
// From: https://github.com/langchain4j/langchain4j-examples
public class StructuredPromptTemplateExample {
    @StructuredPrompt({
            "Create a recipe of a {{dish}} that can be prepared using only {{ingredients}}.",
            "Structure your answer in the following way:",

            "Recipe name: ...",
            "Description: ...",
            "Preparation time: ...",

            "Required ingredients:",
                    "- ...",
                    "- ...",

                    "Instructions:",
                    "- ...",
                    "- ..."
            })

            static class RecipePrompt {

                private String dish;
                private List<String> ingredients;

                RecipePrompt(String dish, List<String> ingredients) {
                    this.dish = dish;
                    this.ingredients = ingredients;
                }
            }

            public static void main(String[] args) {

                ChatLanguageModel model = OpenAiChatModel.builder()
                        .apiKey(ApiKeys.API_KEY_OPEN_AI)
                        .timeout(ofSeconds(60))
                        .build();

                RecipePrompt createRecipePrompt = new RecipePrompt(
                        "salad",
                        asList("cucumber", "tomato", "feta", "onion", "olives")
                );

                Prompt prompt = StructuredPromptProcessor.toPrompt(createRecipePrompt);

                String recipe = model.generate(prompt.text());

                System.out.println(recipe);
            }
}


