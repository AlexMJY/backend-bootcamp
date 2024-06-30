package kr.co.jhta.app.springbootex11.control;

import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        // 전체목록 전달
        // 서비스 인터페이스에서 추가 getList()
        // 서비스 구현클래스에서 추가
        // 가져오기
        List<Board> list = boardService.getList();

        model.addAttribute("list", list);
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

    @GetMapping("/delete/{no}")
    public String delete(@PathVariable Long no) {
        boardService.delete(no);
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam Long bno, Model model) {
        Board board = boardService.readOne(bno);
        model.addAttribute("dto", board);
        return "board/modifyForm";
    }

    @PostMapping("/modify")
    public String modifyOk(@ModelAttribute BoardDTO dto) {
        Board board = boardService.modifyOne(dto);
        log.info("수정 후 : {}", board);
        return "redirect:/board/detail/" + dto.getBno();
    }

}
