package com.aico.security_jwt.config;

import com.aico.security_jwt.jwt.JWTFilter;
import com.aico.security_jwt.jwt.JWTUtil;
import com.aico.security_jwt.jwt.LoginFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration // 이 클래스가 설정 클래스임을 나타냄
@EnableWebSecurity // 웹 보안을 활성화
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
public class SecurityConfig {

    // AuthenticationManager를 구성하는 데 필요한 설정을 포함한 객체
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    @Bean // AuthenticationManager 빈 생성
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean // BCryptPasswordEncoder 빈 생성, 비밀번호 암호화에 사용
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // SecurityFilterChain 빈 생성
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // 허용할 도메인 설정
                        configuration.setAllowedMethods(Collections.singletonList("*")); // 모든 HTTP 메서드 허용 (GET, POST, etc.)
                        configuration.setAllowCredentials(true); // 자격 증명 허용 (쿠키, 인증 헤더 등)
                        configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 헤더 허용
                        configuration.setMaxAge(3600L); // CORS 관련 정보를 캐시할 시간을 지정

                        return configuration;
                    }
                })));

        http // CSRF 보호 비활성화
                .csrf((csrf) -> csrf.disable());

        http // 기본 폼 로그인 비활성화
                .formLogin((form) -> form.disable());

        http // HTTP 기본 인증 비활성화
                .httpBasic((basic) -> basic.disable());

        http // 경로별 인가 설정
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll() // 로그인, 홈, 회원가입 경로는 모든 사용자에게 허용
                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증 필요
                );

        http // JWT 검증
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        http // 커스텀 로그인 필터 추가
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class); // LoginFilter를 UsernamePasswordAuthenticationFilter 위치에 추가
        // AuthenticationConfiguration: SpringSecurity에서 제공하는 설정 객체로, AuthenticationManager를 구성하는 데 필요한 정보를 제공

        http // 세션 관리 설정
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음 (무상태)
                );

        return http.build(); // 설정된 SecurityFilterChain을 빌드하여 반환
    }
}
