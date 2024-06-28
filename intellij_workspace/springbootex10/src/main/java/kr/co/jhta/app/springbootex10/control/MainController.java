package kr.co.jhta.app.springbootex10.control;

import jakarta.servlet.annotation.ServletSecurity;
import kr.co.jhta.app.springbootex10.domain.Dept;
import kr.co.jhta.app.springbootex10.domain.Emp;
import kr.co.jhta.app.springbootex10.repository.DeptRepository;
import kr.co.jhta.app.springbootex10.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/info")
@RequiredArgsConstructor
public class MainController {
    private final EmpRepository empRepository;
    private final DeptRepository deptRepository;

    @GetMapping("/empInfo")
    public String empInfo(Model model) {
        List<Emp> list = empRepository.findAll();
        model.addAttribute("list", list);
        return "empInfo";
    }

    @GetMapping("/getOne/{empno}")
    public String deptInfo(@PathVariable Long empno, Model model) {
        Emp emp = empRepository.findByEmpno(empno);

        Dept dept = deptRepository.findById(emp.getDept().getDeptno()).orElse(null);
        model.addAttribute("dept", dept);
        return "deptInfo";
    }

}
