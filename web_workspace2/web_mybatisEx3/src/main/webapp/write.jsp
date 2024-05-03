<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form action="writeOk.jsp">
			<table class="table table-hover">
				<tr>
					<th>부서명</th>
					<td><input type="text" name="dname" id="dname" class="form-control" /></td>
				</tr>
				<tr>
					<th>지역</th>
					<td><input type="text" name="loc" id="loc" class="form-control" /></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<a href="list.jsp" class="btn btn-primary">목록</a>
						<input type="submit" value="작성" class="btn btn-success" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>