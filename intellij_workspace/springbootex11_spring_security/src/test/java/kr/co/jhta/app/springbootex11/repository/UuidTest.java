package kr.co.jhta.app.springbootex11.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class UuidTest {

    @Test
    public void testUuid() {
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();

        System.out.println("id1 : " + id1);
        System.out.println("id2 : " + id2);


    }
}
