package day03;

import kr.co.jhta.web.vo.DeptVO;

public class TestMain {

	public static void main(String[] args) {
		DeptVO vo = new DeptVO();
		vo.setDeptno(1);
		vo.setDname("IT");
		vo.setLoc("SEOUL");
		
		System.out.println("vo : " + vo);
		System.out.println(vo.getDeptno());
		System.out.println(vo.getDname());
		System.out.println(vo.getLoc());
		
		System.out.println(vo.toString());
		
	}

}
