package kr.co.jhta.web.spring_web08;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadController extends HttpServlet {

    final String filePath = "./data";
    FileDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dao = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(FileDAO.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 다운로드할 파일의 경로와 이름 선택
        String n = req.getParameter("no");
        int no = 0;
        if (n != null) {
            no = Integer.parseInt(n);
        }

//        FileDAO dao = new FileDAO();
        FileDTO dto = dao.selectOne(no);
        String path = getServletContext().getRealPath(filePath);
        File downloadFile = new File(path + "/" + dto.getFileName());

        FileInputStream fis = new FileInputStream(downloadFile);

        // 응답 설정

        // 파일의 타입 체크
        String mimeType = getServletContext().getMimeType(downloadFile.getAbsolutePath());

        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        // 파일 타입 브라우저에 보내주기
        resp.setContentType(mimeType);
        resp.setContentLength((int) downloadFile.length());

        // 응답 헤더
        String header = "Content-Disposition: attachment; filename=\""+downloadFile.getName()+"\"";
        resp.setHeader("Content-Disposition", header);
        
        // 파일 내용을 응답으로 전송
        OutputStream os = resp.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1; // 1바이트씩 전송. 안쓰는 -1로 초깃값 설정
        while ((bytesRead = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        fis.close(); // 자원 반납
        os.close();
    }
}
