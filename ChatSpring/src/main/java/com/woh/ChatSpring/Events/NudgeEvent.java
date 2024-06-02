package com.woh.ChatSpring.Events;

import com.woh.ChatSpring.DTO.Names;
import com.woh.ChatSpring.DTO.RequestModel;
import com.woh.ChatSpring.DTO.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;

@Component
@RequiredArgsConstructor
public class NudgeEvent implements IGenericEvent {
    private final Names names;
    @Override
    public ResponseModel onEvent(RequestModel model) {
        ResponseModel responseModel = new ResponseModel(model.getType());
        responseModel.setMessage(model.getName() + " has nudged you");
        responseModel.setUsersAllowed(new LinkedList<>());
        for(String name : names.getNames()){
            if(!name.equals(model.getName())){
                responseModel.getUsersAllowed().add(name);
            }
        }
        return responseModel;
    }
}
