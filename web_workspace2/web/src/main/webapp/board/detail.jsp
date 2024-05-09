<%@page import="kr.co.jhta.web.vo.BoardVO"%>
<%@page import="kr.co.jhta.web.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script>
	$(function() {
		$(".summernote").summernote({
			
		})
	})
</script>
<title>detail.jsp</title>
</head>
<body>
	<%
		// 1. 전달 받은 파라미터 값 가져오기
		String b = request.getParameter("bno");
	
		// 2. bno가 null이 아니면
		if (b != null) {
			// 3. 숫자로 형변환
			int bno = Integer.parseInt(b);
			
			// 4. dao 객체
			BoardDAO dao = new BoardDAO();
			
			dao.raiseHits(bno);  // hits 값 1 증가
			dao = new BoardDAO();
			// 5. dao를 통해서 지정한 게시물 정보 가져오기
			BoardVO vo =  dao.getOne(bno);
		
		%>
		

	<div class="container">
		<h2>상세보기</h2>
		<form action="detail.jsp">
			<table class="table table-hover">
			<tr>
				<th>작성자</th>
				<td><%= vo.getWriter() %></td>
				<th>조회수</th>
				<td><%= vo.getHits() %></td>
				<th>작성일시</th>
				<td><%= vo.getRegdate() %></td>
			</tr>
			<tr>
				<th colspan="2">제목</th>
				<td colspan="4"><%= vo.getTitle() %></td>
			</tr>
			<tr>
				<th colspan="2">내용</th>
				<td colspan="4">
				<textarea class="summernote" name="" id="" cols="30" rows="10"><%= vo.getContents() %></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<a href="list.jsp" class="btn btn-primary" >목록</a>
					<a href="modify.jsp?bno=<%= vo.getBno() %>" class="btn btn-success" type="submit">수정</a>
					<a href="deleteOk.jsp?bno=<%= vo.getBno() %>" class="btn btn-danger" >삭제</a>
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
