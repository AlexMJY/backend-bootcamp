package kr.co.jhta.app.springbootex08.repository;

import kr.co.jhta.app.springbootex08.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// MemberRepositoryimple
// 추상메서드
public interface MemberRepository extends JpaRepository<Member, Long> {

    // insert: save (entity type)
    // select: findById (key type), getOne(key type)
    // update: save (entity type)
    // delete: deleteById (key type), delete (entity type)
}
