package com.woh.chat_shell;

import jakarta.annotation.PreDestroy;
import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {
    private final LineReader lineReader;
    @Autowired
    public CustomPromptProvider(LineReader lineReader) {
        this.lineReader = lineReader;
    }
    @Override
    public AttributedString getPrompt() {
        return new AttributedString(
                "ChatShell => ", AttributedStyle.DEFAULT.background(AttributedStyle.GREEN));
    }
    public static void refreshPrompt() {
      System.out.print(new AttributedString("ChatShell => ", AttributedStyle.DEFAULT.background(AttributedStyle.GREEN)).toAnsi());
    }
}
