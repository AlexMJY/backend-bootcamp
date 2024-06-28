package kr.co.jhta.app.springbootex11.service;

import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        Board saved = boardRepository.save(b);
        return saved.getBno();
    }
}
