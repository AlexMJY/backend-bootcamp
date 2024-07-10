package kr.co.jhta.app.springbootex11.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "attached_file")
@Setter
@Getter
@ToString(exclude = "board")
public class BoardAttachedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;
    private String filePath;
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "boardno")
    private Board board;

}
