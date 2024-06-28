package kr.co.jhta.app.springbootex11.repository;

import kr.co.jhta.app.springbootex11.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("1건 추가후 확인")
    void testInsert() {
        Board board = Board.builder().writer("writer1").title("title1").contents("contents1").ip("localhost").regDate(LocalDateTime.now()).hits(1).status(1).build();
        boardRepository.save(board);

        List<Board> result = boardRepository.findAll();
        result.forEach(b -> System.out.println(b.getBno() + " : " + b.getWriter() + " : " + b.getTitle() + " : " + b.getContents()));
    }
}