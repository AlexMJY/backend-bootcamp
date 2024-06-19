<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-19
  Time: 오후 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>


    <title>join.jsp</title>
</head>
<body>
    <h3>join.jsp</h3>

    <div class="container">
        <form action="join.do" method="POST">
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
                    <th>NAME</th>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <th>gender</th>
                    <td><input type="text" name="gender"></td>
                </tr>
                <tr>
                    <th>MOTIVE</th>
                    <td><input type="text" name="motive"></td>
                </tr>
                <tr>
                    <th>ZIPCODE</th>
                    <td><input type="text" name="zipcode"></td>
                </tr>
                <tr>
                    <th>ADDRESS</th>
                    <td><input type="text" name="address"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" value="가입하기">
                        <input type="button" value="취소">
                    </td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>
