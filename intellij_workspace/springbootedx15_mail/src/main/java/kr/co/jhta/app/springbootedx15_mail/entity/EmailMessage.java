package kr.co.jhta.app.springbootedx15_mail.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailMessage {
    private String to;
    private String subject;
    private String message;
}
