package day1;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cumValue.do")
public class CumValue extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doCum(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doCum(req, resp);
	}

	private void doCum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int num = Integer.parseInt(req.getParameter("input"));
		int counter = 0;
		
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");
		
		for (int i = 1; i <= num; i++) {
			counter += i;
		}
		out.println("누적 값 : " + counter);
		
		out.println("</body>");
		out.println("</html>");
	}

}
