package kr.co.jhta.app.springbootex11.repository;

import kr.co.jhta.app.springbootex11.domain.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @DisplayName("1건 추가후 확인")
    @Test
    void test_newdata(){
        Board board = new Board();
        board.setWriter("홍길동");
        board.setTitle("금요일");
        board.setContents("월화수목금금금");
        boardRepository.save(board);

        // 모두 꺼내서 보기
        List<Board> result = boardRepository.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}