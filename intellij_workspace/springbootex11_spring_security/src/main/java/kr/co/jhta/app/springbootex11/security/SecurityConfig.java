package kr.co.jhta.app.springbootex11.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration  // 설정 정보임을 명시 (bean)
@EnableWebSecurity
public class SecurityConfig {

    // 암호화 관련 bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("=============== SecurityFilterChain ===============");

        http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/", "/home", "/login", "/logout", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/board/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated() // 그 외 요청은 인증받은 사람만 접근 가능
                );
        // 로그인 기능 추가
//        http.formLogin(Customizer.withDefaults());

        // 커스텀 로그인 페이지 구현
        http
                .formLogin(auth -> auth
                        .loginPage("/login") // 커스텀 요청 경로
                        .loginProcessingUrl("/loginProc").permitAll() // 로그인 인증 경로
                        .defaultSuccessUrl("/home") // 로그인 성공 후 이동 경로
                );
        
        http
                .csrf(auth -> auth.disable());  // csrf 기능 해제, 테스트할 때만 사용, 보안상 안좋음

        return http.build();
    }
}
