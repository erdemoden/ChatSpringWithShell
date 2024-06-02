package com.woh.chat_shell.Events;

import com.woh.chat_shell.Model.ResponseModel;
import com.woh.chat_shell.Model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetNameEvent implements IGenericEvent{
    private final UserModel userModel;
    @Override
    public void onEvent(ResponseModel model) {
        if (model.getIsError()) {
            System.out.println(model.getMessage());
        } else if(userModel.getName()!=null && userModel.getName().equals(model.getMessage())) {
            System.out.println("Name set to: " + userModel.getName());
        }
        else{
            System.out.println("User Joined Called: " + model.getMessage());
        }
    }
}
