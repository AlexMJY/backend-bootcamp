package kr.co.jhta.web.spring_web01;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// Controller ==> MVC : ~~ action

public class WelcomeController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav =  new ModelAndView("welcome");
        mav.addObject("data1", "안녕하세요");
        mav.addObject("data2", "welcome");
        mav.addObject("data3", "니하오");
        mav.addObject("data4", "곤니찌와");

        return mav;
    }
}
