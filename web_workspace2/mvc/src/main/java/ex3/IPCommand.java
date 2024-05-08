package ex3;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IPCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("msg", "IP : " + req.getRemoteAddr());
		// 리턴값은 찾아가야할 JSP의 위치 반환
		return "ex3/ip.jsp";
	}

}
