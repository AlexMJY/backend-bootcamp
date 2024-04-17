package day01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// localhost:8080/web/gugudan.do : 구구단 3단 출력
// 서브릿

@WebServlet("/gugudan.do")
public class GuGuDan3 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GuGuDan3 서블릿 실행 중");
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head><title>GuGuDan3</title></head>");
		out.println("<body>");
		for (int i = 1; i <= 9; i++) {
			out.println("<h3> 3 X " + i + " = " + i * 3 + "</h3>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
