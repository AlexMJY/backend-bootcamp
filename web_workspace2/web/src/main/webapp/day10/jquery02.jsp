<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery02.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	// 1. 버튼 클릭 => 콘솔에 메세지 추력
	$(function() {
		$("#btn").on("click", function() {
			//console.log("btn click");
			// 2. server02.jsp에 사원이름 출력 SCOTT, BLACK, KING
			// 3. 버튼을 클릭하면 server02.jsp의 내용을 콘솔 출력
			$.ajax({
				url : "server02.jsp",
				success : function(data) {
					// console.log("ajax success");
					let data2 = data.trim();
					let vdata = data2.split(",");
					// console.log(vdata);
					
					// 4. 버튼을 클릭하면 server02.jsp의 내용을 배열로 만들어 콘솔 출력
					let varray = [];
					for (let i = 0; i <= vdata.length; i++) {
						varray.push(vdata[i]);
						
						// 5. 배열에서 한개씩 꺼내 <li>SCOTT</li> 형태로 완성
						let txt = "<li>" + vdata[i] + "</li>";
						// 6. ul 태그 뒤에 붙인다.
						$("ul").append(txt);
					}
					console.log(varray);
				}
			});
		});
	})
	
	
	
	
</script>
</head>
<body>
	<h2>사원목록</h2>
		<input type="button" value="출력" id="btn" />
		<div>
			<ul>
				<li>
					<h2>사원</h2>
				</li>
			</ul>
		</div>
</body>
</html>