package kr.co.jhta.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoVO {
	private int fno;
	private String filePath;
	private String type;
	private int no;
}
