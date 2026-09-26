package com.ai.springaibasics.controller;

import com.ai.springaibasics.model.BlogList;
import com.ai.springaibasics.model.LLMRequest;
import com.ai.springaibasics.service.OpenRouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class OpenRouterController {

    private OpenRouterService openRouterService;

    public OpenRouterController(OpenRouterService openRouterService) {
        this.openRouterService = openRouterService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return openRouterService.getResponseFromOpenRouter();
    }

    @PostMapping("/ask")
    public String askLLM( @RequestBody LLMRequest request, @CookieValue(name = "X-CONV-ID") String conversationId){
        log.info("Asking LLM {}", request);
        conversationId = conversationId == null ? UUID.randomUUID().toString() : conversationId;
        return openRouterService.askLLMWithChatMemory(request, conversationId);
    }

    @GetMapping("/askspringai/{topic}")
    public BlogList askLLM(@PathVariable String topic){
        log.info("Asking LLM about spring AI {}", topic);
        return openRouterService.getStructuredOutput(topic);
    }
}
