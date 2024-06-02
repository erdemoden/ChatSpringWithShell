package com.woh.chat_shell.Model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseModel {
    private String message;
    private String type;
    private List<String> usersAllowed;
    private Boolean isError;
}
