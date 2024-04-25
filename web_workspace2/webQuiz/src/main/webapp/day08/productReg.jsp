<%@page import="dao.ProductDAO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productReg.jsp</title>
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
		<h2>상품 등록</h2>
		<form action="productRegOk.jsp" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품명 </th>
				<td><input type="text" name="pname" id="pname" /></td>
			</tr>
			
			<tr>
				<th>상품이미지(imgFile)</th>
				<td>
					<input type="file" name="pfile1" id="pfile1" /> 
				</td>
				
			</tr>
			
			<tr>
				<th>상품이미지(bigFile)</th>
				<td><input type="file" name="pfile2" id="pfile2" /></td>
			</tr>
			
			<tr>
				<th>상품수량</th>
				<td><input type="text" name="pcount" id="pcount" /></td>
			</tr>
	
			<tr>
				<th>판매가</th>
				<td><input type="text" name="pprice" id="pprice" /></td>
			</tr>		
		
			<tr>
				<th>할인율</th>
				<td><input type="text" name="psale" id="psale" /></td>
					 
			</tr>		
		
			<tr>
				<th>상세설명</th>
				<td><textarea name="pcontent" id="pcontent" cols="30" rows="10"></textarea></td>
			</tr>		
	
			
		</table>
		
		<input type="submit" value="등록하기" />
		<input type="reset" value="리셋" />
		<a href="productList.jsp"><input type="button" value="목록으로" /></a>

		</form>
	</div>
	
	
	
</body>
</html>