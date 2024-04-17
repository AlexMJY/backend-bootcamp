package day02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doLogin(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doLogin(req, resp);
	}

	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body><h2>");
		
		// 파라미터 값 가져오기
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		// id와 pw를 가지고 db에 가서 데이터를 검색 후 존재하는지 여부 판단 (나중에)
		
		out.println("당신의 ID는 " + id + "입니다.");
		out.println("<br><br>");
		out.println("당신의 PW는 " + pw + "입니다.");
		
		out.println("</h2></body>");
		out.println("</html>");
	}

}
