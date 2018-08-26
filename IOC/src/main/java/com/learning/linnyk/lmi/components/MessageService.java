package com.learning.linnyk.lmi.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MessageService {

    public Message getMessage(){
        return null;
    }

    public void send(){
        getMessage().read();
    }

}
