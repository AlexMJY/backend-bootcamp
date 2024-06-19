package kr.co.jhta.web.spring_web08;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

    @GetMapping("/upload.do")
    public String upload() {
        return "uploadForm";
    }
}
