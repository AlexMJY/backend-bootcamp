<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
	* {
		margin: 0;
		padding: 0;
	}
	h1 {
		text-align: center;
		margin: 20px;
	}
	span {
		display: inline-block;
		width: 150px;
	}
	form {
		margin-left: 50px;
	}
	div#button {
		text-align: center;
	}
	
</style>
<script>
	window.onload = function() {
		let frm = document.frm;
		
		console.dir(frm);
		
		$("#btn").on("click", idCheck);
		
		$("#id").on("keyup", idCheck);
		
		
		function idCheck() {
			console.log("btn click");
			// 1. id값 가져오기
			let id = document.querySelector("#id").value;
			console.log("id : " + id);
			// 2. 값에 공백 제거 trim
			id = id.trim()
			id = id.replaceAll(' ', '');
			console.log("id : " + id);
			
			// 3. $.ajax({설정값});
			$.ajax({
				url: "idCheckup.jsp",
				dataType: "html",
				data : {"id" : id }, // getJson.jsp?id=aaa 요청
				success : function(response, status, request) { 
					console.log(response);
					let isOk = response.trim();
					if (isOk == "true") {
						
						$("#msg").html("<span class='green' > 사용 가능한 아이디입니다.</span>")
					} else {
						$("#msg").html("<span class='red' > 사용 불가능한 아이디입니다.</span>")
					}
				}, 
				error : function(response, status, request) { 
					console.log("ajax failed"); 
					console.log(response); 
				}
			})
			
			// 4. callback 정의 (간단한 메세지 출력)
		}
		
		
		
		frm[7].onclick = function() {
			console.log("유효성 검사");
			
			if (frm[0].value == '') {
				alert("아이디를 입력해주세요");
				frm[0].focus();
				return;
			}
			if (frm[2].value == '') {
				alert("이름을 입력해주세요")
				frm[2].focus();
				return;
			}
			if (frm[3].value == '') {
				alert("비밀번호를 입력해주세요")
				frm[3].focus();
				return;
			}
			/* if (!frm[4].checked && !frm[4].checked) {
				alert("성별을 선택해주세요")
				return;
			} */
			if (frm[5].value == '') {
				alert("가입동기를 작성해주세요")
				frm[5].focus();
				return;
			} 
			
			frm.action = "registerOk.jsp";
			frm.method = "get";
			frm.submit();
		}
		
	}
	
	function email(x) {
		let email2 = document.getElementById("email2");
		email2.value = (x.value == "직접입력") ? "" : x.value;
	}
</script>
</head>
<body>
	<h1>가입신청서</h1>
	
	<form action="idCheckup.jsp" name="frm">
		<span>ID:</span> 
		<input type="text" name="id" id="id" placeholder="id를 입력하거라" /> 
		<input type="button" value="중복확인" id="btn" />
		<span id="msg"></span>
		<br>
		
		<span>NAME:</span> 
		<input type="text" name="name" id="name" placeholder="name을 입력하거라" /> <br>
		
		<span>비밀번호:</span> 
		<input type="password" name="pw" id="pw" placeholder="pw를 입력하던지 말던지" /> <br>
			  
		<span>성별:</span> 
		<input type="radio" name="gender" class="gender" value="남" />남
		<input type="radio" name="gender" class="gender" value="여" />여 <br>
		
		<span>가입동기</span> 
		<textarea name="motive" id="motive" cols="80" rows="5" style="resize: none;" 
			placeholder="가입동기를 입력하던지 말던지"></textarea>
		
		<div id="button">
			<!-- 가입버튼을 누르면 현재 입력된 값들이 올바른 값이 검사후에 registerOk.jsp로 전달 -->
			<input type="button" value="가입하기" />
			<input type="reset" value="취소하기" />
		</div>
	</form>
</body>
</html>