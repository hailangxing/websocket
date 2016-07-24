package com.websocket;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/WebSocket")
public class WebSocket extends Endpoint {

    private Session session;

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        RemoteEndpoint.Basic remote = session.getBasicRemote();

        System.out.println("pathParams:" + session.getPathParameters());
        System.out.println("requestParams" + session.getRequestParameterMap());
        session.addMessageHandler(new MyMessageHandle(remote));
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("onClose");
    }

    @Override
    public void onError(Session session, java.lang.Throwable throwable) {
        System.out.println("onError");
    }

    private class MyMessageHandle implements MessageHandler.Whole<String> {
        RemoteEndpoint.Basic remote = null;

        public MyMessageHandle(RemoteEndpoint.Basic remote) {
            this.remote = remote;
        }

        @Override
        public void onMessage(String s) {
            System.out.println(s);
            try {
                remote.sendText("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}