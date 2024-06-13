package kr.co.jhta.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean("p")
    public Printer userPrinter() {
        return new LaserPrinter();
    }
}
