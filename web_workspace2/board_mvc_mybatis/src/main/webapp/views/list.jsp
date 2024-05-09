<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<h2>게시판</h2>
		
<%-- 
		<h5>totalCount : ${totalCount}</h5>
		<h5>recordPerPage : ${recordPerPage}</h5>
		<h5>currentPage : ${currentPage}</h5>
		<h5>startNo : ${startNo}</h5>
		<h5>endNo : ${endNo}</h5>
		<h5>startPage : ${startPage}</h5>
		<h5>endPage : ${endPage}</h5>
		<h5>totalPage : ${totalPage}</h5>
		<h5>isPrev : ${isPrev}</h5>
		<h5>isNext : ${isNext}</h5>
 --%>


		<a href="board.do?cmd=write" class="btn btn-primary">글쓰기</a>
		<table class="table table-stripe">
			<tr>
				<th>게시물번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
			</tr>

			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.bno}</td>
					<td>${dto.writer}</td>
					<td><a href="board.do?cmd=detail&bno=${dto.bno}">${dto.title}</a></td>
					<td>${dto.hits}</td>
				</tr>
			</c:forEach>


			<tr>
				<td colspan="4">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<c:if test="${isPrev}">
								<li class="page-item"><a class="page-link" href="board.do?cmd=list&cp=${currentPage - 1}">Previous</a></li>
							</c:if>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<li class="page-item"><a class="page-link" href="board.do?cmd=list&cp=${i}">${i}</a></li>
							</c:forEach>
							<c:if test="${isNext}">
								<li class="page-item"><a class="page-link" href="board.do?cmd=list&cp=${currentPage + 1}">Next</a></li>
							</c:if>
						</ul>
					</nav> 
					
					<%-- 
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="board.do?cmd=list&cp=${i}">[${i}]</a>
					</c:forEach> 
					--%>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>