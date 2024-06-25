package kr.co.jhta.app.springbootex07.control;

import kr.co.jhta.app.springbootex07.dao.OracleDAO;
import kr.co.jhta.app.springbootex07.dto.DeptDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/list")
@RequiredArgsConstructor
public class MainController {

    private final OracleDAO dao;


    // /list + / ==> /list/
    // /list + ' ' ==> /list
    @GetMapping("")
    public String list(Model model) {
        log.info("dao :: " + dao);
        List<DeptDTO> list = dao.selectDeptList();
        model.addAttribute("dlist", list);
        return "list";
    }

    // /list + /one ==> /list/one
    @GetMapping( "/one/{deptno}")
    public String one(@PathVariable int deptno, Model model) {
        DeptDTO dto = dao.selectOne(deptno);
        model.addAttribute("dto", dto);
        return "one";
    }
}
