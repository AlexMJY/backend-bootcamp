package kr.co.jhta.app.springbootex11.service;


import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

    Long register(BoardDTO dto, MultipartFile[] files);

    Page<Board> getList(int page, String keyword);

    Board readOne(Long no);

    void delete(Long no);

    Board modify(BoardDTO dto);
}
