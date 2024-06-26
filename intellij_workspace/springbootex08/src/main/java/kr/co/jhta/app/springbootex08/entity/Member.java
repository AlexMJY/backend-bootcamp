package kr.co.jhta.app.springbootex08.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Member")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id // pk값, 식별자 등록
    @GeneratedValue // 스프링부트가 알아서 값을 만들어서 지정
    @Column(name = "no")
    private Long no;
    private String id;
    private String pw;
    private String name;
    private String gender;
    private String motive;
    private String email;
}
