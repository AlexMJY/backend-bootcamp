package kr.co.jhta.web.spring_web08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UploadController {
    @Autowired
    FileDAO dao;

    @GetMapping("/upload.do")
    public String upload() {
        return "uploadForm";
    }

    @GetMapping("/fileList.do")
    public String fileList(Model model) {
        List<FileDTO> list = dao.selectAll();
        model.addAttribute("list", list);

//        String folderPath = "D:\\dev\\intellij_workspace\\spring_web08\\target\\spring_web08-1.0-SNAPSHOT\\data";


        return "fileList";
    }
}
