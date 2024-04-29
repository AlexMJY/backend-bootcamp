package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 싱글톤
// private 생성자, 생성자를 리턴하는 private static 변수, public 함수로 변수 리턴
public class DBConnection {
	
	private static DBConnection instance = new DBConnection();
	
	
	private DBConnection() {  // 위치한 클래스에서만 사용
	}
	
	public static DBConnection getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		// JNDI (Java Naming and Directory Interface)
		// 디렉토리 서비스에서 제공하는 데이터 및 객체를 발견하고 참고하기 위한 자바API
		
		Context initCtx;
		Connection conn = null;
		try {
			initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");  
			DataSource ds = (DataSource) ctx.lookup("myoracle");  // context.xml 에서 name이 myoracle인 내용을 찾고 global에 담긴 애를 server.xml에서 찾아서 DB 연결
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}
