package com.woh.chat_shell.Service;

import com.woh.chat_shell.Model.RequestModel;
import com.woh.chat_shell.Model.UserModel;
import com.woh.chat_shell.handler.MyStompHandler;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;
@Component
@RequiredArgsConstructor
public class WebSocketClientService{

    private static final Logger log = LoggerFactory.getLogger(WebSocketClientService.class);
    private WebSocketClient webSocketClient = new StandardWebSocketClient();
    private WebSocketStompClient client = new WebSocketStompClient(webSocketClient);
    private StompSession session = null;
    private Boolean connected = false;
    private final UserModel userModel;
    private final MyStompHandler handler;

    @PostConstruct
    public void init() throws ExecutionException, InterruptedException {
        client.setMessageConverter(new MappingJackson2MessageConverter());
        ListenableFuture<StompSession> sessionAsync = client.connect("ws://juicy-fredra-woh-54136c42.koyeb.app/stomp-endpoint", handler);
        session = sessionAsync.get();
        session.subscribe("/chat/messages", handler);
        log.info("Subscribed to /chat/messages.");
    }
        public void sendMessage(RequestModel model) {
            session.send("/app/message", model);
        }
        public void setName(RequestModel model){
            session.send("/app/message", model);
            connected = true;
        }
        public void sendNudge(RequestModel model) {
            session.send("/app/message", model);
        }

    @PreDestroy
    public void onExit() {
        if (connected) {
            RequestModel model = new RequestModel();
            model.setType("exit");
            model.setName(userModel.getName());
            session.send("/app/message", model);
            session.disconnect();
        }
    }

}
