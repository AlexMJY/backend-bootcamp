package day02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// localhost:8080/web/bigChance.do
// 로또 번호 6자리 숫자..

// 이미지로 출력
// <img src ="" alt="" />
// 

@WebServlet("/bigCance.do")
public class bigChance extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doBigChance(resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doBigChance(resp);
	}

	private void doBigChance(HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>인생은 한방</title>");
		out.println("</head>");
		out.println("<body>");
		
		Lotto lt = new Lotto();
		for (int i : lt.print2()) {
			out.println("<img src = './images/ball" + i + ".png'></img>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
