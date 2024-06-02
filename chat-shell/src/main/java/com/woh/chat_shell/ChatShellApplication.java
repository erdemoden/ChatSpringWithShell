package com.woh.chat_shell;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Slf4j
public class ChatShellApplication {


	public static void main(String[] args) {
		SpringApplication.run(ChatShellApplication.class, args);
	}
}
