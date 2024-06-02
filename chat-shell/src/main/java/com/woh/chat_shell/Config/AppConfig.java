package com.woh.chat_shell.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    DefineOperationSystem defineOperationSystem() {
        DefineOperationSystem defineOperationSystem = new DefineOperationSystem();
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            osName = "Windows";
            defineOperationSystem.setOsName(osName);
        } else if (osName.contains("Mac")) {
            osName = "Mac";
            defineOperationSystem.setOsName(osName);
        }
        return defineOperationSystem;
    }
}
