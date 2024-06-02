package com.woh.chat_shell.Events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventFactory {
    private final ApplicationContext applicationContext;

    public IGenericEvent getEvent(String type) {
        return applicationContext.getBean(type, IGenericEvent.class);
    }
}
