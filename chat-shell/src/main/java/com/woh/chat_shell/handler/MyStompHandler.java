package com.woh.chat_shell.handler;

import com.woh.chat_shell.CustomPromptProvider;
import com.woh.chat_shell.Events.EventFactory;
import com.woh.chat_shell.Events.EventsEnum;
import com.woh.chat_shell.Events.IGenericEvent;
import com.woh.chat_shell.Model.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyStompHandler extends StompSessionHandlerAdapter {
    private final EventFactory eventFactory;
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connected to WebSocket server");
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ResponseModel.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        ResponseModel responSeModel = (ResponseModel) payload;
        IGenericEvent event = EventsEnum.getEvent(responSeModel.getType(), eventFactory);
        try {
            event.onEvent(responSeModel);
            CustomPromptProvider.refreshPrompt();
        } catch (Exception e) {
            log.error("Error: ", e);
        }
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("Transport Error: ", exception);
    }
    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("STOMP Error: ", exception);
    }
    //send --name erdem --message deneme
}
