package kr.co.jhta.web.control;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.jhta.web.dto.BoardDTO;
import kr.co.jhta.web.service.BoardService;
import kr.co.jhta.web.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController{

    @Autowired
    @Qualifier("mysqlService")
    BoardService service;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(name = "currentPage", defaultValue = "1") int currentPage) {

        // 현재 페이지 번호 => 파라미터에서 가져옴

        // 총 게시물 수
        int totalNumber = service.getTotal();
        // 한 페이지당 게시물 수
        int countPerPage = 10;

        Map<String, Object> map = PageUtil.getPageData(totalNumber, countPerPage, currentPage); // 페이징에 필요한 변수 설정

//        List<BoardDTO> list = service.readAll((Integer) map.get("startNo"), (Integer) map.get("endNo"));
        List<BoardDTO> list = service.readAll(map);

        model.addAttribute("map", map);
        model.addAttribute("list", list);
        
        return "list";
    }

    @GetMapping("/write")
    public String write() {
        System.out.println("111111111111111111");
        return "writeForm";
    }

    @PostMapping("/write")
    public String writeOk(@ModelAttribute BoardDTO dto, HttpServletRequest req) {
        System.out.println("dto : " + dto);

        String ip = req.getRemoteAddr();
        dto.setIp(ip);

        service.write(dto);
        return "redirect:/board/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("bno") int bno, Model model) {
        BoardDTO dto =  service.readOne(bno);
        model.addAttribute("dto", dto);
        return "detail";
    }

    @GetMapping("/delete/{no}")
    public String deleteOk(@PathVariable("no") int bno) {
        service.dropOne(bno);
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("bno") int bno, Model model) {
        BoardDTO dto = service.readOne(bno);
        model.addAttribute("dto", dto);
        return "modifyForm";
    }
    
    @PostMapping("/modify")
    public String modifyOk(@ModelAttribute BoardDTO dto, Model model, HttpServletRequest req) {

//        String ip = req.getRemoteAddr();
//        dto.setIp(ip);

        System.out.println("before update dto : " + dto);

        service.update(dto);

        return "redirect:/board/detail?bno=" + dto.getBno(); // 디테일로 돌아가기
    }

}
