<%@page import="kr.co.jhta.web.vo.BoardVO"%>
<%@page import="kr.co.jhta.web.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
	<%
		String b = request.getParameter("bno");
		if (b != null) {
			int bno = Integer.parseInt(b);
			BoardDAO dao = new BoardDAO();
			dao.raiseHits(bno);
			dao = new BoardDAO();
			BoardVO vo = dao.getOne(bno);
	%>
	
	<div class="container">
		<h2>상세보기</h2>
		<form action="detail.jsp">
			<table class="table table-hover">
				<tr>
					<th>Writer</th>
					<th><%= vo.getWriter() %></th>
					<th>Hits</th>
					<th><%= vo.getHits() %></th>
					<th>RegDate</th>
					<th><%= vo.getRegdate() %></th>
				</tr>
				<tr>
					<th colspan="2">Title</th>
					<td colspan="4"><%= vo.getTitle() %></td>
				</tr>
				<tr>
					<th colspan="2">Context</th>
					<td colspan="4">
						<textarea class="summernote" name="" id="" cols="30" rows="10"><%= vo.getContents() %></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<a href="list.jsp" class="btn btn-primary">Index</a>
						<a href="modify.jsp?bno=<%= vo.getBno() %>" class="btn btn-success" type="submit" >Modify</a>
						<a href="deleteOk.jsp?bno=<%= vo.getBno() %>>" class="btn btn-danger" >Delete</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<% 
		}
	%>
	
</body>
</html>