<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-18
  Time: 오후 2:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>menu</title>
</head>
<body>
    <h4>menu.jsp</h4>

    <form action="selectMenu.do" id="intrestForm">
        <div>
            <h3>당신이 흥미있는 것들을 고르시오</h3>
        </div>
        <div>
            <input type="checkbox" id="coding" name="interest" value="coding" />
            <label for="coding">coding</label>
        </div>

        <div>
            <input type="checkbox" id="football" name="interest" value="football" />
            <label for="football">football</label>
        </div>

        <div>
            <input type="checkbox" id="game" name="interest" value="game" />
            <label for="game">game</label>
        </div>

        <div>
            <input type="checkbox" id="driving" name="interest" value="driving" />
            <label for="driving">driving</label>
        </div>

        <div>
            <input type="checkbox" id="sleeping" name="interest" value="sleeping" />
            <label for="sleeping">sleeping</label>
        </div>
    </form>

    <button type="submit" form="intrestForm">전송</button>

</body>
</html>
