package com.toy.root.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChatHandle extends TextWebSocketHandler {

    private static List<WebSocketSession> sessionsList = new ArrayList<WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionsList.add(session);
        log.info("user session add");
        System.out.println("user login");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        for (WebSocketSession s : sessionsList) {
            s.sendMessage(new TextMessage(session.getAcceptedProtocol() + " : " + msg));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionsList.remove(session);
        log.info("user session out");
        System.out.println("user logout");
    }

}
