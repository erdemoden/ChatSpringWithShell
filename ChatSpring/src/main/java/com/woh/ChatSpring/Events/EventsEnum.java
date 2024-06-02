package com.woh.ChatSpring.Events;


public enum EventsEnum {
    NUDGE("nudge","nudgeEvent"),
    SEND_MESSAGE("send_message","sendMessageEvent"),
    SET_NAME("set_name","setNameEvent"),
    EXIT("exit","exitEvent");


    String eventType;
    String genericEvent;
    EventsEnum(String eventType, String genericEvent){
        this.genericEvent = genericEvent;
        this.eventType = eventType;
    }

    public static IGenericEvent getEvent(String eventType,EventFactory EventFactory){
        for(EventsEnum event : EventsEnum.values()){
            if(event.eventType.equals(eventType)){
                return EventFactory.getEvent(event.genericEvent);
            }
        }
        return null;
    }
}
