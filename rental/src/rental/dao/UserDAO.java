package rental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import rental.common.DBConn;
import rental.vo.FacVO;
import rental.vo.UserVO;

public class UserDAO {
	private String query;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private boolean result = false;
    private FacVO fvo;
    
    
    
 // 회원 가입.
    public boolean insertUser(UserVO uvo) {
        
        try {
            
            query = "INSERT INTO r_user (userId, userPw, userName, userEmail, userPhone) VALUES (?, ?, ?, ?, ?, SYSDATE)";
            pstmt = DBConn.getConnection().prepareStatement(query);
            
            pstmt.setString(1, uvo.getId());
            pstmt.setString(2, uvo.getPw());
            pstmt.setString(3, uvo.getName());
            pstmt.setString(4, uvo.getEmail());
            pstmt.setString(5, uvo.getPhone());
            
            result = pstmt.executeUpdate() == 1;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt);
        }
        
        
        return result;
    }
    
    // 로그인
    public boolean loginChk(String userId, String userPw) { 
    	query = "SELECT * FROM r_user WHERE id = ? AND pw = ?";
    	
    	UserVO uvo = null;
    	try {
    		pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPw);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	return true;
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
			rental.common.DBConn.close(pstmt, rs);
		}
    	return false;
    }
    

    // 아이디 찾기
    public String selectId(String name, String email) {
        
        String id = null;
        
        try {
            query = "SELECT id FROM r_user WHERE name = ? AND email = ?";
            pstmt = DBConn.getConnection().prepareStatement(query);
            
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                id = rs.getString("id");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt);
        }
        
        return id;
    }
    
    
    // 비밀번호 찾기
    public String selectPw(String userId, String userEmail) {
    	query = "SELECT * FROM t_member WHERE id = ? AND email = ?";
    
    	
    	String pw = null;
    	try {
    		pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, userEmail);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	return rs.getString("pw");
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
			DBConn.close(pstmt, rs);
		}
    	return pw;
    }
    
    public UserVO selelctMember(String id) {
    	UserVO uvo = null;
    	return uvo;
    }
    
    public List<FacVO> selectFac() {
    	query = "SELECT * FROM R_FAC";
    	List<FacVO> array = new ArrayList<FacVO>();
    	try {
    		pstmt = DBConn.getConnection().prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
    			FacVO uvo = new FacVO();
    			uvo.setFacNo(rs.getInt("facNo"));
    			uvo.setFacName(rs.getString("facName"));
    			uvo.setFacAddr(rs.getString("facAddr"));
    			uvo.setCreateDate(rs.getDate("createDate"));
    			
    		}
    		DBConn.close(pstmt, rs);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return array;
    }
    
    public FacVO selectFac(String id) {
    	query = "SELECT * FROM R_FAC WHERE FACNO = " + id;
    	fvo = null;
    	try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                fvo = new FacVO();
                fvo.setFacNo(rs.getInt("facNo"));
                fvo.setFacName(rs.getString("facName"));
                fvo.setFacAddr(rs.getString("facAddr"));
                fvo.setCreateDate(rs.getDate("createDate"));                
            }
            DBConn.close(pstmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return fvo;
    }
    
    
    public boolean updateFac(FacVO uvo) {
    	query = "UPDATE R_FAC SET FACNAME = ? WHERE FACNO = ?";
    	
    	pstmt = DBConn.getConnection().prepareStatement(query);
        
        pstmt.setString(1, uvo.);
        pstmt.setString(2, value);
        pstmt.setString(3, id);
        
        result = pstmt.executeUpdate() == 1;
    	return false;
    }
    
    public boolean deleteFac(FacVO uvo) {
    	return false;
    }
}
