<%@page import="kr.co.jhta.web.dao.MemberDAO"%>
<%@page import="kr.co.jhta.web.vo.ProductVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.jhta.web.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	* {
		margin: 0;
		padding: 0;
	}
	#container {
		width: 1000px;
		margin: auto;
	}
	img {
		width: 200px;
		height: 200px;
	}
	p {
		text-align: center;
	}
	.wrap {
		width: 200px;
		border: 1px solid red;
		float: left;
		padding: 20px;
	}
	.del {
		text-decoration: line-through;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	function productReg() {
		window.location.href = "productReg.jsp";		
	}
</script>
</head>
<body>
	<div id="container">
		<%
			ProductDAO dao = new ProductDAO();
			ArrayList<ProductVO> list = dao.selectAll();
			
			for (ProductVO vo : list) {
				// 1. 상품명 구하기
				// System.out.println("상품명 : " + vo.getPname());
				// 2. 상품명 길이 구하기
				// System.out.println("상품길이 : " + vo.getPname().length());
				// 3. 길이가 15자 이상이면 15자까지만 출력
				// System.out.println((vo.getPname().length() >= 12) ? vo.getPname().substring(0, 12) + "..." : vo.getPname());
				
				vo.setPname((vo.getPname().length() >= 8) ? vo.getPname().substring(0, 8) + "..." : vo.getPname());
				
				
		%>	
			<div class="wrap">
				<a href="productDetail.jsp?pno=<%= vo.getPno() %>">
				<img src="<%= vo.getImgfile() %>" alt="" /></a>
				<p><%= vo.getPname() %></p>
				<p class="del"><%= vo.getPrice() %></p>
				<p>대박할인</p>
				<p><%= vo.getDcratio() %> %</p>
				<p><%= Math.round(vo.getPrice() * (1 - vo.getDcratio() * 0.01)) %>원</p>
				<p>
					<input type="button" value="즉시구매" />
					<input type="button" value="장바구니" />
				</p>
			</div>
					
		<%
			}		
		%>
		
		<div id="reg">
		<%
			
			if (session.getAttribute("admin") != null) {
				System.out.println("어드민 로그인 성공");
				out.println("<br><hr>");
				out.println("<input type='button' value='상품등록' onclick='productReg()'/>");
			} else {
				System.out.println("어드민 로그인 실패");
			}
		%>
		</div>
		
		</div>
</body>
</html>