package kr.co.jhta.app.springbootex09.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Id // pk값, 식별자 등록
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_no_seq") // 스프링부트가 알아서 값을 만들어서 지정
//    @SequenceGenerator(name = "member_no_seq", sequenceName = "member_no_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long no;

    @Column(length = 50)
    private String id;

    @Column(length = 100)
    private String pw;

    private String name;
    private String gender;
    private String motive;
    private String email;

}
