<%@page import="kr.co.jhta.web.vo.ProductVO"%>
<%@page import="kr.co.jhta.web.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img {
		width: 200px;
		height: 400px;
	}
</style>
</head>
<body>
	<%
		// pno라는 파라미터 값 가져오기
		String p = request.getParameter("pno");
	
		// out.println("pno: " + p);
		// 파라미터 값이 null이 아니라면
		if (p != null) {
			// productDAO 객체 생성하기
			ProductDAO dao = new ProductDAO();
			// p라는 문자열 변수의 값을 숫자로 형변환
			int pno = Integer.parseInt(p);
			// db에서 해당 상품정보 가져오기
			ProductVO vo = dao.getOne(pno);
	%>
		<div class="container">
			<table class="table table-hover">
				<tr>
					<th>상품번호</th>
					<td><%= vo.getPno() %></td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><%= vo.getPname() %></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><%= vo.getPrice() %></td>
				</tr>
				<tr>
					<th>할인율</th>
					<td><%= vo.getDcratio() %></td>
				</tr>
				<tr>
					<th>상품 설명</th>
					<td><%= vo.getProdesc() %></td>
				</tr>
				<tr>
					<th>재고</th>
					<td><%= vo.getQty() %></td>
				</tr>
				<tr>
					<th>이미지 파일1</th>
					<td> <img src="<%= vo.getImgfile() %>" alt="" /> </td>
				</tr>
				<tr>
					<th>이미지 파일2</th>
					<td> <img src="<%= vo.getBigfile() %>" alt="" /> </td>
				</tr>
			</table>
		</div>
	<%
		}
	%>
</body>
</html>