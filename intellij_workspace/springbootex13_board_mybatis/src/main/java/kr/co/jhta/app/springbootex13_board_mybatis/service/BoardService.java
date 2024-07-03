package kr.co.jhta.app.springbootex13_board_mybatis.service;


//import kr.co.jhta.app.springbootex13_board_mybatis.domain.Board;

import kr.co.jhta.app.springbootex13_board_mybatis.dto.BoardAttachedFileDTO;
import kr.co.jhta.app.springbootex13_board_mybatis.dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    List<BoardDTO> getList(int startNo, int endNo);

    void register(BoardDTO dto, MultipartFile[] files);

    BoardDTO getOne(Long no);

    void deleteOne(Long no);

    void modifyOne(BoardDTO dto);

    int getTotal();

    int getTotalByKeyword(String keyword);

    List<BoardDTO> getListByKeyword(int startNo, int endNo, String keyword);

    List<BoardAttachedFileDTO> readFiles(Long bno);


//    Long register(BoardDTO dto, MultipartFile[] files);
//
//    Page<Board> getList(int page, String keyword);
//
//    Board readOne(Long no);
//
//    void delete(Long no);
//
//    Board modify(BoardDTO dto);
}
