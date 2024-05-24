package kr.co.jhta.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.jhta.web.vo.EmpVO;

public class EmpDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	public EmpDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("can not find Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB connect failure");
			e.printStackTrace();
		}
	}
	
	public ArrayList<EmpVO> selectAll() {
		sb.setLength(0);
		sb.append("SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno FROM emp ");
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				int mgr= rs.getInt("mgr");
				Date hiredate = rs.getDate("hiredate");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");
				EmpVO vo = new EmpVO(empno, ename, job, mgr, hiredate, sal, comm, deptno);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}  // selectAll() End
	
		public EmpVO selectOne(int empno) {
			sb.setLength(0);
			sb.append("SELECT * FROM emp WHERE empno = ?");  
			EmpVO vo = null;
			
			try {
				// 5
				pstmt = conn.prepareStatement(sb.toString());
				
				pstmt.setInt(1, empno);  // 첫번째 ?에 값 할당
				
				// 6
				rs = pstmt.executeQuery();
				while (rs.next()) {
					// int eno = rs.getInt("empno");
					String name = rs.getString("ename");
					int sal = rs.getInt("sal");
					String job = rs.getString("job");
					
					vo = new EmpVO();
					vo.setEmpno(empno);
					vo.setEname(name);
					vo.setSal(sal);
					vo.setJob(job);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return vo;
		}
}
