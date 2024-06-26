package kr.co.jhta.app.springbootex09.repository;

import kr.co.jhta.app.springbootex09.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
}
