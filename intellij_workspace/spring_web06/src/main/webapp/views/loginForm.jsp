<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-19
  Time: 오전 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>loginForm.jsp</title>
</head>
<body>
    <h3>loginForm.jsp</h3>
    <form action="login.do" method="POST">
        <table>
            <tr>
                <th>ID</th>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <th>PW</th>
                <td><input type="text" name="pw"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="로그인">
                    <input type="button" value="가입하기">
                </td>
            </tr>
        </table>

    </form>
</body>
</html>
