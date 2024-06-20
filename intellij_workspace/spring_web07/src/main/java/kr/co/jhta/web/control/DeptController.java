package kr.co.jhta.web.control;

import kr.co.jhta.web.dao.CommonDAO;
import kr.co.jhta.web.dto.DeptDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeptController {

    private final CommonDAO dao;

    @GetMapping("/showDept.do")
    public String showDept(Model model) {

        List<DeptDTO> list =  dao.deptList();
        model.addAttribute("list", list);

        return "deptList";
    }
}
