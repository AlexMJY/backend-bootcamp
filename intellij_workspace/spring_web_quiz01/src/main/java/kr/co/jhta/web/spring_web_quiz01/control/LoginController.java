package kr.co.jhta.web.spring_web_quiz01.control;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    CommonDAO mdao;

    @GetMapping("login.do")
    public String login() {
        return "loginForm";
    }

    @PostMapping("login.do")
    public String loginOk(HttpSession session, @RequestParam("id") String id, @RequestParam("pw") String pw) {
//        session.setAttribute("id", id);
//        session.setAttribute("pw", pw);
        System.out.println("loginOk() id : " + id);
        System.out.println("loginOk() pw : " + pw);
        boolean result = mdao.findByIdAndPw(id, pw);

        System.out.println("result : " + result);
        if (result != true) {
            return "redirect:login.do";
        }
        session.setAttribute("id", id);
        return "redirect:main.do";
    }




}
