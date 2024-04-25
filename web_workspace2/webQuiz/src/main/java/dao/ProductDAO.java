package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import vo.ProductVO;

public class ProductDAO {
	// 1~3 기본생성자
	// 전체조회 : selectAll();
	// 4~7
	// 1건조회 : getOne(int pno);
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "scott";
	String password = "tiger";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sb = new StringBuffer();
	
	
	public ProductDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("conn : " + conn);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't Find Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB Connection Failed");
			e.printStackTrace();
		}
	} // productDAO() end
	
	public ArrayList<ProductVO> selectAll() {
		sb.setLength(0);
		sb.append("SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile FROM product");
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				ProductVO vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // selectAll() end
	
	public ProductVO getOne(int pno) {
	      sb.setLength(0);
	      sb.append("SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile FROM product WHERE pno = ?"); 
	      ProductVO vo = null;
	      try {
	         // 5
	         PreparedStatement pstmt = conn.prepareStatement(sb.toString());
	         pstmt.setInt(1, pno);
	         // 6
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            int pnum = rs.getInt("pno");
	            String pname = rs.getString("pname");
	            int price = rs.getInt("price");
	            int dcratio = rs.getInt("dcratio");
	            String prodesc = rs.getString("prodesc");
	            int qty = rs.getInt("qty");
	            String imgfile = rs.getString("imgfile");
	            String bigfile = rs.getString("bigfile");
	            vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
	
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	      return vo;
	   }	// getone(int pno) end
	
	public ArrayList<ProductVO> findByName(String p) {
		sb.setLength(0);
		sb.append("SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile FROM product WHERE pname LIKE ?");  
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, "%" + p + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				ProductVO vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // findByName(String p) end
	
	public ArrayList<ProductVO> getData(Set<Integer> key) {
		sb.setLength(0);
		sb.append("SELECT pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile FROM product WHERE pno = ?");
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			for (Integer pno : key ) {
				pstmt.setInt(1, pno);
				rs = pstmt.executeQuery();
				rs.next();
				String pname = rs.getString("pname");
				int price = rs.getInt("price");
				int dcratio = rs.getInt("dcratio");
				String prodesc = rs.getString("prodesc");
				int qty = rs.getInt("qty");
				String imgfile = rs.getString("imgfile");
				String bigfile = rs.getString("bigfile");
				
				ProductVO vo = new ProductVO(pno, pname, price, dcratio, prodesc, qty, imgfile, bigfile);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	} // getData() end
	
	   public void insertOne(ProductVO vo) {
		      sb.setLength(0);
		      sb.append("INSERT INTO product ");
		      sb.append("VALUES (PRODUCT_PNO_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)");

		      try {
		         pstmt = conn.prepareStatement(sb.toString());
		         pstmt.setString(1, vo.getPname());
		         pstmt.setInt(2, vo.getPrice());
		         pstmt.setInt(3, vo.getDcratio());
		         pstmt.setString(4, vo.getProdesc());
		         pstmt.setInt(5, vo.getQty());
		         pstmt.setString(6, vo.getImgfile());
		         pstmt.setString(7, vo.getBigfile());

		         int result = pstmt.executeUpdate();

		         System.out.println("result : " + result);
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
		   }
	
	public ArrayList<ProductVO> getData2(Set<Integer> key) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		key.forEach(n -> list.add(getOne(n)));
		return list;
	} // getData2() end
}
