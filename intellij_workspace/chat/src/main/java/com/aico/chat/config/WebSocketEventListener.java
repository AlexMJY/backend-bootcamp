package com.aico.chat.config;

import com.aico.chat.chat.ChatMessage;
import com.aico.chat.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;

    // WebSocket 세션이 끊어지는 이벤트를 처리하는 메서드
    @EventListener
    public void HandleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        // WebSocket 메시지에서 STOMP 헤더 정보를 추출
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        // 세션 속성에서 사용자 이름을 가져옴
        String username = (String) headerAccessor.getSessionAttributes().get("name");
        if (username != null) {
            // 로그에 사용자 이름과 함께 연결이 끊어졌다는 정보를 출력
            log.info("User Disconnected : {}", username);
            // 채팅 메시지 객체를 생성하여 사용자 퇴장 정보를 포함시킴
            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            // 공용 채팅방("/topic/public")으로 퇴장 메시지를 전송
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
