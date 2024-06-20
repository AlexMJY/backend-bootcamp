package kr.co.jhta.web.spring_web08;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FilenameFilter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
//@NoArgsConstructor
@Data
public class FileDAO {
    String driver =  "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    String user = "scott";
    String password = "tiger";

    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    StringBuffer sb = new StringBuffer();

    public FileDAO() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("conn : " + conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void insert(String filename) {
        sb.setLength(0);
        sb.append("INSERT INTO uploadfile VALUES (file_seq.NEXTVAL, ?)");
        try {
            pstmt =  conn.prepareStatement(sb.toString());
            pstmt.setString(1, filename);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FileDTO selectOne(int no) {
        sb.setLength(0);
        sb.append("SELECT no, filename FROM uploadfile WHERE no = ?");
        FileDTO dto = new FileDTO();
        try {
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("no");
                String fileName = rs.getString("filename");
                dto.setNo(n);
                dto.setFileName(fileName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dto;
    }

    public List<FileDTO> selectAll() {
        List<FileDTO> list = new ArrayList<>();

        sb.setLength(0);
        sb.append("SELECT no, filename FROM uploadfile");
        try {
            pstmt = conn.prepareStatement(sb.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("no");
                String fileName = rs.getString("filename");
                FileDTO dto = new FileDTO(no, fileName);
                list.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
