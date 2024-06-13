package kr.co.jhat.app;

// 자바 클래스로 Bean 정보를 관리

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // <bean id="h" class="kr.co.jhta.app.Helloimple" />
    @Bean(name = "h")
    public Hello helloworld() {
        return new HelloImple();
    }
}
