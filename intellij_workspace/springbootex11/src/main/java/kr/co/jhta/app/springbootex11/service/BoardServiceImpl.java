package kr.co.jhta.app.springbootex11.service;

import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {
        Board b = new Board();
        b.setTitle(dto.getTitle());
        b.setContents(dto.getContents());
        b.setWriter(dto.getWriter());
        b.setRegDate(LocalDateTime.now());
        b.setIp(dto.getIp());
        b.setHits(0);
        b.setStatus(1);

        Board saved = boardRepository.save(b);

        return saved.getBno();
    }

    @Override
    public List<Board> getList() {
//        List<Board> list = boardRepository.findAll();
//        return list;

        return boardRepository.findAll();
    }

    @Override
    public Board readOne(Long no) {
//        Optional<Board> result = boardRepository.findById(no);
//
//        Board board = null;
//        if (result.isPresent()) {
//            board = result.get();
//        }
//        return board;

        return boardRepository.findById(no).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public void delete(Long no) {
        boardRepository.deleteById(no);
    }

    @Override
    public Board modifyOne(BoardDTO dto) {
        Board board = boardRepository.findById(dto.getBno()).orElseThrow(() -> new IllegalArgumentException());
        board.setWriter(dto.getWriter());
        board.setTitle(dto.getTitle());
        board.setContents(dto.getContents());

        Board saved = boardRepository.save(board);
        return saved;
    }
}
