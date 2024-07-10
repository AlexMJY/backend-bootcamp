package kr.co.jhta.app.springbootex11.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder() {
        final String password = "tiger";
        final String encodePassword = passwordEncoder.encode(password);
        // 인코딩 된 패스워드
        log.info("============= encodePassword : {} =============", encodePassword);

        boolean matchResult = passwordEncoder.matches(password, encodePassword);
        log.info("============= matchResult : {} =============", matchResult);
        assertTrue(matchResult);
    }
}