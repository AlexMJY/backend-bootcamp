package kr.co.jhta.app.springbootex11.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller // SpringBoot에서 컨트롤러로 사용됨을 나타냄
public class FileDownloadController {

    @Value("${file.upload-dir}") // application.properties 파일에서 설정된 file.upload-dir 값을 가져옴
    private String uploadDirectory;

    @GetMapping("/board/detail/download/{filename}") // HTTP GET 요청을 처리하며, URL 경로 변수로 filename을 받음
    public ResponseEntity<Resource> download(@PathVariable String filename, Model model) throws UnsupportedEncodingException {
        System.out.println("filename : " + filename);

        // 업로드 디렉토리와 파일 이름을 결합하여 파일 객체 생성
        File file = new File(uploadDirectory + "/" + filename);

        // 파일이 존재하지 않는다면 404 Not Found 상태로 응답
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // 파일이 존재하면 FileSystemResource 객체로 변환
        Resource resource = new FileSystemResource(file);

        // 파일 이름을 UTF-8로 인코딩하여 URL에 사용할 수 있게 변환
        String encodingFileName = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodingFileName + "\"");
        headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        System.out.println("resource : " + resource);

        // HTTP 200 OK 상태로 응답하며, 헤더와 파일 리소스를 포함
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}