package kr.co.jhta.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class ConnectionManager {
    
    // JDBC : Connection
    // MyBatis : SQLSession
    static SqlSessionFactory factory;
    
    // static clock : 생성자보다 이전에 호출됨, 클래스가 로딩되면 메모리에 올라감
    static {
        try {
            Reader r = Resources.getResourceAsReader("sqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(r);
            System.out.println("factory : " + factory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }  // static block end
} // class end
