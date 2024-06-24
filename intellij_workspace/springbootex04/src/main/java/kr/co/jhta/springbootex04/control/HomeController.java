package kr.co.jhta.springbootex04.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// logger
@Slf4j
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("======= HomeController.home() =======");
        log.info("Logger ==>>>>>>>>>>>>>>>>>>>>>>>>");
        model.addAttribute("msg", "Hello World");
        return "home";
    }
}
