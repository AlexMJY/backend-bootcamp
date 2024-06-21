<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list.jsp</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>

    <div class="container" style="max-width: 1200px">
        <h4>list.jsp</h4>

        <h5>currentPage: ${map.currentPage}</h5>
        <h5>totalNumber : ${map.totalNumber}</h5>
        <h5>countPerPage : ${map.countPerPage}</h5>
        <h5>totalPages : ${map.totalPages}</h5>
        <h5>startNo : ${map.startNo}</h5>
        <h5>endNo : ${map.endNo}</h5>
        <h5>startPageNo : ${map.startPageNo}</h5>
        <h5>endPageNo : ${map.endPageNo}</h5>
        <h5>prev : ${map.prev}</h5>
        <h5>next : ${map.next}</h5>



<%--        <a href="board/write" class="btn btn-primary">글쓰기</a>--%>
<%--        <a href="write" class="btn btn-primary">글쓰기</a>--%>

        <a href="write" class="btn btn-primary">쓰기</a>
        <table class="table table-striped">
            <tr>
                <th>게시물번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>내용</th>
                <th>작성일시</th>
                <th>조회수</th>
                <th>상태</th>
            </tr>
            <c:forEach var="dto" items="${list}">
                <tr>
                    <td>${dto.bno}</td>
                    <td>${dto.writer}</td>
                    <td><a href="detail?bno=${dto.bno}">${dto.title}</a></td>
                    <td>${dto.contents}</td>
                    <td>${dto.regdate}</td>
                    <td>${dto.hits}</td>
                    <td>${dto.status}</td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="4">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:if test="${map.prev}">
                                <li class="page-item"><a class="page-link" href="list?currentPage=${map.currentPage - 1}">«</a></li>
                            </c:if>

                            <c:forEach var="i" begin="${map.startPageNo}" end="${map.endPageNo}">
                                <li class="page-link"><a href="list?currentPage=${i}">${i}</a></li>
                            </c:forEach>

                            <c:if test="${map.next}">
                                <li class="page-item"><a class="page-link" href="list?currentPage=${map.currentPage + 1}">»</a></li>
                            </c:if>
                        </ul>
                    </nav>
                </td>
            </tr>

        </table>
    </div>

</body>
</html>
