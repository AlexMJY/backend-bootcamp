package kr.co.jhta.springboot_websocket.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
