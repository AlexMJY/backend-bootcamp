package day01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/printGuGuDan.do")
public class GuGuTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");
		
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				out.println(i + " x " + j + " = " + i * j);
				out.println("<br>");
			}
			out.println("<br><br><br>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
}
