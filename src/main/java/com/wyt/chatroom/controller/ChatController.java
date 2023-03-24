package com.wyt.chatroom.controller;

// ChatController.java

import com.wyt.chatroom.model.ChatMessage;
import com.wyt.chatroom.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody ChatMessage chatMessage) {
        chatService.sendMessage(chatMessage.getSender(), chatMessage.getReceiver(), chatMessage.getMessage());
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<ChatMessage> getMessage(@PathVariable Long id,
                                                  @RequestParam String sender,
                                                  @RequestParam String receiver) {
        Optional<ChatMessage> optionalMessage = chatService.getChatMessage(id, sender, receiver);
        if (optionalMessage.isPresent()) {
            return ResponseEntity.ok(optionalMessage.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/history")
    public List<ChatMessage> getChatHistory(@RequestParam String sender, @RequestParam String receiver) {
        return chatService.getChatMessages(sender, receiver);
    }

}
