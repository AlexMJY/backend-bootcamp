package rental.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import rental.common.DBConn;
import rental.vo.UserVO;

public class UserDAO {
	
	private String query;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private boolean result = false;
	
	
	// 회원 가입.
	public boolean insertUser(UserVO uvo) {
		
		try {
			
			query = "INSERT INTO r_user (userNo, userId, userPw, userName, userEmail, userPhone) "
                    + "VALUES (r_user_seq.NEXTVAL, ?, ?, ?, ?, ?)";
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
	public UserVO loginChk(UserVO uvo) {
        query = "SELECT userName, admin FROM r_user WHERE userId = ? AND userPw = ?";
        
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, uvo.getId());
            pstmt.setString(2, uvo.getPw());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	uvo.setAdmin(rs.getBoolean("admin"));
            	uvo.setName(rs.getString("userName"));
            	
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt, rs);
        }
        return uvo;
    }
    
	// 아이디 찾기
	public String selectId(String name, String email) {
		
		String id = null;
		
		try {
			query = "SELECT userId FROM r_user WHERE userName = ? AND userEmail = ?";
			pstmt = DBConn.getConnection().prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getString("userId");
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
        query = "SELECT userPw FROM r_user WHERE userId = ? AND userEmail = ?";
        String pw = null;
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, userEmail);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	pw = rs.getString("userPw");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt, rs);
        }
        return pw;
    }
    
	// 전체 회원목록
	public List<UserVO> selectUser() {
		List<UserVO> userList = new ArrayList<>();
		
		try {
			query = "SELECT userId, userPw, userName, userEmail, userPhone, create_at FROM r_user";
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserVO uvo = new UserVO();
				
				uvo.setId(rs.getString("userId"));
				uvo.setPw(rs.getString("userPw"));
				uvo.setName(rs.getString("userName"));
				uvo.setEmail(rs.getString("userEmail"));
				uvo.setPhone(rs.getString("userPhone"));
				uvo.setJoinDate(rs.getDate("create_at"));
				userList.add(uvo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		
		return userList;
	}

	// 회원 정보 하나 보기
	public UserVO selectUser(String id) {
		UserVO uvo = null;
		
		try {
			query = "SELECT userId, userPw, userName, userEmail, userPhone, create_at FROM r_user WHERE userId = ?";
			pstmt = DBConn.getConnection().prepareStatement(query);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				uvo = new UserVO();
				uvo.setId(rs.getString("userId"));
				uvo.setPw(rs.getString("userPw"));
				uvo.setName(rs.getString("userName"));
				uvo.setEmail(rs.getString("userEmail"));
				uvo.setPhone(rs.getString("userPhone"));
				uvo.setJoinDate(rs.getDate("create_at"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		
		return uvo;
	}
	
	// 유저 데이터 업데이트
	public boolean updateUser(String type, String value, String id) {
		
		try {
			query = "UPDATE r_user SET " + type +" = ? WHERE userId = ?";
			pstmt = DBConn.getConnection().prepareStatement(query);
			
			pstmt.setString(1, value);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate() == 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}
		
		return result;
	}
	
	
    // 회원삭제 
    public boolean deleteUser(String id) {
        try {
            
            query = "DELETE FROM r_user WHERE userId = ?";
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, id);
            
            result = pstmt.executeUpdate() == 1;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt);
        }
        
        return result;
    }

	
	
	

}
