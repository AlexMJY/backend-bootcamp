package kr.co.jhta.app.springbootex08.repository;

import kr.co.jhta.app.springbootex08.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DeptRepository extends JpaRepository<Dept, Integer> {
}
