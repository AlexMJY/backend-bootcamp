<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax01.jsp</title>
<script type="text/javascript">
	// AJAX (Asynchronous Javascript And XML)
	
	// 비동기화 방식으로 통식
	
	// 통신을 담당하는 객체 : XMLHttpRequest 
	
	let xhr = null;
	
	function getXMLHttpRequest() {
		// MS 브라우저의 경우
		if (window.ActiveObject) { 
			try {
				return new ActiveObject("MsMXL2.XMLHttp");
			} catch(e) {
				try {
					return new ActiveObejct("Microsoft.XMLHttp");
				} catch(e) {
					null;
				}
			}
		} else if (window.XMLHttpRequest) {
			// 그 외 브라우저라면 (메인)
			return new XMLHttpRequest();
		} else {
			// 어떤 브라우저인지 모를 때
			return null;
		}
	} // getXMLHttpRequest() end
	
	function load(url) {
		/* console.log(url); */
		// 1. 통신객체(XMLHttpRequest) 얻어오기
		xhr = getXMLHttpRequest();
		console.log(xhr);
		
		// 2. callback 함수
		// 대기하고 있다가 응답이 오면 이 함수 실행
		xhr.onreadystatechange = viewMessage; // 상태가 변할 때마다 자주 호출
		
		// 3. open (통신방식, 주소, 비동기통신여부)
		xhr.open("GET", url, true);  // GET 방식으로 url에 접근하는 비동기통신방식
		
		xhr.send(null); // POST 방식일 땐 value 값을 줘야 한다.
		
		console.log(xhr);
		
	}
	
	// 4. callback 함수 정의
	function viewMessage() {
		// console.log("viewMessage() 호출 중")
		
		// 통신이 완료되었을 때 and 정상 페이지일 때
		if (xhr.readyState == 4) { // 통신 완료
			if (xhr.status == 200) { // 정상 페이지
				// alert(xhr.responseText);
				let div1 = document.querySelector("#div1");
				
				// 서버로부터 수신한 내용 출력
				div1.innerText = xhr.responseText;
				
			}
		}
	}
	
	
</script>
</head>
<body>
	<!-- AJAX : 자바스크립트를 통해서 서버에 데이터 요청, 리로드 없이 데이터 불러오는 방식 -->
	<input type="button" value="simple1.txt" onclick="load('simple1.txt')" />
	<input type="button" value="simple1.jsp" onclick="load('simple1.jsp')" />
	<input type="button" value="simple2.txt" onclick="load('simple2.txt')" />
	<input type="button" value="simple2.jsp" onclick="load('simple2.jsp')" />
	
	<div id="div1">
		<!-- 서버에서 가져온 글자 출력 -->
	</div>



</body>
</html>