<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<style>
	div>h2, #buttons {
		text-align: center;
	}
</style>

<script>
	window.onload = function() {
		let addrBtn = document.getElementById("addrBtn");
		let registerBtn = document.getElementById("registerBtn");
		addrBtn.onclick = openKakaoPostCode;
		registerBtn.onclick = checkData;
	}
	
	function openKakaoPostCode() {
		new daum.Postcode({
	        oncomplete: function(data) {
	        	let addr = document.getElementById('addr');
	        	let zipcode = document.getElementById('zipcode');
	        	addr.value = data.address;
	        	zipcode.value = data.zonecode;
	        }
		}).open();
	}
	
	function checkData() {
		let frm = document.frm;
		
		if (frm[0].value == null || frm[0].value == '') {
			alert("아이디를 입력하세요!.");
			frm[0].focus();
			return
		}
		
		if (frm[1].value == null || frm[1].value == '') {
			alert("이름을 입력하세요!.");
			frm[1].focus();
			return
		}
		
		if (frm[4].value == null || frm[4].value == '') {
			alert("비밀번호를 입력하세요!.");
			frm[4].focus();
			return
		}
		
		if (frm[8].value == null || frm[8].value == '') {
			alert("우편번호를 입력하세요!.");
			return
		}
		
		if (frm[10].value == null || frm[10].value == '') {
			alert("주소를 입력하세요!.");
			return
		}
		
 		if (!(frm[14].checked || frm[15].checked)) {
			alert("성별을 선택하세요!.");
			return
		}
		
		frm.action='registerCheck.jsp';
		frm.method='get';
		frm.submit();
	}
	
</script>
</head>


<body>
	<div class="container" >
		<h2>가입신청서</h2>
		<form action="registerCheck.jsp" name="frm">
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
				<td>
					<input type="text" name="addr" id="addr" />
					<input type="text" name="zipcode" id="zipcode" />
					<input type="button" value="검색" id="addrBtn"/>
				</td>
				
			</tr>		
	
			<!-- <tr>
				<th>EMAIL</th>
				<td>
					<input type="text" name="email1" id="" /> @ <input type="text" name="email2" id="" /> 
					<select class="box"  name="domain-list">
						  <option value="naver.com">naver.com</option>
						  <option value="google.com">google.com</option>
						  <option value="kakao.com">kakao.com</option>
					</select> 
				</td>
			</tr>		 -->
	
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
			
			<tr>
				<td colspan="2" id="buttons">
					<input type="submit" value="가입하기" id="registerBtn" />
					<input type="button" value="취소하기" />
				</td>
			</tr>			
		</table>
		
		

		</form>
	</div>
	
	
</body>
</html>