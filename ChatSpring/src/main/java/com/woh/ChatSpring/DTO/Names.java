package com.woh.ChatSpring.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
public class Names {
    private List<String> names = new LinkedList<>();
}
