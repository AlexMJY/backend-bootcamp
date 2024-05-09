<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyForm.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script type="text/javascript">
	$(function() {
		$(".summernote").summernote({
			height: 150,
			lang: "ko-KR"
		});
	})
</script>
</head>
<body>
	<div class="container">
		<h2>수정하기</h2>
		<form action="board.do" method="get">
			<h2>글쓰기</h2>
			<table class="table table-striped">
				<tr>
					<th>작성자</th>
					<td> 
						<input type="text" name="writer" value="${vo.writer}" /> 
						<input type="hidden" name="cmd" value="modifyOk" />
						<input type="hidden" name="bno" value="${vo.bno }" />
					</td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td> <input type="text" name="title" value="${vo.title }" /> </td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td><textarea class="summernote" name="contents" id="" cols="30" rows="10" > ${vo.contents }</textarea></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input class="btn btn-success" type="button" value="목록" />
						<input class="btn btn-primary" type="submit" value="작성" /> 
						<input class="btn btn-danger" type="reset" value="다시쓰기" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>