package kr.co.jhta.app.springbootex11.control;

import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("msg", "HELLO");
        return "board/list";
    }

    @GetMapping("/write")
    public String write() {
        return "board/writeForm";
    }

    @PostMapping("/write")
    public String write(Model model, @ModelAttribute BoardDTO dto) {
        log.info("전달받은 DTO : " + dto);
        Long registerd = boardService.register(dto);
        log.info("등록된 게시물 번호 {}", registerd);
        return "redirect:/board/list";
    }
}
