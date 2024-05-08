<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<table class="table table-striped">
			<tr>
				<th>게시물번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
			</tr>
			
			<tr>
				<td>${vo.bno}</td>
				<td>${vo.writer}</td>
				<td>${vo.title}</td>
				<td>${vo.hits}</td>
			<tr>	
			
			<tr>
				<td colspan="2">
					<a href="board.do" class="btn btn-success">목록</a>
					<a href="board.do?cmd=modify&bno=${vo.bno}" class="btn btn-primary">수정</a>
					<a href="board.do?cmd=delete&bno=${vo.bno}" class="btn btn-danger">삭제</a>
				</td>
			</tr>
			
		</table>
	</div>
</body>
</html>