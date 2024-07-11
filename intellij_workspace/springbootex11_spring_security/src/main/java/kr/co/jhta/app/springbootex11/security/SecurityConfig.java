package kr.co.jhta.app.springbootex11.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration  // 설정 정보임을 명시 (bean으로 등록됨)
@EnableWebSecurity
public class SecurityConfig {

    // 암호화 관련 bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder를 사용하여 비밀번호를 암호화함
        return new BCryptPasswordEncoder();
    }

    // 시큐리티 필터 체인 설정 bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("=============== SecurityFilterChain ===============");

        // 동시 접속사 차단
        http
                .sessionManagement(auth -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true) 
                        // true: 초과 시 새로운 로그인 차단
                        // false: 초과 시 기존 세션 차단
                );


        http
                .authorizeHttpRequests( auth -> auth
                        // 특정 요청 경로에 대한 접근 권한 설정
                        .requestMatchers("/", "/home", "/login", "/logout", "/join", "/joinProc").permitAll() // 해당 경로는 모든 사용자에게 접근 허용
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin/** 경로는 ADMIN 역할을 가진 사용자만 접근 허용
                        .requestMatchers("/board/**").hasAnyRole("ADMIN", "USER") // /board/** 경로는 ADMIN 또는 USER 역할을 가진 사용자만 접근 허용
                        .anyRequest().authenticated() // 그 외의 모든 요청은 인증된 사용자만 접근 가능
                );

        // 기본 로그인 기능 추가 (현재 주석 처리됨)
//        http.formLogin(Customizer.withDefaults());

        // 커스텀 로그인 페이지 설정
        http
                .formLogin(auth -> auth
                        .loginPage("/login") // 사용자 정의 로그인 페이지 경로
                        .loginProcessingUrl("/loginProc") // 로그인 인증 처리 경로
                        .permitAll()
                        .defaultSuccessUrl("/home") // 로그인 성공 후 이동할 기본 경로
                );

        // 커스텀 로그아웃 페이지 설정
        http
                .logout(auth -> auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .permitAll()
                );


        // CSRF 보호 기능 비활성화 (테스트 시에만 사용, 실제 운영 환경에서는 비활성화 하지 말 것)
        http
                .csrf(auth -> auth.disable());


        http
                .oauth2Login(auth -> auth
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                );

        // 설정된 HttpSecurity 객체를 빌드하여 반환
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
//        log.info("=============== SecurityFilterChain2 ===============");
//
//        return http.build();
//    }
}
