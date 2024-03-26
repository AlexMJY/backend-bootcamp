package rental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rental.vo.UserVO;

public class UserDAO {
	private String query;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private boolean result;
    
    public boolean loginChk(String userId, String userPw) { // login check
    	query = "SELECT * FROM user WHERE id = ? AND pw = ?";
    	
    	UserVO uvo = null;
    	try {
    		pstmt = rental.common.DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	return true;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
			rental.common.DBConn.close(pstmt, rs);
		}
    	return false;
    }
    
    public boolean selectPw(String userId, String userEmail) {
    	query = "SELECT * FROM t_member WHERE id = ? AND email = ?";
    	
    	UserVO uvo = null;
    	try {
    		pstmt = rental.common.DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, userEmail);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	return true;
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
			rental.common.DBConn.close(pstmt, rs);
		}
    	return false;
    }
}
