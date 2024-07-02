package kr.co.jhta.app.springbootex13_board_mybatis.dao;

import kr.co.jhta.app.springbootex13_board_mybatis.dto.BoardAttachedFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardAttachedDAO {

    void addOne(BoardAttachedFileDTO dto);
}
