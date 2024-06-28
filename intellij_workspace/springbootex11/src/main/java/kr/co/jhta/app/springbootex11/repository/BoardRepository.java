package kr.co.jhta.app.springbootex11.repository;

import kr.co.jhta.app.springbootex11.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
