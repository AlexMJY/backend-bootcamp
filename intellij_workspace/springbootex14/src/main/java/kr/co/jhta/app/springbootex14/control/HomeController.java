package kr.co.jhta.app.springbootex14.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/home")
    public void home() {
        System.out.println("홈으로");
    }
    
    @GetMapping("/about")
    public void about() {
        System.out.println("우리 회사는 월급도 많이 주고 좋아요");
    }

    @GetMapping("/product")
    public void product() {
        System.out.println("아이스크림 팔아요~");
    }

    @GetMapping("/customer")
    public void customer() {
        System.out.println("직원 10명");
    }
}
