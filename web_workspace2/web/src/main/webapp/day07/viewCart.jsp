<%@page import="kr.co.jhta.web.vo.ProductVO"%>
<%@page import="kr.co.jhta.web.dao.ProductDAO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewCart.jsp</title>
<style>
	img {
		width: 100px;
		height: 100px;
	}
	table {
		border-top: 5px solid black;
		border-bottom: 5px solid black;
		margin: 0 auto;
		width: 800px;
	}
	th {
		text-align: center;
	}
</style>
</head>
<body>
	<table class="table">
		<tr>
			<th>상품번호</th>
			<th>상품명</th>
			<th>이미지</th>
			<th>수량</th>
			<th>할인가격</th>
		</tr>
	

	<%
		// session에서 cart 속성 가져오기 : obj
		Object obj = session.getAttribute("cart");
		
		// obj 가 null이면 ArrayList 생성해서 session의 속성으로 지정하고 다시 가져오기
		if (obj == null) {
			ArrayList<Integer> cart = new ArrayList<Integer>();
			session.setAttribute("cart", cart);
			obj = session.getAttribute("cart");
		}
		
		// 아래 코드부터는 obj가 반드시 존재
		
		ArrayList<Integer> cart = (ArrayList<Integer>) obj;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		// 1개씩 꺼내서 상품과 수량 집계
		for (Integer it : cart) {
			// n번 상품이 존재한다면
			if (map.containsKey(it)) {
				// 현재 상품의 갯수 구하기
				int cnt = map.get(it);
				// 상품 갯수 1 증가시키기
				cnt++;
				// 다시 맵에 넣기
				map.put(it, cnt);
			} else {
				map.put(it, 1);
			}
			System.out.println("map : " + map);
		}
		// 화면에 표 형태로 출력
		Set key = map.keySet(); // 상품 번호만 set 타입으로 리턴
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductVO> item = dao.getData(key);
		
		
		long total = 0;
		
		for (ProductVO vo : item) {
			int cnt = map.get(vo.getPno());
			long productPrice = Math.round(vo.getPrice() * (1 - vo.getDcratio() * 0.01)) * map.get(vo.getPno());
			total += productPrice; 
	%>
		
			<tr>
				<th><%= vo.getPno() %></th>
				<th><%= vo.getPname() %> </th>
         		<th><img src="<%= vo.getImgfile() %>" alt="<%=vo.getPname() %>" width="100px" /> </th>
         		<th><%= cnt %></th> 
         		<th><%= productPrice %>원</th>
			</tr>
		
	<%	
		}
	%>
	
	<tr>
		<td colspan="4"><b>합계 : <%= total %></b></td>
		<td></td>
	</tr>
	
	<tr>
		<td colspan="5">
			<input type="button" value="결제하기" />
		</td>
	</tr>
	
	</table>
</body>
</html>