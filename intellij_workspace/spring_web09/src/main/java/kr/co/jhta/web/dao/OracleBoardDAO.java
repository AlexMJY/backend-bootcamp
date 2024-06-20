package kr.co.jhta.web.dao;

import kr.co.jhta.web.dao.CommonDAO;
import kr.co.jhta.web.dto.BoardDTO;
import kr.co.jhta.web.dto.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// @component : Spring이 관리해라(범용적)
// @Repository : component와 기능은 같음, db와 직접 연결된경우
@Repository
public class OracleBoardDAO implements CommonDAO {

    @Autowired
    private SqlSession ss;

    String driver = "oracle.jdbc.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "scott";
    String password = "tiger";


    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    StringBuffer sb = new StringBuffer();

    @Override
    public List<BoardDTO> selectAll() {
        System.out.println("ss : " + ss);
        //         ss.selectList("별칭 ==> 네임스페이스명");
        return ss.selectList("kr.co.jhta.board.selectAll");

    }

    @Override
    public void insertOne(BoardDTO dto) {
        ss.insert("kr.co.jhta.board.insertOne", dto);
    }

    @Override
    public BoardDTO selectOne(int bno) {
        BoardDTO dto = ss.selectOne("kr.co.jhta.board.selectOne", bno);
        return dto;
    }

    @Override
    public void deleteOne(int bno) {
        ss.delete("kr.co.jhta.board.deleteOne", bno);
    }

}
