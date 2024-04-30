<%@page import="vo.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">s
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
				<th>작성자조회수</th>
				<th>조회수</th>
			</tr>
			
			<%
				BoardDAO dao = new BoardDAO();
			
				int totalCount = dao.getTotalCount();
				int recordPerPage = 20;
				int totalPage = totalCount % recordPerPage == 0 ? totalCount / recordPerPage : totalCount / recordPerPage + 1;
				String cp = request.getParameter("cp");  // current page
				int currentPage = 0;
				if (cp != null) {
					currentPage = Integer.parseInt(cp);
				} else {
					currentPage = 1;
				}
				int startNo = (currentPage - 1) * recordPerPage + 1;
				int endNo = currentPage * recordPerPage;
				int startPage = 1;
				int endPage = totalPage;
				
				
			
			
				ArrayList<BoardVO> list = dao.selectAll(startNo, endNo);
				
				for (BoardVO vo :  list) {
			%>
			
			<tr>
				<td><%= vo.getBno() %></td>
				<td><a href="detail.jsp?bno=<%= vo.getBno() %>"><%= vo.getTitle() %></a></td>
				<td><%= vo.getWriter() %></td>
				<td><%= vo.getHits() %></td>
			</tr>
			
			<%
				}
			%>
			
			<tr>
			<td colspan="4">
				<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  <li class="page-item"><a class="page-link" href="#">Previous</a></li>
				<%
					  for (int i = startPage; i <= endPage; i++) {
				%>
				    <li class="page-item"><a class="page-link" href="list.jsp?cp=<%= i %>"><%= i %></a></li>
				<%
						}
				%>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				  </ul>
				</nav>
			</td>
		</tr>
		</table>
	</div>
</body>
</html>