package kr.co.jhta.web.control;

import jakarta.servlet.http.HttpSession;
import kr.co.jhta.web.dao.CommonDAO;
import kr.co.jhta.web.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {
    @Autowired
    CommonDAO dao;

    @GetMapping("/join.do")
    public String join() {
        return "join";
    }

    @PostMapping("/join.do")
    public String joinOk(@ModelAttribute MemberDTO dto) {
        // @ModelParam은 url형식이고 String 타입으로 만들어야 됨 MemberDto로 받으려면 @ModelBody 사용해야 됨
        // 근데 @ModelBody는 json 형식인데 url로 넘겨줘서 오류 생김
        // 이 문제를 해결한게 @ModelAttribute
        // @RequestParam과 동일한 방식으로 동작하면서, 폼 데이터도 처리할 수 있음
        System.out.println(dto);
        System.out.println(dto.getId());

        dao.insert(dto);



        return "redirect:main.do";
    }
}
