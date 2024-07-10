package kr.co.jhta.app.springbootex11.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping( value = {"/", "/home"})
    public String home() {
        return "home";
    }
}
