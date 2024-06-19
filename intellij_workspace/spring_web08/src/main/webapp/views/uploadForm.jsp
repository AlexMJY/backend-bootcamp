<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-19
  Time: 오후 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uploadForm.jsp</title>
</head>
<body>
    <h3>uploadForm.jsp</h3>
    <form action="uploadOk.do" method="post" enctype="multipart/form-data">
        <input type="text" name="msg" >
        <input type="file" name="uploadfile" multiple>
        <input type="submit" value="전송">
    </form>
</body>
</html>
