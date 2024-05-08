package kr.co.jhata.web.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jhata.web.dao.BoardDAO;
import kr.co.jhata.web.vo.BoardVO;

public class ModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
//		Object obj = req.getAttribute("vo");
//		String list = (String) obj;
		
		String b = req.getParameter("bno");
		
		if (b != null) {
			int bno = Integer.parseInt(b);
			
			String title = req.getParameter("title");
			String writer = req.getParameter("writer");
			String contents = req.getParameter("contents");
			
			BoardDAO dao = new BoardDAO();
			BoardVO vo = dao.getOne(bno);
			
			vo.setBno(bno);
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContents(contents);
			
			dao = new BoardDAO();
			dao.updateOne(vo);
		}
		
		
		
		return "board.do?cmd=list";
	}

}
