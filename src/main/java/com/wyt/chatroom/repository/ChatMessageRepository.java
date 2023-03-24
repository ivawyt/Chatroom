package com.wyt.chatroom.repository;

import com.wyt.chatroom.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findAllBySenderAndReceiverOrderByTimestampAsc(String sender, String receiver);

    Optional<ChatMessage> findByIdAndSenderOrReceiver(Long id, String sender, String receiver);
}
