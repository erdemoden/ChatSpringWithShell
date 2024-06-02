package com.woh.ChatSpring.Events;


import com.woh.ChatSpring.DTO.RequestModel;
import com.woh.ChatSpring.DTO.ResponseModel;
import org.springframework.stereotype.Component;

@Component
public class SendMessageEvent implements IGenericEvent{
    @Override
    public ResponseModel onEvent(RequestModel model) {
        ResponseModel responseModel = new ResponseModel(model.getType());
        responseModel.setMessage(model.getName()+" says: " + model.getMessage());
        return responseModel;
    }
}
