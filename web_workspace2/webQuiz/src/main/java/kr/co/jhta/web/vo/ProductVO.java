package kr.co.jhta.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
	private int pno;
	private String pname;
	private int price;
	private int dcratio;
	private String prodesc;
	private int qty;
	private String imgfile;
	private String bigfile;
	
	public ProductVO(String pname, int price, int dcratio, String prodesc, int qty, String imgfile, String bigfile) {
		this.pname = pname;
		this.price = price;
		this.dcratio = dcratio;
		this.prodesc = prodesc;
		this.qty = qty;
		this.imgfile = imgfile;
		this.bigfile = bigfile;
		
	}
}
