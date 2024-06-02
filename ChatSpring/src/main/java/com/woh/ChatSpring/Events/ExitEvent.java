package com.woh.ChatSpring.Events;

import com.woh.ChatSpring.DTO.Names;
import com.woh.ChatSpring.DTO.RequestModel;
import com.woh.ChatSpring.DTO.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExitEvent implements IGenericEvent{
    private final Names names;
    @Override
    public ResponseModel onEvent(RequestModel model) {
        names.getNames().remove(model.getName());
        ResponseModel responseModel = new ResponseModel(model.getType());
        responseModel.setMessage("Goodbye, " + model.getName() + "!");
        log.info("User " + model.getName() + " has left the chat");
        return responseModel;
    }
}
