package com.woh.ChatSpring.Events;

import com.woh.ChatSpring.DTO.Names;
import com.woh.ChatSpring.DTO.RequestModel;
import com.woh.ChatSpring.DTO.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetNameEvent implements IGenericEvent{
    private final Names names;
    @Override
    public ResponseModel onEvent(RequestModel model) {
        ResponseModel responseModel = new ResponseModel(model.getType());
        if(model.getName() == null ||model.getName().isEmpty() || model.getName().isBlank()){
            responseModel.setMessage("Name cannot be empty");
            responseModel.setIsError(true);
            return responseModel;
        }
        else{
            for (String name : names.getNames()) {
                if(name.equals(model.getName())){
                    responseModel.setMessage("Name already exists");
                    responseModel.setIsError(true);
                    return responseModel;
                }
            }
        }
        names.getNames().add(model.getName());
        responseModel.setIsError(false);
        responseModel.setMessage(model.getName());
        return responseModel;
    }
}
