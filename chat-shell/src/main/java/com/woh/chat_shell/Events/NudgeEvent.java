package com.woh.chat_shell.Events;

import com.woh.chat_shell.Config.DefineOperationSystem;
import com.woh.chat_shell.Model.ResponseModel;
import com.woh.chat_shell.Model.UserModel;
import com.woh.chat_shell.Service.ScreenMover;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class NudgeEvent implements IGenericEvent {
    private final ScreenMover screenMover;
    private final DefineOperationSystem defineOperationSystem;
    private final UserModel userModel;

    @Override
    public void onEvent(ResponseModel model) throws IOException, InterruptedException {
            if (model.getUsersAllowed().contains(userModel.getName())) {
                if (defineOperationSystem.getOsName().equals("Windows")) {
                    System.out.println(model.getMessage());
                    screenMover.moveScreenForWindows();
                } else if (defineOperationSystem.getOsName().equals("Mac")) {
                    System.out.println(model.getMessage());
                    screenMover.moveScreenForMac();
                } else {
                    System.out.println(defineOperationSystem.getOsName()+" is not supported.");
                }
            }
        }
}
