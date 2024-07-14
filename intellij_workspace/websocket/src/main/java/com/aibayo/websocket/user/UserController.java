package com.aibayo.websocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /*
    @MessageMapping: Spring의 WebSocket을 사용하여 메시지를 처리할 때 사용.
    특정 목적지(destination)로 들어오는 메시지를 처리할 메서드를 정의.
    "/user.addUser" 경로로 들어오는 메시지를 처리하고, 처리 결과를 "/user/topic" 경로로 전송.
    */
    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(@Payload User user) {
        service.saveUser(user); // User 객체를 저장하는 서비스 호출
        return user; // 처리된 User 객체를 반환
    }

    /*
    @MessageMapping: 특정 목적지(destination)로 들어오는 메시지를 처리할 메서드를 정의.
    "/user.disconnectUser" 경로로 들어오는 메시지를 처리하고, 처리 결과를 "/user/topic" 경로로 전송.
    */
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user) {
        service.disconnect(user); // User 객체를 disconnect하는 서비스 호출
        return user; // 처리된 User 객체를 반환
    }

    /*
    @GetMapping: HTTP GET 요청을 처리할 메서드를 정의.
    "/users" 경로로 들어오는 GET 요청을 처리하고, 연결된 사용자 목록을 반환.
    */
    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(service.findConnectedUsers()); // 연결된 사용자 목록을 반환
    }

}
