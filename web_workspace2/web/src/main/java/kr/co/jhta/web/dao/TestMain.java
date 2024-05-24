package kr.co.jhta.web.dao;

import java.util.ArrayList;

import kr.co.jhta.web.vo.ProductVO;

public class TestMain {
	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		
		ArrayList<ProductVO> list = dao.selectAll();
		
		for (ProductVO vo : list) {
			System.out.println("상품명 : " + vo.getPname());
		}
		System.out.println("총 상품 갯수 : " + list.size());
		
		ProductVO vo = dao.getOne(18);
		System.out.println("18번 상품 : " + vo.getPname());
		
		ArrayList<ProductVO> list2 =  dao.findByName("젤리");
		for (ProductVO vo2 : list2) {
			System.out.println("상품명 : " + vo2.getPname());
		}
	}
	
	
}
