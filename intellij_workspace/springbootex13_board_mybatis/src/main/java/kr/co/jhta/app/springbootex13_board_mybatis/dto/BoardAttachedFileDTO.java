package kr.co.jhta.app.springbootex13_board_mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardAttachedFileDTO {
    private int fileNo;
    private String fileName;
    private String filePath;
    private Long boardNo;
}
