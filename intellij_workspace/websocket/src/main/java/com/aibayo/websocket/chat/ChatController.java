package com.aibayo.websocket.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자를 통해 필요한 의존성을 자동으로 주입합니다.
public class ChatController {
    private final ChatMessageService chatMessageService; // 채팅 메시지 서비스를 주입받습니다.
    private final SimpMessagingTemplate messagingTemplate; // 메시지 전송 템플릿을 주입받습니다.

    @MessageMapping("/chat") // 클라이언트에서 "/chat" 경로로 메시지를 보낼 때 호출됩니다.
    public void processMessage(@Payload ChatMessage chatMessage) {
        // 수신자의 ID가 null이거나 비어있는지 확인
        if (chatMessage.getRecipientId() == null || chatMessage.getRecipientId().isEmpty()) {
            throw new IllegalArgumentException("Recipient ID must not be null or empty");
        }

        ChatMessage savedMsg = chatMessageService.save(chatMessage); // 받은 메시지를 저장합니다.
        messagingTemplate.convertAndSendToUser( // 특정 사용자에게 메시지를 전송합니다.
                chatMessage.getRecipientId(), "/queue/messages", // 수신자의 ID와 메시지 큐 경로를 지정합니다.
                ChatNotification.builder() // ChatNotification 객체를 빌드하여 전송합니다.
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build()
        );
    }

    @GetMapping("/message/{senderId}/{recipientId}") // 특정 송신자와 수신자 간의 메시지를 조회합니다.
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId, @PathVariable("recipientId") String recipientId) {
        // 송신자와 수신자 ID를 기반으로 메시지를 조회하고, HTTP 응답으로 반환합니다.
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}
