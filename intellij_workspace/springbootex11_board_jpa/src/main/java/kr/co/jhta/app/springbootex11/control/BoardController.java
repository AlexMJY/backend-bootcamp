package kr.co.jhta.app.springbootex11.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kr.co.jhta.app.springbootex11.domain.Board;
import kr.co.jhta.app.springbootex11.dto.BoardDTO;
import kr.co.jhta.app.springbootex11.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Slf4j // 로깅 기능을 사용하기 위해 추가
@Controller // 이 클래스가 Spring MVC의 컨트롤러임을 나타냄
@RequestMapping("/board") // 기본 경로 설정
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성
public class BoardController {

    private final BoardService boardService; // 의존성 주입을 위해 final로 선언

    @GetMapping("/list") // HTTP GET 요청을 처리하며, 게시물 목록을 보여줌
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "keyword", defaultValue = "none") String keyword) {
        // 전체 목록을 서비스에서 가져옴
        Page<Board> boardPage = boardService.getList(page, keyword);

        // 디버깅을 위한 출력문
        System.out.println("boardPage.getTotalElements() : " + boardPage.getTotalElements());
        System.out.println("boardPage.getNumberOfElements() : " + boardPage.getNumberOfElements());
        System.out.println("boardPage.getNumber() : " + boardPage.getNumber());

        // 페이지네이션을 위한 시작 페이지 계산
        int startPage = Math.max(0, boardPage.getNumber() - 4);
        // 페이지네이션을 위한 끝 페이지 계산
        int endPage = Math.min(boardPage.getTotalPages() - 1, boardPage.getNumber() + 10);

        if (startPage > 5) {
            endPage = Math.min(boardPage.getTotalPages() - 1, boardPage.getNumber() + 4);
        }

        // 모델에 데이터 추가
        model.addAttribute("boardPage", boardPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        if (!keyword.equals("none")) {
            model.addAttribute("keyword", keyword);
        }

        return "board/list"; // 뷰 이름 반환
    }

    @GetMapping("/detail/{no}") // 게시물 상세 정보를 보여주는 메서드
    public String detail(@PathVariable Long no, Model model) {
        Board board = boardService.readOne(no);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/write") // 게시물 작성 폼을 보여주는 메서드
    public String write(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/writeForm";
    }

    @PostMapping("/write") // 게시물 작성 후 저장하는 메서드
    public String write(@Valid @ModelAttribute("boardDTO") BoardDTO dto, BindingResult bindingResult, HttpServletRequest request, @RequestParam("fileUpload") MultipartFile[] files) {
        log.info("files : {}", files.length);
        log.info("전달받은 DTO : " + dto);

        if (bindingResult.hasErrors()) {
            return "board/writeForm";
        }

        // 작성자의 IP 주소를 DTO에 설정
        dto.setIp(request.getRemoteAddr());
        Long registerd = boardService.register(dto, files);
        log.info("등록된 게시물 번호 {}", registerd);
        return "redirect:/board/list"; // 작성 후 게시물 목록으로 리다이렉트
    }

    @GetMapping("/delete/{no}") // 게시물 삭제 메서드
    public String delete(@PathVariable Long no) {
        boardService.delete(no);
        return "redirect:/board/list";
    }

    @GetMapping("/modify") // 게시물 수정 폼을 보여주는 메서드
    public String modify(@RequestParam Long bno, Model model) {
        Board board = boardService.readOne(bno);
        model.addAttribute("dto", board);
        return "board/modifyForm";
    }

    @PostMapping("/modify") // 게시물 수정 후 저장하는 메서드
    public String modifyOk(@ModelAttribute BoardDTO dto, HttpServletRequest request) {
        Board board = boardService.modify(dto);

        // 수정된 게시물의 IP 주소와 수정 날짜를 설정
        String ip = request.getRemoteAddr();
        board.setIp(ip);
        board.setRegDate(LocalDateTime.now());

        log.info("수정 후 : {}", board);

        return "redirect:/board/detail/" + dto.getBno(); // 수정 후 해당 게시물의 상세 페이지로 리다이렉트
    }
}
