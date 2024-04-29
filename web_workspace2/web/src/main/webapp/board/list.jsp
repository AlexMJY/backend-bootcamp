<%@page import="vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>

<div class="container">

	<h2>게시판</h2>
	<a class="btn btn-primary" href="write.jsp">글쓰기</a>	
	<table class="table table-hover">
	
		<tr>
			<th>게시물번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		
		<%
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> list = dao.selectAll();
			
			/* out.println("list: " + list); */
			
			for (BoardVO vo : list) {
		%>
		<tr>
			<td><%= vo.getBno() %></td>
			<td><a href="detail.jsp?bno=<%=vo.getBno() %>"><%= vo.getTitle() %></a></td>
			<td><%= vo.getWriter() %></td>
			<td><%= vo.getHits() %></td>
			<!-- <td><input type="button" value="상세보기" /></td> -->
		</tr>
		
		
	
		<%
			}
		%>
	
	</table>
</div>
</body>
</html>