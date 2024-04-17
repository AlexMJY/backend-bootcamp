package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hobby2.do")
public class Hobby2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHobby(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHobby(req, resp);
	}

	private void doHobby(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 1. 한글처리		
		// 2. 파라미터 값 가져오기
		// 3. 쓰기 객체
		// 4. 출력
		
		String[] hList = req.getParameterValues("hobby");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Hobby</title>");
		out.println("</head>");
		out.println("<body>");
		
		for (String s : hList) {
			out.println("<h3>당신의 취미는 : " + s + "입니다.</h3><br>");
		}
		
		System.out.println("-------------------------------------");
		
//		List<String> list =  Arrays.asList(hList);
//		list.forEach(x -> out.println("<h3>당신의 취미는 : " + x + "입니다.</h3>"));
		
		Arrays.asList(hList).forEach(x -> out.println("<h3>당신의 취미는 : " + x + "입니다.</h3>"));
				
		

		
		out.println("</body>");
		out.println("</html>");
	}
}
