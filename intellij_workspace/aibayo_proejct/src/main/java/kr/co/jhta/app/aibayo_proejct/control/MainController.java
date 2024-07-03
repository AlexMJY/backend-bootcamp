package kr.co.jhta.app.aibayo_proejct.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/chatroom")
    public String chatroom() {
        return "chatroom/index";
    }

    @GetMapping("/choolsuk")
    public String choolsuk() {
        return "choolsuk/index";
    }

    @GetMapping("/classManage")
    public String classManage() {
        return "classManage/index";
    }
}
