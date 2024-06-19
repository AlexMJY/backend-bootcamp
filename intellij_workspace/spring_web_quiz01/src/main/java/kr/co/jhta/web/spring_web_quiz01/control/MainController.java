package kr.co.jhta.web.spring_web_quiz01.control;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    CommonDAO mdao;

    @GetMapping("/main.do")
    public String main() {
        return "main";
    }

    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:main.do";
    }
}
