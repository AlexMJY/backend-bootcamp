<%@page import="dao.FileInfoDAO"%>
<%@page import="dao.FileDAO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
</head>
<body>
	
	<%
		// upload 디렉토리의 실제 경로 얻어오기
		String saveDir = application.getRealPath("/upload");
	
		out.println(saveDir);
		
		
		int maxFileSize = 1024 * 1024 * 30;
		
		// cos.jar : MultipartRequest
		// 파일 저장 수행
		// new DefaultFileRenamePolicy() : 동일 명 파일 있음 renaming
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
		Enumeration params =  mr.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.println("name : " + name);
			
			String value = mr.getParameter(name);
			out.println("<h2>" + name + " : " + value + "</h2>");
		}
		
		out.println("<hr>");
		
		Enumeration files = mr.getFileNames();
		
		FileInfoDAO dao2 = new FileInfoDAO();
		
		FileDAO dao = new FileDAO();
		
		String title = mr.getParameter("title");
		String writer = mr.getParameter("writer");
		String contents = mr.getParameter("contents");
		// 시퀀스를 통해서 유니크한 파일 번호를 하나 얻어오기
		int fileNumber = dao.getNumber();
		
		dao.addOne(fileNumber, title, writer, contents);

		
		while (files.hasMoreElements()) {
			String fname = (String) files.nextElement();
			String filename = mr.getFilesystemName(fname); // 실제 저장되는 파일 이름
			String original = mr.getOriginalFileName(fname); // 진짜 파일 이름
			String type = mr.getContentType(fname); // 파일 타입
			File file = mr.getFile(fname);  // 객체 생성
			
			out.println("<h2> 저장 파일명 : " + filename + "</h2>");		
			out.println("<h2> 실제 파일명 : " + original + "</h2>");		
			out.println("<h2> 콘텐츠 타입 : " + type+ "</h2>");
			dao2.addFileOne("../upload/" + filename, type, fileNumber);
			out.println("<hr>");
		}


		
	%>
		<a href="view.jsp">이미지 보기</a>
		<a href="dirView.jsp">저장디렉토리의 파일 목록보기</a>
</body>
</html>