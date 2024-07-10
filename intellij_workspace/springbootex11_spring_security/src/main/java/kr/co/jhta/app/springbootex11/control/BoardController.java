package kr.co.jhta.app.springbootex11.control;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0")int page,
                       @RequestParam(value = "keyword", defaultValue = "none") String keyword) {
      //  model.addAttribute("msg", "hello");
        Page<Board> boardPage = boardService.getList(page, keyword);
        // 전체목록을 가지고 전달
        // 서비스 인터페이스 에서 추가   getList()
        // 서비스 구현클래스에서 추가
        // 가져오기
        System.out.println("boardPage.getTotalElements() : " + boardPage.getTotalElements());
        System.out.println(boardPage.getNumberOfElements());
        System.out.println(boardPage.getNumber());

        // 시작 페이지
        int startPage = Math.max(0, boardPage.getNumber() - 4);

        // 끝 페이지
        int endPage = 10;
//        int endPage = boardPage.getTotalPages()-1;

        if(startPage > 5)
            endPage = Math.min(boardPage.getTotalPages()-1, boardPage.getNumber()+4);
        else
            endPage = Math.min(boardPage.getTotalPages()-1, boardPage.getNumber()+10);

        model.addAttribute("boardPage", boardPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        if(!keyword.equals("none")){
            model.addAttribute("keyword", keyword);
        }

        log.info("list: {}", boardPage);
        // 뷰로 전달
        // 뷰에서는 받아온 값을 이용해서 th:each
        return "board/list";
    }

    @GetMapping("/write")
    public String write(Model model) {
        return "board/writeForm";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute BoardDTO dto, HttpServletRequest request,
                        @RequestParam("fileupload")MultipartFile[] files) {
        log.info("files : {}", files.length);


        log.info("전달받은 dto : " + dto.toString());
        dto.setIp(request.getRemoteAddr());
        Long registered = boardService.register(dto, files);
        log.info("등록된게시물번호 {} ",  registered);
        return "redirect:/board/list";
    }
    @GetMapping("/detail/{no}")
    public String detail(@PathVariable Long no, Model model) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>> {} ", no);
        Board board = boardService.readOne(no);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/modify")
    public String modify(Model model, @RequestParam("bno") Long no) {
        Board board = boardService.readOne(no);
        model.addAttribute("board", board);
        return "board/modifyForm";
    }
    @PostMapping("/modify")
    public String modify(@ModelAttribute BoardDTO dto, HttpServletRequest request) {
        System.out.println("dto : " + dto.toString());
        String ip = request.getRemoteAddr();
        dto.setIp(ip);
        dto.setRegDate(LocalDateTime.now());
        boardService.modify(dto);
        return "redirect:/board/list";
    }
    @GetMapping("/delete/{no}")
    public String delete(@PathVariable Long no, Model model) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>> {} ", no);
        return "redirect:/board/list";
    }


}
