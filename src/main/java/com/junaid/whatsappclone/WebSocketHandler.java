package com.junaid.whatsappclone;

import java.util.HashMap;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;


class Message{
    String message;
    String target;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public  String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
   
   
}


public class WebSocketHandler extends TextWebSocketHandler {
     public static final HashMap<String,WebSocketSession> sessions = new HashMap<>();
     private  final ObjectMapper objectmapper = new ObjectMapper();

    public WebSocketHandler() {
    }
     @Override
     public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String customId = session.getUri().getQuery().split("=")[1];
        System.out.println("custom id is : " + customId);
        sessions.put(customId,session);
       System.out.println(String.format("NEW CONNECTION:\nSESSION : %s\nID : %s",session.getId(),customId));
         
     }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage JsonMessage) throws Exception {
        String payLoad = JsonMessage.getPayload();
        Message message = objectmapper.readValue(payLoad,Message.class);
        String destination = message.target;

        TextMessage str = new TextMessage(message.message);
        System.out.println("payload received is : " + payLoad);
        System.out.println(String.format("message : %s\ndestination : %s",message.message,destination));

        for(String id : sessions.keySet()){
            if(id.equals(destination)){
                sessions.get(id).sendMessage(str);
            }
        }
        

       
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
       for(String id : sessions.keySet()){
        if(sessions.get(id).equals(session)){
            System.out.println(String.format("CONNECTION LOST :\nSESSION : %s\nID : %s",sessions.get(id),id));
            sessions.remove(id);
            System.out.println(sessions.keySet());
        }
       }
    }

    


    
    
    
}
