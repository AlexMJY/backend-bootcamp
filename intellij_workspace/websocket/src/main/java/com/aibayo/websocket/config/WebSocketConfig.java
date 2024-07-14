package com.aibayo.websocket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /* WebSocketMessageBrokerConfigurer의 configureMessageBroker 메서드는
    메시지 브로커를 구성하여 특정 주제(prefix)로의 메시지 라우팅을 설정합니다.
    이 메서드를 사용해 애플리케이션 내에서 목적지(prefix)를 지정하고,
    클라이언트가 구독할 수 있는 엔드포인트를 정의합니다.
    예를 들어, "/topic"과 "/queue" 같은 주제를 설정하여 브로드캐스트 또는 1:1 메시징을 지원할 수 있습니다.*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix(("/user"));
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .withSockJS();
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // 기본 MIME 타입을 JSON으로 설정
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(APPLICATION_JSON);
        // Jackson을 사용하여 메시지를 JSON으로 변환하는 메시지 변환기를 생성
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        // 변환기 리스트에 JSON 변환기를 추가
        messageConverters.add(converter);
        // 기본 메시지 변환기 설정을 유지하지 않으므로 false를 반환
        return false;
    }
}
