package kr.co.jhta.app.springbootex09.repository;

import kr.co.jhta.app.springbootex09.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

//    @Test
//    public void testSelectAll() {
//        List<Member> list = memberRepository.findAll();
//        System.out.println("list size : " + list.size());
//        assertEquals(8, list.size());
//    }

    @Test
    public void testFindByName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Member> member = memberRepository.findByName("aaa", pageable);
        System.out.println(member.getTotalElements());
        member.get().forEach(m -> System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName()));
    }

    @Test
    public void testFindByNo() {
        List<Member> list = memberRepository.findByNoBetweenOrderByNameDesc(55, 60);
        list.forEach(m -> System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName()));
    }

    @Test
    @Transactional
    @Commit
    public void testDeleteByNoGreaterThanEqual() {
        System.out.println("번호 60 이상은 삭제");
        memberRepository.deleteByNoGreaterThanEqual(60);
    }

    @Test
    public void testFindByNoLessThan() {
        List<Member> list = memberRepository.findByNoLessThanEqualOrderByNoAsc(54);
        list.forEach(m -> System.out.println(m.getId() + " : " + m.getPw() + " : " + m.getName()));
    }

//    @Test
//    public void testJpql() {
//        Member member = memberRepository.findData(52);
//        System.out.println(member.getId() + " : " + member.getPw() + " : " + member.getName());
//    }

    @Test
    public void testQuery() {
        Member member = memberRepository.findMember("bbb", "bbb");
        System.out.println(member.getId() + " : " + member.getPw() + " : " + member.getName());
    }
}