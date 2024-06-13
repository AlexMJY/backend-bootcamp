package kr.co.jhta.app.dao;

import kr.co.jhta.app.dto.DeptDTO;

import java.sql.Connection;
import java.util.List;

public interface CommonDAO {

	public void connect();
	public List<DeptDTO> selectAll();
	public DeptDTO selectOne(int deptno);
	public void insertOne(DeptDTO dto);
	public void updateOne(DeptDTO dto);
	public void deleteOne(int deptno);
	public void close();
	
}
