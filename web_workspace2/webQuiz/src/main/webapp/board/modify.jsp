<%@page import="vo.BoardVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify.jsp</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
	<div class="container">
		<form action="modifyOk.jsp">
			<table class="table table-striped">
				<%
					String b = request.getParameter("bno");
					if (b != null) {
						int bno = Integer.parseInt(b);
						BoardDAO dao = new BoardDAO();
						BoardVO vo = dao.getOne(bno);
				%>
					<tr>
						<th>Writer</th>
						<td>
							<input class="form-control" type="text" name="writer" id="" value="<%= vo.getWriter() %>" />
							<input type="hidden" name="bno" value="<%= vo.getBno() %>" />
						</td>
					</tr>
					<tr>
						<th>Title</th>
						<td><input class="form-control" type="text" name="title" id="" value="<%= vo.getTitle() %>" /></td>
					</tr>
					<tr>
						<th>Contents</th>
						<td><input class="form-control" type="text" name="contents" id="" value="<%= vo.getContents() %>" /></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<a href="list.jsp" class="btn btn-primary">Index</a>
							<input class="btn btn-success" type="submit" value="Modify" />
							<input class="btn btn-warning" type="reset" value="Delete" />
						</td>
					</tr>
				
				<%
					}
				%>
			</table>
		</form>
	</div>
</body>
</html>