package kr.co.jhta.app.springbootex12.repository;

import kr.co.jhta.app.springbootex12.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {
}
