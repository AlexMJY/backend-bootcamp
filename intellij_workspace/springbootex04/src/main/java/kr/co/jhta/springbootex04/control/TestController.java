package kr.co.jhta.springbootex04.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    // 사용자 브라우저에 직접 데이터 전달
//    @ResponseBody
    @GetMapping("/show")
    public String showData() {
        return "{msg: Hello World - showData()}";
    }
}