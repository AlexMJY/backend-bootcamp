<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>detail.jsp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <form action="modify" method="post">
            <table class="table table-striped">
                <tr>
                    <th>게시물 번호</th>
                    <td>${dto.bno}</td>
                </tr>
                <tr>
                    <th>작성일시</th>
                    <td>${dto.regdate}</td>
                </tr>

                <tr>
                    <th>조회수</th>
                    <td>${dto.hits}</td>
                </tr>

                <tr>
                    <th >작성자</th>
                    <td>${dto.writer}</td>
                </tr>



                <tr>
                    <th>제목</th>
                    <td>${dto.title}</td>
                </tr>

                <tr>
                    <th>내용</th>
                    <td>${dto.contents}</td>
                </tr>


            </table>
        </form>
        <a href="list" class="btn btn-primary">목록</a>
        <a href="modify?bno=${dto.bno}" class="btn btn-warning">수정</a>
        <a href="delete/${dto.bno}" class="btn btn-danger">삭제</a>

    </div>
</body>
</html>
