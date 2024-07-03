package kr.co.jhta.app.springbootex13_board_mybatis.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SayHelloController {

    @GetMapping("/sayHello")
    public String hello(Model model) {
        model.addAttribute("data", "수요일");
        return "hello";
    }
}
