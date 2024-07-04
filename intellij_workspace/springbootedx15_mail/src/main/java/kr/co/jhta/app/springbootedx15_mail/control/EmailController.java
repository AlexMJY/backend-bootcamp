package kr.co.jhta.app.springbootedx15_mail.control;

import kr.co.jhta.app.springbootedx15_mail.entity.EmailMessage;
import kr.co.jhta.app.springbootedx15_mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;  // EmailService 객체 주입

    @PostMapping("/sendMail/email")
    public String sendMail(@RequestParam String receiver, Model model) {

        EmailMessage emailMessage = EmailMessage.builder()  // 이메일 메시지 객체 생성
                .to(receiver)  // 수신자 설정
                .message("message")  // 메시지 설정
                .subject("인증 코드 발송")  // 메일 제목 설정
                .build();

        String code = emailService.sendMail(emailMessage);  // 이메일 발송 및 인증 코드 생성

        model.addAttribute("code", code);  // 모델에 인증 코드 추가
        return "verify";  // 인증 코드 확인 페이지로 이동
    }

    @GetMapping("/form")
    public String form(Model model) {
        return "form";  // 이메일 입력 폼 페이지로 이동
    }
}
