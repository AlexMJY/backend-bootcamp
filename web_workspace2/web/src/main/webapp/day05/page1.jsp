<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page1.jsp</title>
</head>
<body>
	<h2>page1.jsp</h2>
	
	<%
		// jsp ==> _jspService() 안쪽으로 코드가 들어가게 된다.
		
		// _jspServise 의 지역변수 : 내장객체 (미리 선언되어 있음)
		// ex) out, request, response ...
		
		// pageContext : 현재 JSP 페이지의 컨텍스트를 나타내는 객체
		// request : 사용자의 요청 객체와
		// session : 웹 브라우저 정보, 내용 유지를 위한 세션정보 저장 객체
		// application : web application context 정보 저장 객체

		// 내장객체 속성, 값 지정 가능
		pageContext.setAttribute("id1", "aaa");
		request.setAttribute("id2", "bbb");
		session.setAttribute("id3", "ccc");
		application.setAttribute("id4", "ddd");
		
		// 부모 참조변수 = 자식의 참조값
		// 자기가 알고 있는 메서드만 호출 
		Object obj1 = pageContext.getAttribute("id1");
		Object obj2 = request.getAttribute("id2");
		Object obj3 = session.getAttribute("id3");
		Object obj4 = application.getAttribute("id4");
		
		out.println("obj1 : " + obj1 + "<br>");
		out.println("obj2 : " + obj2 + "<br>");
		out.println("obj3 : " + obj3 + "<br>");
		out.println("obj4 : " + obj4 + "<br>");
		
		out.println("<br><br><br>");
	
		
		// 문자열 슬라이싱 하려면 형변환
		String id1 = (String) obj1;
		String id2 = (String) obj2;
		String id3 = (String) obj3;
		String id4 = (String) obj4;
		
		out.println("str1 : " + id1.substring(1, 2) + "<br>");
		out.println("str2 : " + id2.substring(1, 2) + "<br>");
		out.println("str3 : " + id3.substring(1, 2) + "<br>");
		out.println("str4 : " + id4.substring(1, 2) + "<br>");


		// forward 방식으로 page2.jsp 이동
		// RequestDispatcher rd = request.getRequestDispatcher("page2.jsp");
		// rd.forward(request, response);
		
		response.sendRedirect("page2.jsp");
	
	%>
</body>
</html>