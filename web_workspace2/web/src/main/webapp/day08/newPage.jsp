<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>newPage.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function() {
		$("#btn").on("click", function() {
			console.log($("#txt").val());	
			
			// 이 페이지를 부른 상위 페이지 부르기 : opener
			let p = opener.document.getElementById("txt2").value=$("#txt").val();
			// console.dir(p);
		})
	})
</script>
</head>
<body>
	<h2>newPage.jsp입니다</h2>
	<input type="text" name="txt" id="txt" />
	<input type="button" value="버튼" id="btn" />
</body>
</html>