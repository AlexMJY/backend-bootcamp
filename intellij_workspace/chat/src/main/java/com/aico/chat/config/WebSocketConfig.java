package com.aico.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*클라이언트가 WebSocket 서버에 연결할 수 있는 엔드포인트를 등록
    이 메서드는 STOMP (Simple Text Oriented Messaging Protocol) 프로토콜을 사용하는
    WebSocket 엔드포인트를 정의
    클라이언트는 이 엔드포인트를 통해 서버와 WebSocket 연결을 맺고 메시지를 주고받을 수 있음.

    1. STOMP 엔드포인트 등록: 클라이언트가 WebSocket 연결을 맺을 수 있도록 특정 URL 엔드포인트를 설정
    2. CORS 설정: 필요에 따라 CORS(Cross-Origin Resource Sharing) 설정을 통해 다른 도메인에서의 요청을 허용 가능
    3. SockJS 폴백 옵션: 클라이언트가 WebSocket을 지원하지 않는 경우, SockJS로 폴백(fallback) 메커니즘을 제공
    */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }


    /*메시지 브로커를 구성하여 메시지 라우팅을 관리
    메시지 브로커는 클라이언트 간의 메시지를 라우팅하고, 특정 주제나 큐에 메시지를 전달
    이 메서드를 통해 애플리케이션 내부에서 사용할 메시지 브로커의 동작 정의
    
    1. 애플리케이션 목적지 프리픽스 설정: 클라이언트가 메시지를 보내는 대상 경로의 프리픽스를 설정
    일반적으로 @MessageMapping 어노테이션이 사용되는 경로
    
    2. 브로커 프리픽스 설정: 메시지를 전달할 브로커 경로의 프리픽스를 설정
    예를 들어, topic이나 queue 경로를 설정할 수 있음.*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
