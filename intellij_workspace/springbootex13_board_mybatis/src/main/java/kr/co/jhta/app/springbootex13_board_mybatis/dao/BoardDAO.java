package kr.co.jhta.app.springbootex13_board_mybatis.dao;

import kr.co.jhta.app.springbootex13_board_mybatis.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface BoardDAO {

    List<BoardDTO> getAll(HashMap<String, Integer> map);

    int addOne(BoardDTO dto);

    BoardDTO getOne(Long no);

    void deleteOne(Long no);

    void modifyOne(BoardDTO dto);

    int getTotal();

    int getTotalByKeyword(String keyword);

    List<BoardDTO> getAllByKeyWord(HashMap<String, Object> map);

    int selectSequence();
}
