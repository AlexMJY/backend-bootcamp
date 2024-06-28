package kr.co.jhta.app.springbootex11.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bno;
    @Column(length = 30, nullable = false)
    private String writer;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 2000, nullable = false)
    private String contents;
    @Column(name = "regdate", updatable = true)
    private LocalDateTime regDate;
    private int hits;
    private String ip;
    private int status;
}
