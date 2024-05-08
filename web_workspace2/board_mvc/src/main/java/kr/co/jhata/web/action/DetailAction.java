package kr.co.jhata.web.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.jhata.web.dao.BoardDAO;
import kr.co.jhata.web.vo.BoardVO;

public class DetailAction implements Action {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		// 1. 파라미터 bno 값 가져오기
		// 2. dao
		// 3. 특정 게시물 정보 vo로 넘겨받기
		// 4. req 요청객체에 vo를 속성으로 지정
		
		String b = req.getParameter("bno");
		
		if (b != null) {
			int bno = Integer.parseInt(b);
			
			BoardDAO dao = new BoardDAO();
			BoardVO vo =  dao.getOne(bno);
			
			dao = new BoardDAO();
			dao.raiseHits(bno);
			req.setAttribute("vo", vo);
		}
		
		return "views/detail.jsp";
	}

}
