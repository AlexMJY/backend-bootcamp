package kr.co.jhta.web.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.jhta.web.dao.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    CommonDAO dao;

    @GetMapping("/login.do")
    public String login() {
        return "login";
    }

    @PostMapping("/loginOk.do")
    public String loginOk(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
//        String id = request.getParameter("id");
//        String pw = request.getParameter("pw");

//        model.addAttribute("id", id);
//        model.addAttribute("pw", pw);

        boolean result = dao.findByIdAndPw(id, pw);

        if (result != true) {
            return "redirect:login.do";
        }

        // 로그인 성공 후 실행
        session.setAttribute("id", id);
        return "redirect:main.do";

//        return "loginOk";

    }
}
