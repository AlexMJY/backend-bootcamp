<%@page import="kr.co.jhta.web.vo.BoardVO"%>
<%@page import="kr.co.jhta.web.dao.BoardDAO"%>
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
				
				// 2. bno가 null이 아니면
				if (b != null) {
					// 3. 숫자로 형변환
					int bno = Integer.parseInt(b);
					
					// 4. dao 객체
					BoardDAO dao = new BoardDAO();
					
					// 5. dao를 통해서 지정한 게시물 정보 가져오기
					BoardVO vo =  dao.getOne(bno);
				%>
				<tr>
					<th>작성자</th>
					<td>
						<input class="form-control" type="text" name="writer" id="" value="<%= vo.getWriter() %>" />
						<input type="hidden" name="bno" value="<%= vo.getBno() %>" />
					
					</td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td><input class="form-control" type="text" name="title" id="" value="<%= vo.getTitle() %>"/></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea class="form-control" name="contents" id="" cols="30" rows="10" ><%= vo.getContents() %></textarea></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<a href="list.jsp" class="btn btn-primary">목록</a>
						<input class="btn btn-success" type="submit" value="수정" />
						<input class="btn btn-warning" type="reset" value="다시쓰기" />
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