package kr.co.jhta.app.springbootex09.repository;

import kr.co.jhta.app.springbootex09.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

//    Page<Member> findByNo(Long no, Pageable pageable);
    Page<Member> findByName(String name, Pageable pageable);

    // 파라미터로 넘겨온 번호 미만의 회원들 찾기
    List<Member> findByNoLessThan(int no);

    // 파라미터로 넘겨온 번호 이하의 회원들 찾기
    List<Member> findByNoLessThanEqual(int no);

    List<Member> findByNoLessThanEqualOrderByNoAsc(int no);

    List<Member> findByNoBetween(int no1, int no2);

    List<Member> findByNoGreaterThan(int no);

    List<Member> findByNoBetweenOrderByNameDesc(int no1, int no2);

    void deleteByNoGreaterThanEqual(int no);

    Member findByNameContaining(String name);


    // JPQL 쿼리를 통한 사용자 정의 쿼리
    @Query("SELECT m FROM Member m WHERE m.name LIKE %:name%")
    List<Member> findMember(@Param("name") String name);

    @Query(value = "SELECT * FROM dept WHERE deptno = ?1", nativeQuery = true)
    Member findData(int no);


    // id, pw가 일치하는 사람 1명 찾아서 리턴
    @Query(value = "SELECT * FROM member WHERE id = ?1 AND pw = ?2", nativeQuery = true)
    Member findMember(String id, String pw);


}
