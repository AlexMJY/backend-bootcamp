package kr.co.jhta.app.springbootex10.repository;


import kr.co.jhta.app.springbootex10.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {


}
