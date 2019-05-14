package com.codeicus.challenge.controller.jsf;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("jsfTaskController")
public class JsfTaskController {

    public String getText() {
        return "Hello from Spring: " + LocalDateTime.now().toString();
    }
}