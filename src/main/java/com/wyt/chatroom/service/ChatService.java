package com.wyt.chatroom.service;

// ChatService.java

import com.wyt.chatroom.model.ChatMessage;
import com.wyt.chatroom.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void sendMessage(String sender, String receiver, String message) {
        ChatMessage chatMessage = new ChatMessage(sender, receiver, message);
        chatMessageRepository.save(chatMessage);

    }

    // get single message
    public Optional<ChatMessage> getChatMessage(Long id, String sender, String receiver) {
        Optional<ChatMessage> chatMessage = chatMessageRepository.findByIdAndSenderOrReceiver(id, sender, receiver);
        if (chatMessage == null) {
            throw new RuntimeException("Chat message not found");
        }
        return chatMessage;
    }

    public List<ChatMessage> getChatMessages(String sender, String receiver) {
        List<ChatMessage> senderMessages = chatMessageRepository.findAllBySenderAndReceiverOrderByTimestampAsc(sender, receiver);
        List<ChatMessage> receiverMessages = chatMessageRepository.findAllBySenderAndReceiverOrderByTimestampAsc(receiver, sender);
        List<ChatMessage> allMessages = new ArrayList<>();
        allMessages.addAll(senderMessages);
        allMessages.addAll(receiverMessages);
        //Collections.sort(allMessages);
        return allMessages;
    }

}
