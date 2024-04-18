<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="result.jsp">
		ID : <input type="text" name="id" id="" /> <br>
		NAME : <input type="text" name="name" id="" /> <br>
		주민등록번호 : <input type="text" name="sId1" id="" /> - <input type="text" name="sId2" id="" />  <br>
		비밀번호 : <input type="text" name="pw" id="" /> <br>
		전화번호 : <input type="text" name="phone1" id="" /> - <input type="text" name="phone2" id="" /> - <input type="text" name="phone3" id="" /> <br>
		주소 : <input type="text" name="addr" id="" /> <br>
		EMAIL : <input type="text" name="email1" id="" /> @ <input type="text" name="email2" id="" /> 
				<select class="box"  name="domain-list">
					  <option value="naver.com">naver.com</option>
					  <option value="google.com">google.com</option>
					  <option value="kakao.com">kakao.com</option>
				</select> <br> 
		성별 :  <input type="radio" name="gender" value="man" id="" />남 <input type="radio" name="gender" value="woman" id="" />여  <br>
		취미 :  <input type='checkbox' name='hobby' value='music' />음악감상
  				<input type='checkbox' name='hobby' value='book' />독서
  				<input type='checkbox' name='hobby' value='sport' />운동 <br>
		
		<input type="submit" value="가입하기" />
		<input type="button" value="취소하기" />
	</form>
</body>
</html>