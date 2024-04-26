<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>movie.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#btn").on("click", function() {
			let movie = document.querySelector("#movie").value;
			
			$.ajax({
				url: "movieSearch.jsp",
				dataType: "html",
				data : {"name" : movie}, 
				success : function(response, status, request) { 
					console.log(response);
					$("#movieImg").attr("src", response);
				}
				
			})
		})
	})
</script>

</head>
<body>
	<h2>영화소개</h2>
	<input type="text" name="movie" id="movie" />
	<input type="button" value="검색" id="btn" />
	<div><img src="" alt=""  id="movieImg" /></div>
</body>
</html>