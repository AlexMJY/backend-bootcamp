package day04;

import dao.DeptDAO;

public class TestMain {
	public static void main(String[] args) {
		
		DeptDAO dao = new DeptDAO();
		
		// SELECT
//		DeptVO vo = dao.selectOne(40);
//		System.out.println("vo : " + vo);
		
		
		// INSERT
//		ArrayList<DeptVO> list = dao.selectAll();
//		System.out.println(list);
		
//		DeptVO vo2 = new DeptVO();
//		vo2.setDname("AI");
//		vo2.setLoc("부산");
//		
//		dao.insertOne(vo2);
		
		// UPDATE
//		DeptVO vo3 = new DeptVO(1, "영업", "대구");
//		dao.updateOne(vo3);
		
		// DELETE
		dao.deleteOne(1);
	}
}
