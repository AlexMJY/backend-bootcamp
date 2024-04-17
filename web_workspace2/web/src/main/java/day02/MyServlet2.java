package day02;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/myServlet2.do")
public class MyServlet2 extends HttpServlet {
	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출 중");
	}
	

	@Override
	public void destroy() {
		System.out.println("destroy() 호출 중");
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		System.out.println("서비스 호출 중");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title></title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> service() 호출 중 </h1>");		
		out.println("</body>");
		out.println("</html>");
		
	}
}
