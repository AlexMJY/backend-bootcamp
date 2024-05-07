package ex3;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NowCommand {

	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss a");
		
		
		
		req.setAttribute("msg", "오늘 날짜 : " + sdf.format(today));
		
		return "ex3/now.jsp"; 
	}

}
