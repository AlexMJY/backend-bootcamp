package kr.co.jhta.web.control;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.jhta.web.dto.BoardDTO;
import kr.co.jhta.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardDTO> list = service.ReadAll();
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

    @GetMapping("/delete")
    public String deleteOk(@RequestParam("bno") int bno) {
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
    public String modifyOk(@ModelAttribute BoardDTO dto) {
        
        return "redirect:/model/"; // 디테일로 돌아가기
    }

}
