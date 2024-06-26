package kr.co.jhta.app.springbootex09.repository;

import kr.co.jhta.app.springbootex09.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testSelectAll() {
        List<Member> list = memberRepository.findAll();
        System.out.println("list size : " + list.size());
        assertEquals(8, list.size());
    }

}