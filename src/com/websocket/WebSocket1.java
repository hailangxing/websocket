package com.websocket;

import java.io.IOException;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/WebSocket1")
public class WebSocket1 {
    private Session session;

    @OnOpen
    public void open(Session session, EndpointConfig config) {
        this.session = session;
        System.out.println("*** WebSocket opened from sessionId "
                + session.getId());
        // sysLogger.info("*** WebSocket opened from sessionId " +
        // session.getId());
    }

    @OnMessage
    public void inMessage(Session session, String message) {
        // sysLogger.info("*** WebSocket Received from sessionId " +
        // this.session.getId() + ": " + message);
        System.out.println("WebSocket Received from sessionId:"
                + session.getId() + "-->" + message);
        try {
            session.getBasicRemote().sendText("Server收到消息：" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void end(Session session) {
        // sysLogger.info("*** WebSocket closed from sessionId " +
        // this.session.getId());
        System.out.println("*** WebSocket closed from sessionId "
                + this.session.getId());
    }
}