package com.woh.chat_shell.Events;

import com.woh.chat_shell.Model.ResponseModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class ExitEvent implements IGenericEvent{
    @Override
    public void onEvent(ResponseModel model) throws IOException, InterruptedException {
        System.out.println(model.getMessage());
    }
}
