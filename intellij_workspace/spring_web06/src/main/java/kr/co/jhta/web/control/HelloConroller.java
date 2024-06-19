package kr.co.jhta.web.control;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.jhta.dao.CommonDAO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Controller
public class HelloConroller {
    @Autowired
    CommonDAO dao;

    @GetMapping("/helloworld.do")
    public String helloWorld() {
        return "newWorld";
    }

    @GetMapping("/login.do")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login.do")
    public String loginOk(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        // parameter value
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        System.out.println("id : " + id);
        System.out.println("pw : " + pw);

//        boolean result = dao.findByIdAndPw(id, pw);

        model.addAttribute("id", id);

        return "loginOk";
    }
}
