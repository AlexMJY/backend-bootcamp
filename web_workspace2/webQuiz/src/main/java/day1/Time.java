package day1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/time2.do")
public class Time extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");
		
		Date today =  new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		out.println(sdf.format(today));
		
		out.println("<br><br>");
		
		out.println(today.getYear() + "년 " + (today.getMonth() + 1) + "월 " +  today.getDate() + "일 " + today.getHours() + "시 " + today.getMinutes() + "분 " + today.getSeconds() + "초");
		out.println("</body>");
		out.println("</html>");
	}
}
