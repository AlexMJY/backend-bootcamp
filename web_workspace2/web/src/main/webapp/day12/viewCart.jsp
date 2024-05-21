<%@page import="vo.ProductVO"%>
<%@page import="dao.ProductDAO"%>
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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>


<script type="text/javascript">
	// 가상계좌와 에크스로 결제를 제외한 모든 테스트 결제는 카드사 매입전 자동적으로 취소됨.
	// 매일 자정 직전에 자동 최소되는 PG사

	$(function() {
		// var IMP = window.imp;
		IMP.init("imp10374475");
	})
	
	function checkForm() {
			IMP.request_pay(
					  {
					    pg: "html5_inicis",
					    pay_method: "card",
					    merchant_uid: "p_"+new Date().getTime(), // 주문 고유 번호
					    name: "붕어빵",
					    amount: 10,
					    buyer_email: "gildong@gmail.com",
					    buyer_name: "홍길동",
					    buyer_tel: "010-4242-4242",
					    buyer_addr: "서울특별시 강남구 신사동",
					    buyer_postcode: "01181",
					  },
					  
					  function (resp) {
					    // 결제 종료 시 호출되는 콜백 함수
					    // response.imp_uid 값으로 결제 단건조회 API를 호출하여 결제 결과를 확인하고,
					    // 결제 결과를 처리하는 로직을 작성합니다.
					    console.log(resp);

					    if (resp.success) {
					    	let msg = "결제가 완료되었습니다.";
					    	msg += "고유 ID" + resp.imp_uid;
					    	msg += "상점 거래 ID" + resp.merchant_uid;
					    	msg += "결제 금액" + resp.paid_amount;
					    	msg += "카드 승인 번호" + resp.apply_num;
					    	$.ajax({
					    		
				    			url : "/web/day12/orderOk.jsp",
				    			type : "post",
				    			data : {
				    				msg : msg,
				    				orderno : resp.merchant_uid,
				    				amount : resp.paid_amount,
				    				cardConfirmno : resp.apply_num,
				    				uniqueid : "1111"
				    				
				    			},
				    			success : function (result) {
				    				console.log(result);
				    			}
				    		
				    		});
					    }
					  },
			);
	}
	
	function checkForm2() {
		console.log("checkForm2() 호출 중")
		$.ajax({
			url : "/web/day12/orderOk.jsp",
			type : "post",
			data : {
				orderno : "12300001",
				amount : 10,
				cardConfirmno : "30132123",
				uniqueid : "1111"
				
			},
			success : function (result) {
				console.log(result);
			}
		
		});
	}
</script>

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
			<th><%=vo.getPno()%></th>
			<th><%=vo.getPname()%></th>
			<th><img src="<%=vo.getImgfile()%>" alt="<%=vo.getPname()%>"
				width="100px" /></th>
			<th><%=cnt%></th>
			<th><%=productPrice%>원</th>
		</tr>

		<%
		}
		%>

		<tr>
			<td colspan="4"><b>합계 : <%=total%></b></td>
			<td></td>
		</tr>

		<tr>
			<td colspan="5">
				<input type="button" value="결제하기" onclick="checkForm();" />
				<input type="button" value="결제 ajsa" onclick="checkForm2();" />
			</td>
		</tr>

	</table>
</body>
</html>