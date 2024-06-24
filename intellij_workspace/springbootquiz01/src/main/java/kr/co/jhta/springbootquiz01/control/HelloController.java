package kr.co.jhta.springbootquiz01.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@Slf4j
@Controller
public class HelloController {

//    @GetMapping("/hello")
//    public String hello() {
//        log.info("run hello()");
//        return "hello method";
//    }

    @GetMapping("/test")
    public String test(Model model) {
        log.info("test method");
        model.addAttribute("msg", "안녕하세요");
        return "test";
    }

    @GetMapping("/time")
    public String time(Model model) {
        log.info("time method");
        LocalTime lt = LocalTime.now();
        model.addAttribute("nowTime", lt);
        return "time";
    }
}
