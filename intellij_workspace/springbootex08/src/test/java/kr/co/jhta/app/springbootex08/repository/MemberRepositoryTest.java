package kr.co.jhta.app.springbootex08.repository;

import kr.co.jhta.app.springbootex08.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testClass() {
        System.out.println("MemberRepository >>>>" + memberRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        Member m1 = Member.builder()
                .name("MJY")
                .gender("Male")
                .email("MJY@gmail.com")
                .motive("news")
                .pw("1234")
                .id("abc")
                .build();
        memberRepository.save(m1);

    }

    @Test
    public void testSelect() {
        Long no = 1L;
        Optional<Member> result = memberRepository.findById(no);

        if (result.isPresent()) {
            Member m1 = result.get();
            System.out.println("=========== " + m1.getName() + " ===========");
        }
    }

//    @DisplayName("다른 방식의 조회")
//    @Test
//    public void testSelect2() {
//        Long no = 1L;
//        Member m2 = memberRepository.getOne(no);
//        System.out.println("=========== " + m2.getName() + " ===========");
//    }

    @Test
    public void testUpdate() {
        Member member = Member.builder().no(1L).motive("search").build();
        System.out.println(memberRepository.save(member));
    }

    @Test
    public void testDelete() {
        Long no = 52L;
        memberRepository.deleteById(no);
    }

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("no").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);
        Page<Member> result = memberRepository.findAll(pageable);
        result.get().forEach(member -> {
            System.out.println(member);
        });
    }

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Member> result = memberRepository.findAll(pageable);
        System.out.println("result : " + result);
        System.out.println("getTotalPages : " + result.getTotalPages()); // 총 페이지 번수
        System.out.println("getTotalElements : " + result.getTotalElements()); // 전체 갯수
        System.out.println("getNumber : " + result.getNumber());  // 현재 페이지 번호
        System.out.println("getSize : " + result.getSize());  // 페이지당 데이터 갯수
        System.out.println("hasNext : " + result.hasNext());  // 다음 페이지 존재 여부
        System.out.println("hasPrevious : " + result.hasPrevious());  // 이전 페이지 존재 여부
        System.out.println("======================================");
        for (Member m : result.getContent()) {
            System.out.println(m);
        }
    }

    @Test
    public void testSort2() {
        Sort sort1 = Sort.by("no").descending();
        Sort sort2 = Sort.by("name").ascending();

        Sort sortAll = sort1.and(sort2);
        Pageable pageable = PageRequest.of(0, 10, sortAll);
        Page<Member> result = memberRepository.findAll(pageable);
        result.get().forEach(member -> System.out.println(member));

    }
}