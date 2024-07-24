package com.aico.security_jwt.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CorsMvcConfig 클래스는 WebMvcConfigurer 인터페이스를 구현하여 CORS 설정을 구성합니다.
public class CorsMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        // 모든 경로에 대해 CORS 설정을 추가합니다.
        corsRegistry.addMapping("/**")
                // http://localhost:3000 도메인에서의 요청을 허용합니다.
                .allowedOrigins("http://localhost:3000");
    }
}
