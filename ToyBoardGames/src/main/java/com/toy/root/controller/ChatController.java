package com.toy.root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chatpage")
    public String chatPage() {
        return "chat/chatpage";
    }
}
