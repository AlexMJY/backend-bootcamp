package kr.co.jhta.app.springbootex11.repository;

import kr.co.jhta.app.springbootex11.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingOrderByBnoDesc(String title, Pageable pageable);


    // JPQL : entity를 기준으로 만든 QL

    @Query("SELECT b FROM Board b WHERE b.title like %:keyword% or b.contents like %:keyword% or b.writer like %:keyword%")
    Page<Board> findAllLikeKeyword(@Param("keyword") String keyword, Pageable pageable);
}
