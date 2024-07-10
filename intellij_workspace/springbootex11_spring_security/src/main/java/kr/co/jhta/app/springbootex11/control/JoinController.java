package kr.co.jhta.app.springbootex11.control;

import kr.co.jhta.app.springbootex11.dto.JoinDTO;
import kr.co.jhta.app.springbootex11.repository.UserRepository;
import kr.co.jhta.app.springbootex11.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;
    private final UserRepository userRepository;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    public void joinProc(JoinDTO dto) {
        log.info(" 사용자명 : {}, 패스워드는 : {}", dto.getUsername(), dto.getPassword());


        // username이 중복이면 회원가입 취소
        boolean existsUser = userRepository.existsByUsername(dto.getUsername());
        if (existsUser) {
            return;
        }

        joinService.joinProcoess(dto); // store to db
//        return "redirect:/login";
    }
}
