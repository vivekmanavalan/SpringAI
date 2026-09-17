package com.ai.springaibasics.service;

import com.ai.springaibasics.model.BlogList;
import com.ai.springaibasics.model.LLMRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.AdvisorParams;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class OpenRouterService {

    private static final Logger log = LoggerFactory.getLogger(OpenRouterService.class);
    private final ChatClient chatClient;

    public OpenRouterService(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
                //We can do this to get the detailed log
                //.defaultAdvisors(new SimpleLoggerAdvisor())
                /*Passes the schema directly to provider's(openAi, Grok) native formatting
                parameter instead of relying on prompt text instructions*/
                .defaultAdvisors(AdvisorParams.ENABLE_NATIVE_STRUCTURED_OUTPUT)
                .build();
    }

    public String getResponseFromOpenRouter(){
        String response = chatClient.prompt()
                .user("Say hi")
                .call()
                .content();
        log.info("response from LLM: {}", response);
        return response;
    }

    public String askLLM(LLMRequest request){
        String response = chatClient.prompt()
                .user(request.getQuestion())
                .system("Respond in one line")
                .call().content();
        log.info("Response from LLM: {}", response);
        return response;
    }
    public BlogList getStructuredOutput(String topic){
        BlogList response = chatClient.prompt()
                .user(promptUserSpec ->
                        {
                            promptUserSpec.text("Recommend top 3 blogs only that talk about the spring ai {topic}");
                            promptUserSpec.param("topic", topic);
                        }
                        )
                .call()
                .entity(BlogList.class);
        log.info("Response from LLM: {}", response);
        return response;
    }
}
