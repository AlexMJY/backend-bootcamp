package kr.co.jhta.app.springbootex11.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// 관리자 페이지 접근용 컨트롤러
@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
