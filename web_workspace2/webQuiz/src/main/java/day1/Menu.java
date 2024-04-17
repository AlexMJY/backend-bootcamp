package day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/menu2.do")
public class Menu extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		selectMenu(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		selectMenu(req, resp);
	}

	private void selectMenu(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>menu select</title>");
		out.println("</head>");
		out.println("<body>");
		
		String[] mList = req.getParameterValues("menu");
		Arrays.asList(mList).forEach(x -> out.println("<h3> 선택한 메뉴는 : " + x + "입니다.</h3>"));
		
		out.println("</body>");
		out.println("</html>");
	}

}
