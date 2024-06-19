<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-19
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>


    <title>main.jsp</title>
</head>
<body>
    <h3>main.jsp</h3>
<%--    <img src="./pic.jpg" alt="">--%>

    <!-- JSTL을 사용해서 id가 존재하면 XXX님 환영합니다. 없으면 Login 페이지로 -->
    <c:if test="${id != null}">
        <h2>${id} 님 환영합니다.</h2>
        <a href="logout.do">로그아웃</a>
        <a href="showDept.do">부서목록보기</a> <!-- DeptController (GET) 부서정보 가져와서 deptList.jsp // OracleDao 메서드 selectDeptList() method -->
    </c:if>

    <c:if test="${empty id}">
        <ul>
            <li><a href="login.do">로그인</a></li>
            <li><a href="join.do">회원가입</a></li>
        </ul>
    </c:if>



</body>
</html>
