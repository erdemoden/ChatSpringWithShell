package com.woh.chat_shell.Events;

import com.woh.chat_shell.Model.ResponseModel;

import java.io.IOException;

public interface IGenericEvent {
    void onEvent(ResponseModel model) throws IOException, InterruptedException;
}
