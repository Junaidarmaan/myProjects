package com.junaid.whatsappclone;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConstraintController {


    @GetMapping("/getUsers")
    public String getUsers(){
        return WebSocketHandler.sessions.keySet().toString();
    }
    @GetMapping("/isUnique/{id}")
    public boolean isUnique(@PathVariable String id){
        return WebSocketHandler.sessions.containsKey(id);
    }
}
