package kr.co.jhta.dao;

import kr.co.jhta.dto.DeptDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DeptDAO implements Dao {
    ConnectionManager cm;

    public void setCm(ConnectionManager cm) {
        this.cm = cm;
    }

    @Override
    public List<DeptDTO> selectAll() {
        SqlSessionFactory factory = ConnectionManager.factory;
        SqlSession ss = factory.openSession(true);
        List<DeptDTO> list = ss.selectList("kr.co.jhta.dept.selectAll");
        return list;
    }

    @Override
    public DeptDTO selectOne() {
        return null;
    }

    @Override
    public void insertOne(DeptDTO dto) {

    }

    @Override
    public void updateOne(DeptDTO dto) {

    }

    @Override
    public void deleteOne(int no) {

    }
}
