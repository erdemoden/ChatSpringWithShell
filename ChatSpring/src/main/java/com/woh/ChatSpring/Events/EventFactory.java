package com.woh.ChatSpring.Events;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EventFactory {
    private final ApplicationContext applicationContext;

    public EventFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    public IGenericEvent getEvent(String beanName) {
        return applicationContext.getBean(beanName, IGenericEvent.class);
    }
}
