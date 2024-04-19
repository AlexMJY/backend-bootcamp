<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="registerOk.jsp">
		ID : <input type="text" name="id" id="" /> <br>
		비밀번호 : <input type="text" name="pw" id="" /> <br>
		NAME : <input type="text" name="name" id="" /> <br>
		성별 :  <input type="radio" name="gender" value="man" id="" />남 <input type="radio" name="gender" value="woman" id="" />여  <br>
		
		<input type="submit" value="가입하기" />
		<input type="button" value="취소하기" />
	</form>
</body>
</html>