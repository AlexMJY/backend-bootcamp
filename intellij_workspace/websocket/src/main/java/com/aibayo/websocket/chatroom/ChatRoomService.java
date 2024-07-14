package com.aibayo.websocket.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    // 주어진 발신자와 수신자 ID로 채팅방 ID를 가져오는 메서드
    // 만약 채팅방이 존재하지 않고 createNewRoomIfNotExists가 true이면 새로운 채팅방을 생성
    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
        // 주어진 발신자와 수신자 ID로 채팅방을 찾고, 존재하면 채팅방 ID를 반환
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                // 채팅방이 존재하지 않으면, 새로운 채팅방을 생성할지 여부를 확인
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId); // 새로운 채팅방 ID를 반환
                    }
                    return Optional.empty(); // 새로운 채팅방을 생성하지 않으면 빈 값 반환
                });
    }

    // 발신자와 수신자 ID로 새로운 채팅방 ID를 생성하는 메서드
    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId); // 채팅방 ID 생성

        // 발신자와 수신자 정보를 사용하여 채팅방 객체를 생성
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        // 수신자와 발신자 정보를 반대로 사용하여 또 다른 채팅방 객체를 생성
        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        // 두 개의 채팅방 객체를 저장소에 저장
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return chatId; // 생성된 채팅방 ID 반환
    }
}
