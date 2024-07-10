package kr.co.jhta.app.springbootex11.repository;

import kr.co.jhta.app.springbootex11.domain.BoardAttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardAttachedFileRepository
        extends JpaRepository<BoardAttachedFile, Integer> {
}
