package kr.co.jhta.control;

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
public class HelloKoreaController implements Controller {
    NowTime nt;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int hour = nt.getHour();
        StringBuffer m = new StringBuffer();

        if (hour >= 6 && hour <= 10) {
            m.append("안녕하세요");
        } else if (hour >= 10 && hour <= 15) {
            m.append("점심 식사하셨나요?");
        } else {
            m.append("좋은 저녁되세요.");
        }

        return new ModelAndView("helloKorea", "msg", m);
    }
}
