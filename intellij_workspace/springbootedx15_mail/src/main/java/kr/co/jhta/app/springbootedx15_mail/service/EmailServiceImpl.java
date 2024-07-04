package kr.co.jhta.app.springbootedx15_mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.co.jhta.app.springbootedx15_mail.entity.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;  // 메일 발송을 위한 JavaMailSender 객체
    private final SpringTemplateEngine springTemplateEngine;  // 템플릿 엔진 객체

    @Override
    public String sendMail(EmailMessage emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();  // MimeMessage 객체 생성
        String authNum = createCode();  // 인증번호 생성

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());  // 메일 수신자 설정
            mimeMessageHelper.setSubject(emailMessage.getSubject());  // 메일 제목 설정
            mimeMessageHelper.setText(setContext(authNum), true);  // 메일 본문 설정 및 HTML 여부 설정
            javaMailSender.send(mimeMessage);  // 메일 발송
            log.info(" ================ SUCCESS ================");
        } catch (MessagingException e) {
            throw new RuntimeException(e);  // 예외 발생 시 RuntimeException으로 변환하여 던짐
        }

        return authNum;  // 인증번호 반환
    }

    // 인증번호 생성 메서드
    public String createCode() {
        return "" + (int) (Math.random() * 10000);  // 0에서 9999 사이의 랜덤 숫자 생성
    }

    // thymeleaf를 통한 HTML 컨텍스트 설정 메서드
    public String setContext(String authNum) {
        Context context = new Context();  // Context 객체 생성
        context.setVariable("authNum", authNum);  // 인증번호를 컨텍스트에 설정
        return springTemplateEngine.process("email", context);  // 'email' 템플릿을 사용하여 HTML 내용 생성 및 반환
    }
}
