<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style>
	div>h2 {
		text-align: center;
	}
</style>
</head>


<body>
	

	<div class="container">
		<h2>가입신청서</h2>
		<form action="registerCheck.jsp">
		<table>
			<tr>
				<th>ID </th>
				<td><input type="text" name="id" id="id" /></td>
			</tr>
			
			<tr>
				<th>NAME</th>
				<td><input type="text" name="name" id="name" /></td>
			</tr>
			
			<tr>
				<th>주민등록번호</th>
				<td><input type="text" name="socialNum1" id="socialNum1" /> - <input type="text" name="socialNum2" id="socialNum2" /></td>
			</tr>
	
			<tr>
				<th>비밀번호</th>
				<td><input type="text" name="pw" id="pw" /></td>
			</tr>		
		
			<tr>
				<th>전화번호</th>
				<td>
					<input type="text" name="phone1" id="phone1" /> - 
					<input type="text" name="phone2" id="phone2" /> - 
					<input type="text" name="phone3" id="phone3" />
				</td>
					 
			</tr>		
		
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" id="addr" /></td>
			</tr>		
	
			<tr>
				<th>EMAIL</th>
				<td>
					<input type="text" name="email1" id="" /> @ <input type="text" name="email2" id="" /> 
					<select class="box"  name="domain-list">
						  <option value="naver.com">naver.com</option>
						  <option value="google.com">google.com</option>
						  <option value="kakao.com">kakao.com</option>
					</select> 
				</td>
			</tr>		
	
			<tr>
				<th>성별</th>
				<td><input type="radio" name="gender" value="man" id="" />남 <input type="radio" name="gender" value="woman" id="" />여</td>
			</tr>
		
			<tr>
				<th>취미</th>
				<td>
					<input type='checkbox' name='hobby' value='music' />음악감상
	  				<input type='checkbox' name='hobby' value='book' />독서
	  				<input type='checkbox' name='hobby' value='sport' />운동 <br>
				</td>
					
			</tr>			
		</table>
		
		<input type="submit" value="가입하기" />
		<input type="button" value="취소하기" />

		</form>
	</div>
	
	
</body>
</html>