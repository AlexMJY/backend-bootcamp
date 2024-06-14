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
//        SqlSessionFactory factory = ConnectionManager.factory;  // static으로 올라가기 때문에 ConnectionManager, cm 둘다 사용해도 상관없음
//        SqlSession ss = factory.openSession(true);
//        List<DeptDTO> list = ss.selectList("kr.co.jhta.dept.selectAll");
//        return list;
        return ConnectionManager.factory
                                .openSession(true)
                                .selectList("kr.co.jhta.dept.selectAll");
    }

    @Override
    public DeptDTO selectOne(int deptno) {
//        SqlSessionFactory factory = cm.factory;
//        SqlSession ss = factory.openSession(true);
//        DeptDTO dto =  ss.selectOne("kr.co.jhta.dept.selectOne",no);
//        return dto;
        return cm.factory.openSession(true).selectOne("kr.co.jhta.dept.selectOne",deptno);
    }

    @Override
    public void insertOne(DeptDTO dto) {
//        SqlSessionFactory factory = cm.factory;
//        SqlSession ss = factory.openSession(true);
//        ss.insertOne("kr.co.jhta.dept.insertOne", dto);
        cm.factory.openSession(true).insert("kr.co.jhta.dept.insertOne", dto);
    }

    @Override
    public void updateOne(DeptDTO dto) {
        cm.factory.openSession(true).insert("kr.co.jhta.dept.updateOne", dto);
    }

    @Override
    public void deleteOne(int deptno) {
        cm.factory.openSession(true).insert("kr.co.jhta.dept.deleteOne", deptno);
    }
}