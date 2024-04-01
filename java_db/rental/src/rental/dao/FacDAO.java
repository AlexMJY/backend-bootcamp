package rental.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import rental.common.DBConn;
import rental.vo.FacVO;
import rental.vo.ResVO;
import rental.vo.ReviewVO;


public class FacDAO {
	private String query;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private boolean result = false;
    private FacVO fvo;
    
    
 // 시설 리스트
    public List<FacVO> selectFac() {
        query = "SELECT facNo, facName, RPAD(facAddr, 40, ' ') facAddr, create_at FROM r_fac";
        List<FacVO> facArray = new ArrayList<FacVO>();
        
        try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                fvo = new FacVO();
                fvo.setFacNo(rs.getInt("facNo"));
                fvo.setFacName(rs.getString("facName"));
                fvo.setFacAddr(rs.getString("facAddr"));
                fvo.setCreateAt(rs.getString("create_at"));
                facArray.add(fvo);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConn.close(pstmt, rs);
        }
        return facArray;
    }
    
    
    // 시설 조회
    public FacVO selectFac(String id) {
    	query = "SELECT * FROM R_FAC WHERE FACNO = ?";
    	FacVO fvo = null;
    	try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                fvo = new FacVO();
                fvo.setFacNo(rs.getInt("facNo"));
                fvo.setFacName(rs.getString("facName"));
                fvo.setFacAddr(rs.getString("facAddr"));
                fvo.setCreateAt(rs.getString("create_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	DBConn.close(pstmt, rs);
        }
    	return fvo;
    }
    
    
    // 시설 등록
    public boolean registerFac(FacVO fvo) {
    	query = "INSERT INTO r_fac (facNo, facName, facAddr, create_at) "
    			+ "VALUES (r_fac_seq.NEXTVAL, ?, ?, TO_DATE(?, 'yyyy-MM-dd'))";
    	try {
    		pstmt = DBConn.getConnection().prepareStatement(query);

    		pstmt.setString(1, fvo.getFacName());
    		pstmt.setString(2, fvo.getFacAddr());
    		pstmt.setString(3, fvo.getCreateAt());
    		
    		result = pstmt.executeUpdate() == 1;
    		DBConn.close(pstmt);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
    // r_res date 조회
    public ArrayList<Integer> rentalDaySelect(FacVO fvo) {
    	
    	ArrayList<Integer> dayList = new ArrayList<>();
    	
    	try {
        	query = "SELECT TO_CHAR(res_date, 'DD') as day FROM r_res "
        			+ "WHERE TRUNC(res_date, 'MM') = TRUNC(ADD_MONTHS(SYSDATE, 1), 'MM') AND "
        			+ "facName = ?";
    		pstmt = DBConn.getConnection().prepareStatement(query);
    		pstmt.setString(1, fvo.getFacName());
    		rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	dayList.add(rs.getInt("day"));
            }
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		DBConn.close(pstmt, rs);
		}
    	return dayList;
    }
    
    // 예약 데이터 INSERT
    public boolean insertRental(ResVO rso) {
    	
    	try {
    		query = "INSERT INTO r_res (resno, userId, facname, res_date) "
    				+ "VALUES (r_res_seq.NEXTVAL, ?, ?, TO_DATE(?, 'yyyy-MM-dd'))";
    		pstmt = DBConn.getConnection().prepareStatement(query);
    		
    		pstmt.setString(1, rso.getUserId());
    		pstmt.setString(2, rso.getFacName());
    		pstmt.setString(3, rso.getResDate());
    		
			result = pstmt.executeUpdate() == 1;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}
    		
    	return result;
    }

    // 예약 데이터 모두 불러오기
    public List<ResVO> resSelect(String id) {
    	
    	List<ResVO> resList = new ArrayList<ResVO>();
		
		try {
			query = "SELECT resNo, userId, facName, res_date, applicationdate FROM r_res "
					+ "WHERE sysdate < res_date AND userId = ? ORDER BY res_date ASC";
			pstmt = DBConn.getConnection().prepareStatement(query);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ResVO rvo = new ResVO();
				
				rvo.setResNo(rs.getInt("resNo"));
				rvo.setUserId(rs.getString("userId"));
				rvo.setFacName(rs.getString("facName"));
				rvo.setResDate(rs.getString("res_date"));
				rvo.setApplicationDate(rs.getString("applicationdate"));
				resList.add(rvo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		
		return resList;
	}

    // 예약 데이터 삭제
    public boolean deleteRental(int resNo) {
    	
    	try {
    		query = "DELETE FROM r_res WHERE resNo = ?";
            
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setInt(1, resNo);
            result = pstmt.executeUpdate() == 1;
    		
    	} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}
    	return result;
    }
    
    
    
    // fac리뷰 가져오기
    public List<ReviewVO> selectFacReview(String facName, int page) {
		List<ReviewVO> reviewList = new ArrayList<>();
		
		try {
			// query = "SELECT userId, facName, content, create_at FROM r_review WHERE facName = ?";
			
			query = "SELECT ROWNUM, CEIL(count / 5) count, userId, facName, content, create_at "
					+ "FROM ("
					+ "    SELECT ROW_NUMBER() OVER (ORDER BY create_at) no, "
					+ "           COUNT(*) OVER () count, "
					+ "           userId, facName, content, create_at "
					+ "    FROM r_review "
					+ "    WHERE facName = ? "
					+ ")"
					+ "WHERE no BETWEEN (? - 1) * 5 + 1 AND ? * 5";
			
			
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, facName);
			pstmt.setInt(2, page);
			pstmt.setInt(3, page);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewVO reviewVo = new ReviewVO();
				
				reviewVo.setUserId(rs.getString("userId"));
				reviewVo.setFacName(rs.getString("facName"));
				reviewVo.setContent(rs.getString("content"));
				reviewVo.setCreateAt(rs.getString("create_at"));
				reviewVo.setCount(rs.getInt("count"));
				
				reviewList.add(reviewVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return reviewList;
	}
    
    // 시설 이용내역
    public List<ResVO> facUseList(String id) {
//		query = "SELECT resno, facname, res_date FROM r_res WHERE avl = 1";
    	
		List<ResVO> usedList = new ArrayList<>();
		try {
			query = "SELECT * FROM r_res WHERE avl = 1 AND userId = ?";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ResVO rvo = new ResVO();
				rvo.setResNo(rs.getInt("resNo"));
				rvo.setUserId(rs.getString("userId"));
				rvo.setFacName(rs.getString("facName"));
				rvo.setResDate(rs.getString("res_Date"));
				usedList.add(rvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return usedList;
    	
    }
    
    
    // 조회
    public Boolean checkReview(String id, int num) {
    	query = "SELECT content FROM r_review WHERE userId= ? AND reviewNo = ?";
    	
    	try {
            pstmt = DBConn.getConnection().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setInt(2, num);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	DBConn.close(pstmt, rs);
        }
    	return result;
    }
    
    
    
    
   public boolean insertReview(ReviewVO reviewVo) {
	   try {
		   query = "INSERT INTO r_review (reviewno, userid, facname, content) VALUES (?, ?, ?, ?)";
		   
		   pstmt = DBConn.getConnection().prepareStatement(query);
		   
		   pstmt.setInt(1, reviewVo.getReviewNo());
		   pstmt.setString(2, reviewVo.getUserId());
		   pstmt.setString(3, reviewVo.getFacName());
		   pstmt.setString(4, reviewVo.getContent());
		   
		   result = pstmt.executeUpdate() == 1;
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   } finally {
		   DBConn.close(pstmt);
	   }
	   return result;
   }
   
   
   // 유저가 쓴 리뷰 가져오기
   public List<ReviewVO> selectReview(String id) {
	   List<ReviewVO> reviewList = new ArrayList<>();
	   
	   try {
		   query = "SELECT reviewNo, userId, facName, RPAD(content, 30, ' ') content, "
		   		+ "create_at, edit_at FROM r_review WHERE userId = ?";
		   pstmt = DBConn.getConnection().prepareStatement(query);
		   pstmt.setString(1, id);
		   
		   rs = pstmt.executeQuery();
		   
		   while (rs.next()) {
			   ReviewVO reviewVo = new ReviewVO();
			   reviewVo.setReviewNo(rs.getInt("reviewno"));
			   reviewVo.setUserId(rs.getString("userid"));
			   reviewVo.setFacName(rs.getString("facName"));
			   reviewVo.setContent(rs.getString("content"));
			   reviewVo.setCreateAt(rs.getString("create_at"));
			   reviewVo.setEditAt(rs.getString("edit_at"));
			   reviewList.add(reviewVo);
		   }
		   
	   } catch (Exception e) {
		   e.printStackTrace();
	   } finally {
		   DBConn.close(pstmt, rs);
	   }	   
	   return reviewList;
   }
   
   public ReviewVO selectReview(int no) {
	   ReviewVO reviewVo = null;
	   try {
		   query = "SELECT reviewno, userid, facname, content, create_at, edit_at FROM r_review WHERE = ?";
		   pstmt = DBConn.getConnection().prepareStatement(query);
		   pstmt.setInt(1, no);
		   
		   rs = pstmt.executeQuery();
		   
		   if (rs.next()) {
			   reviewVo = new ReviewVO();
			   reviewVo.setReviewNo(rs.getInt("reviewno"));
			   reviewVo.setUserId(rs.getString("userid"));
			   reviewVo.setFacName(rs.getString("facname"));
			   reviewVo.setContent(rs.getString("content"));
			   reviewVo.setCreateAt(rs.getString("create_at"));
			   reviewVo.setEditAt(rs.getString("edit_at"));
		   }
	   } catch (Exception e) {
		   e.printStackTrace();
	   } finally {
		   DBConn.close(pstmt, rs);
	   }
	   return reviewVo;
   }
   
   public boolean updateReview(ReviewVO reviewVo, String reviewContent) {
	   try {
		   query = "UPDATE r_review SET content = ?, edit_at = SYSDATE WHERE reviewno = ?";
		   pstmt = DBConn.getConnection().prepareStatement(query);
		   
		   pstmt.setString(1, reviewContent);
		   pstmt.setInt(2, reviewVo.getReviewNo());
		   
		   result = pstmt.executeUpdate() == 1;
	   } catch (Exception e) {
		   e.printStackTrace();
	   } finally {
		   DBConn.close(pstmt);
	   }
	   return result;
   }
    
}
