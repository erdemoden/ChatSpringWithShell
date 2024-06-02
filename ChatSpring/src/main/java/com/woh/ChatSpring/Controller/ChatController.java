package com.woh.ChatSpring.Controller;

import com.woh.ChatSpring.DTO.RequestModel;
import com.woh.ChatSpring.DTO.ResponseModel;
import com.woh.ChatSpring.Events.EventFactory;
import com.woh.ChatSpring.Events.EventsEnum;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final EventFactory eventFactory;;
    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public ResponseModel processMessageFromClient(RequestModel model) throws Exception {
       return EventsEnum.getEvent(model.getType(),eventFactory).onEvent(model);
    }
}
