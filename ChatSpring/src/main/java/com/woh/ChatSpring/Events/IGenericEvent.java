package com.woh.ChatSpring.Events;

import com.woh.ChatSpring.DTO.RequestModel;
import com.woh.ChatSpring.DTO.ResponseModel;

import java.io.IOException;

public interface IGenericEvent {
    ResponseModel onEvent(RequestModel model);
}
