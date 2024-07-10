package kr.co.jhta.app.springbootex11.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_no_seq")
//    @SequenceGenerator(name = "board_no_seq", sequenceName = "board_no_seq",
//            allocationSize = 1, initialValue = 1)
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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardAttachedFile> attachedFiles = new ArrayList<>();


    private int status;



}
