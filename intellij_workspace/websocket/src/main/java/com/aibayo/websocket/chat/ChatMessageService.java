package com.aibayo.websocket.chat;

import com.aibayo.websocket.chatroom.ChatRoomService;
import com.aibayo.websocket.exception.ChatRoomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    // 의존성 주입: ChatMessageRepository와 ChatRoomService를 final로 선언하여 필수적으로 초기화
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    // 새로운 채팅 메시지를 저장하는 메서드
    public ChatMessage save(ChatMessage chatMessage) {
        // 채팅방 ID를 가져옴. 존재하지 않으면 ChatRoomNotFoundException 예외를 던짐
        var chatId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new ChatRoomNotFoundException("Chat room not found for " + chatMessage.getSenderId() + " and recipientId: " + chatMessage.getRecipientId()));

        // 채팅 메시지에 채팅방 ID를 설정
        chatMessage.setChatId(chatId);
        // 채팅 메시지를 저장소에 저장
        chatMessageRepository.save(chatMessage);
        // 저장된 채팅 메시지를 반환
        return chatMessage;
    }

    // 주어진 발신자와 수신자의 채팅 메시지를 찾는 메서드
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        // 채팅방 ID를 가져옴. 존재하지 않으면 빈 리스트를 반환
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        // 채팅방 ID가 존재하면 해당 ID로 채팅 메시지를 찾고, 존재하지 않으면 빈 리스트 반환
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
