package com.woh.chat_shell.Events;

import com.woh.chat_shell.Model.ResponseModel;
import org.springframework.stereotype.Component;

@Component
public class SendMessageEvent implements IGenericEvent{
    @Override
    public void onEvent(ResponseModel model) {
        System.out.println(model.getMessage());
    }
}
