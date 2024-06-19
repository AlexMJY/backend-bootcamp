package kr.co.jhta.web.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowController implements Controller {
    String lunch;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {


        return new ModelAndView("show", "msg", lunch);
    }
}
