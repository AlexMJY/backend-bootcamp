<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-18
  Time: 오후 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>select.jsp</title>
</head>
<body>
<h3>select.jsp</h3>
<h2>당신이 선택한 취미는?</h2>

<h3>${interestList}</h3>

<ul>
    <c:forEach var="menu" items="${interestList}">
        <li>${menu}</li>
    </c:forEach>
</ul>

</body>
</html>
