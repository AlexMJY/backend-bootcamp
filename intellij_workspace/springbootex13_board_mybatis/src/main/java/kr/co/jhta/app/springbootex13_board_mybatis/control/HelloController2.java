package kr.co.jhta.app.springbootex13_board_mybatis.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController2 {

    @GetMapping("/hi")
    public String hi(Model model) {
        model.addAttribute("msg", "장마가 계속");

        return "welcome";
    }
}
