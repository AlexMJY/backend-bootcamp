package ex3;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//locathost:8080/mvc/mc => ex/hello.jsp (안녕하세요)
// localhost:8080/mvc/mc?type=hello => ex/hello.jsp
// localhost:8080/mvc/mc?type=ip => ex/ip.jsp (접속 IP : XXX)
// localhost:8080/mvc/mc?type=now => ex/now.jsp (현재시간 : XXX) / NowCommand execute()
// localhost:8080/mvc/mc?type=dept => ex/dept.jsp (list : 부서의 목록) / DeptCommand execute()
// JSTL, EL 사용해서 표 만들기


@WebServlet("/mc")
public class MyController3 extends HttpServlet {
	
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("UTF-8");
		
		String type = req.getParameter("type");
		String url = "";
		String msg = "";
		ActionCommand ac = null;
		
		// 모든 커멘드는 execute 함수를 가져야 한다 => 인터페이스로 만들어서 상속 시키기
		if (type == null || type.equals("hello")) {
			// url = "ex3/hello.jsp";
			// msg = "안녕하세요";
			// req.setAttribute("msg", msg);
			ac = new HelloCommand();
		} else if (type.equals("ip")) {
			ac = new IPCommand();
		} else if (type.equals("now")) {
			ac = new NowCommand();
		} else if (type.equals("dept")) {
			ac = new DeptCommand();
		}
		url = ac.execute(req, resp);
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

}
