<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload = function() {
		let btn = document.getElementById("btn1");
		btn.onclick = function() {
			let frm = document.frm;
			if (frm[0].value == '') {
				alert("ID를 입력하세요");
				frm[0].focus();
				return ;
			}
			
			if (frm[1].value == '') {
				alert("PW를 입력하세요");
				frm[1].focus();
				return;
			}
			frm.action = "registerOk.jsp";
			frm.method = "get";
			frm.submit();
			alert("회원가입 완료");
		}
	}
</script>

</head>
<body>
	
	<form action="registerOk.jsp" name="frm">
		
		<span>아이디</span>
		<input type="text" name="id" id="" placeholder = "아이디를 입력하세요"/> <br>
		
		<span>비밀번호</span>
		<input type="password" name="pw" id="" placeholder = "비밀번호를 입력하세요"/> <br>
		
		<span>이름</span>
		<input type="text" name="name" id="" placeholder = "이름을 입력하세요"/> <br>
		
		<span>성별</span>  
		<input type="radio" name="gender" value="man" id="" />남 <input type="radio" name="gender" value="woman" id="" />여  <br>
		
		<span>가입동기</span>
		<textarea name="motive" id="" cols="50" rows="10" placeholder="가입동기를 입력하세요." style="resize: none;"></textarea>
		
		<input type="button" value="가입하기" id="btn1" />
		<input type="button" value="취소하기" id="btn2" />
	</form>
</body>
</html>