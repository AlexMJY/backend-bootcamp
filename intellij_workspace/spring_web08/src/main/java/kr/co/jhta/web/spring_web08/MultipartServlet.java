package kr.co.jhta.web.spring_web08;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@MultipartConfig  // 파일 처리 configuration
@WebServlet("/uploadOk.do")
public class MultipartServlet extends HttpServlet {
    final String UPLOAD_DIRECTORY = "/data";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /data의 파일 저장 디렉터리
        String realPath = getServletContext().getRealPath(UPLOAD_DIRECTORY);
        System.out.println("파일 저장경로 : " + realPath);

        File file = new File(realPath);

        if (!file.exists()) {
            file.mkdirs();
        }
        // 무조건 해당 디렉터리 존재

        String msg = req.getParameter("msg");
        System.out.println("전달한 파라미터 값 : " + msg);

        for (Part part : req.getParts()) {
            String fileName = getFileName(part);
            System.out.println("파일명 : " + fileName);

            if (fileName != null) {
                part.write(realPath + File.separator + fileName);
            }
        }

    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if(content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }
}
