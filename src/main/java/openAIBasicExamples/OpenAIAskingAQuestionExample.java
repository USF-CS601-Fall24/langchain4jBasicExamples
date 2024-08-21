package openAIBasicExamples;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import keys.ApiKeys;

// From: https://github.com/langchain4j/langchain4j-examples
public class OpenAIAskingAQuestionExample {

    public static void main(String[] args) {
        // Before running this example, set your OpenAI API key in ApiKeys class
        ChatLanguageModel cl = OpenAiChatModel.withApiKey(ApiKeys.API_KEY_OPEN_AI);
        String answer = cl.generate("Explain the benefits of using LangChain4j.");
        System.out.println(answer);

    }
}