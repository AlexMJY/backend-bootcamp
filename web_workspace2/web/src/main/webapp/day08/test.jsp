<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
	$ (function() {
		$("#btn").on("click", show);
	})
	
	
	window.onload = function() {
		let btn = document.querySelector(".btn2");
		btn.addEventListener = ("click", show);
		btn.onclick = show;
		
	}
	
	function show() {
		// alert("show function");
		
		let w = 300;
		let h = 300;
		let x = 100;
		let y = 100;
		let spec = "width="+w+",height="+h+",left="+x+",top="+y+",menubar=0,resizable=0,scrollbars=0,status=0,tollbar=0";
		window.open("newPage.jsp", "_blank", spec);
	}
</script>
</head>
<body>
	<input type="text" name="txt2" id="txt2" />
	<input type="button" value="버튼1" id="btn" />
	<input type="button" value="버튼2" class="btn2" />
</body>
</html>