package kr.co.jhta.app.springbootex11.control;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.ValueExp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "keyword", defaultValue = "none") String keyword) {
        // 전체목록 전달
        // 서비스 인터페이스에서 추가 getList()
        // 서비스 구현클래스에서 추가
        // 가져오기
        Page<Board> boardPage = boardService.getList(page, keyword);

        System.out.println("boardPage.getTotalElements() : " + boardPage.getTotalElements());
        System.out.println("boardPage.getNumberOfElements() : " + boardPage.getNumberOfElements());
        System.out.println("boardPage.getNumber() : " + boardPage.getNumber());

        // 시작페이지
        int startPage = Math.max(0, boardPage.getNumber() - 4);
        // 끝 페이지
        int endPage = 10;

        if (startPage > 5)
            endPage = Math.min(boardPage.getTotalPages() - 1, boardPage.getNumber() + 4);
        else
            endPage = Math.min(boardPage.getTotalPages() - 1, boardPage.getNumber() + 10);

        model.addAttribute("boardPage", boardPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        if (!keyword.equals("none")) {
            model.addAttribute("keyword", keyword);
        }

        return "board/list";
    }

    @GetMapping("/detail/{no}")
    public String detail(@PathVariable Long no, Model model) {
        Board board = boardService.readOne(no);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/write")
    public String write() {
        return "board/writeForm";
    }

    @PostMapping("/write")
    public String write(Model model, @ModelAttribute BoardDTO dto, HttpServletRequest request, @RequestParam("fileUpload")MultipartFile[] files) {
        log.info("files : {}", files.length);
        log.info("전달받은 DTO : " + dto);

        dto.setIp(request.getRemoteAddr());
        Long registerd = boardService.register(dto, files);
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
    public String modifyOk(@ModelAttribute BoardDTO dto, HttpServletRequest request) {
        Board board = boardService.modify(dto);

        String ip = request.getRemoteAddr();
        board.setIp(ip);
        board.setRegDate(LocalDateTime.now());

        log.info("수정 후 : {}", board);

        return "redirect:/board/detail/" + dto.getBno();
    }

}
