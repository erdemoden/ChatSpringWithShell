package com.woh.chat_shell.Commands;

import com.woh.chat_shell.Config.DefineOperationSystem;
import com.woh.chat_shell.Model.RequestModel;
import com.woh.chat_shell.Model.UserModel;
import com.woh.chat_shell.Service.ScreenMover;
import com.woh.chat_shell.Service.WebSocketClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@ShellComponent
@RequiredArgsConstructor
public class ChatCommands {
    private static final Logger log = LoggerFactory.getLogger(ChatCommands.class);
    private final WebSocketClientService webSocketClientService;
    private final ScreenMover screenMover;
    private final DefineOperationSystem defineOperationSystem;
    private final UserModel userModel;

    @ShellMethod(key = "send", value = "Send a message to the chat server")
    public void sendMessage(String message) {
        if(userModel == null ||userModel.getName().isEmpty()){
            System.out.println("Please set your name with setname command.");
        }
        else {
            RequestModel model = new RequestModel();
            model.setName(userModel.getName());
            model.setMessage(message);
            model.setType("send_message");
            webSocketClientService.sendMessage(model);
        }
    }

    @ShellMethod(key = "hello", value = "say Hello")
    public String sendDeneme() {
       return "Hello";
    }

    @ShellMethod(key = "setname", value = "set your name")
    public void setName(String name) throws ExecutionException, InterruptedException {
        RequestModel model = new RequestModel();
        model.setName(name);
        model.setType("set_name");
        userModel.setName(name);
        webSocketClientService.setName(model);
    }
    @ShellMethod(key = "nudge", value = "nudge screen")
    public void nudgeScreen() throws IOException, InterruptedException {
        if(userModel == null ||userModel.getName().isEmpty()){
            System.out.println("Please set your name with setname command.");
        }
        else {
            RequestModel model = new RequestModel();
            model.setType("nudge");
            model.setName(userModel.getName());
            webSocketClientService.sendNudge(model);
        }
    }

    @ShellMethod(key = "move", value = "move screen")
    public void moveScreen() throws IOException, InterruptedException {
        if(defineOperationSystem.getOsName().equals("Mac")){
            screenMover.moveScreenForMac();
        }
        else{
            System.out.println(defineOperationSystem.getOsName());
        }
    }

}
