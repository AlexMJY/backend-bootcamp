<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="sidebar">
	<div id="login">
		<form action="loginOk.jsp">
			<%
				if (session.getAttribute("vo") == null) {
			%>
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="id" id="" /></td>
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="text" name="pw" id="" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="로그인" /> 
					<input type="button" value="회원가입" /></td>
				</tr>
			</table>
			<%
				} else {
			%>
			<table>
				<tr>
					<td>환영합니다.</td>
					<td><input type="text" name="id" id="" /></td>
				</tr>
				
				<tr>
					<a href="logout.jsp">logout</a>
				</tr>
			</table>
			<%
				}
			%>
			
		</form>
	</div>
	

</div>
