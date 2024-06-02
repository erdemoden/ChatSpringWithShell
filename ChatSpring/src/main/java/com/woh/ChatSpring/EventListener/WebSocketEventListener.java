package com.woh.ChatSpring.EventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketEventListener {
    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);
    private static final ConcurrentHashMap<String, String> userSessions = new ConcurrentHashMap<>();

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        String username = headerAccessor.getFirstNativeHeader("name");

        if (username != null) {
            userSessions.put(sessionId, username);
            log.info("User connected: " + username + " with session ID: " + sessionId);
        }
    }

    @EventListener
    public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        String username = headerAccessor.getFirstNativeHeader("name");

        if (username != null) {
            userSessions.put(sessionId, username);
            log.info("User connected: " + username + " with session ID: " + sessionId);
        }
    }
    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        log.debug("Client with username {} disconnected", event.getUser());
    }
}
