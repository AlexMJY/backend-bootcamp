<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-20
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>fileList.jsp</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
    <div class="container">
    <h3>file.jsp</h3>
    <h3>${list}</h3>



    <table class="table table-striped">
        <c:forEach var="dto" items="${list}">
        <tr>
            <td>${dto.no}</td>
            <td><a href="download?no=${dto.no}">${dto.fileName}</a></td>
<%--            <td>${dto.fileName}</td>--%>
            <td><img src="./data/${dto.fileName}" alt=""></td>
        </tr>
        </c:forEach>
    </table>

    </div>
</body>
</html>
