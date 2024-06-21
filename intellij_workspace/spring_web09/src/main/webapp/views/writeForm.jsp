<%--
  Created by IntelliJ IDEA.
  User: jhta
  Date: 2024-06-20
  Time: 오후 3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>writeForm.jsp</title>

    <!-- include libraries(jQuery, bootstrap) -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- include summernote css/js -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


</head>

<body>

<div class="container">
    <form action="write" method="post">
        <table class="table table-striped">
            <tr>
                <th>작성자</th>
                <td><input type="text" name="writer" id="writer"></td>
            </tr>

            <tr>
                <th>제목</th>
                <td><input type="text" name="title" id="title"></td>
            </tr>

            <tr>
                <th>내용</th>
                <td><textarea id="summernote" name="contents" id="contents" cols="30" rows="10"> </textarea></td>
            </tr>
            
            <tr>
                <td colspan="2">
                    <input type="submit" class="btn btn-success" value="글쓰기">
                    <input type="reset" class="btn btn-warning"  value="리셋">
                    <a href="list" class="btn btn-primary">목록</a>
                </td>
            </tr>
        </table>
    </form>
</div>


<script>
    $(document).ready(function() {
        $('#summernote').summernote({
            placeholder: 'Hello stand alone ui',
            tabsize: 2,
            height: 120,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'underline', 'clear']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'video']],
                ['view', ['fullscreen', 'codeview', 'help']]
            ]
        });
    });
</script>

</body>
</html>
