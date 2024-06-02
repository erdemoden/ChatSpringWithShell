package com.woh.ChatSpring.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ResponseModel {
    private String message;
    private final String type;
    private List<String> usersAllowed;
    private Boolean isError;
}
