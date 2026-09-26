package com.ai.springaibasics;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiBasicsApplication.class, args);
	}

	/*
	Retains only 10 messages in the chat memory in DB and it can be configured
	*/
	@Bean
	ChatMemory chatMemory(ChatMemoryRepository  chatMemoryRepository) {
		return MessageWindowChatMemory.builder()
				.chatMemoryRepository(chatMemoryRepository)
				.maxMessages(10)
				.build();
	}
}
