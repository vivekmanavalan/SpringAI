package com.ai.springaibasics.controller;

import com.ai.springaibasics.model.BlogList;
import com.ai.springaibasics.model.LLMRequest;
import com.ai.springaibasics.service.OpenRouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public String askLLM( @RequestBody LLMRequest request){
        log.info("Asking LLM {}", request);
        return openRouterService.askLLM(request);
    }

    @GetMapping("/askspringai/{topic}")
    public BlogList askLLM(@PathVariable String topic){
        log.info("Asking LLM about spring AI {}", topic);
        return openRouterService.getStructuredOutput(topic);
    }
}
