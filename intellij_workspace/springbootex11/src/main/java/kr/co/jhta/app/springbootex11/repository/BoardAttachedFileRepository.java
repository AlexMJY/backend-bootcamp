package kr.co.jhta.app.springbootex11.repository;

import kr.co.jhta.app.springbootex11.domain.BoardAttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardAttachedFileRepository extends JpaRepository<BoardAttachedFile, Integer> {
}
