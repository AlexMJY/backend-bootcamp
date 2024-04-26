<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax03.jsp</title>
<script type="text/javascript" src="../js/httpRequest.js" ></script>
<script type="text/javascript">
	function prt() {
		
		// search.jsp?txt=뽀로로코딩컴퓨터 ==> imgFile URL 출력
		let txt = document.querySelector("#txt").value;
		let params = "txt="+txt;
		sendRequest("search.jsp", params, callback, "GET");
	}
	
	function callback() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				// console.log(xhr);
				document.querySelector("#div1").innerHTML = xhr.responseText;
				let img = document.querySelector("#img");
				img.src = xhr.responseText;
			}
		}
	}
</script>
</head>
<body>
	<input type="text" name="txt" id="txt" />
	<input type="button" value="출력" onclick="prt()" />
	
	<div id="myconsole">
		<img src="../images/before.png" alt="뽀로로" id="img" />
	</div>
	<div id="div1"></div>
</body>
</html>