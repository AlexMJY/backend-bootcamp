package kr.co.jhta.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.HTML;

@Controller
public class HomeController {

    @GetMapping("/home.do")
    public ModelAndView home() {
        System.out.println("!!! call home() method !!!");
        return new ModelAndView("home");
    }

    @RequestMapping("/home2.do")
    public ModelAndView home2() {
        System.out.println("!!! call home2() method !!!");
        return new ModelAndView("home2");
    }

    @RequestMapping("/home3.do")
    public String home3() {
        return "data/home3";
    }

    @RequestMapping("/doTest.do")
    public String aaa() {
        System.out.println("call aaa() method");
        return "test2"; // return view name
    }

    @RequestMapping(value = "/doTest2.do", method = RequestMethod.GET)
    public String bbb() {
        return "test3";
    }

    @RequestMapping(value = "/doTest2.do", method = RequestMethod.POST)
    public String ccc() {
        return "test4";
    }

    @GetMapping("/doGet1.do")
    public String ddd() {
        return "getTest";
    }

    @PostMapping("/doPost1.do")
    public String eee() {
        return "postTest";
    }

    @GetMapping("/doGet2.do")
    public String fff(HttpServletRequest request, Model model) {
        String hostName = request.getRemoteAddr();

        // 데이터 전달
        model.addAttribute("hostName", hostName);
        return "getModel";
    }

    @RequestMapping("/hello.do")
    @ResponseBody
    public String ggg() {
        return "<html><body><h1>Hello World</h1></body></html>"; // 사용자에게 뷰가 아니라 데이터(JSON)를 전달
    }

}
