<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-20
  Time: 오후 5:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modifyForm.jsp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <form action="modify" method="post">
        <table class="table table-striped">
            <tr>
                <th>작성자</th>
                <td><input type="text" name="writer" id="writer" value="${dto.writer}"></td>
            </tr>

            <tr>
                <th>제목</th>
                <td><input type="text" name="title" id="title" value="${dto.title}"></td>
            </tr>

            <tr>
                <th>내용</th>
                <td><textarea name="contents" id="contents" cols="30" rows="10">${dto.contents}</textarea></td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" class="btn btn-success" value="수정하기">
<%--                    <input type="reset" class="btn btn-warning"  value="리셋">--%>
                    <a href="board/list" class="btn btn-primary">목록</a>
                </td>
            </tr>
        </table>

    </form>
</div>
</body>
</html>
