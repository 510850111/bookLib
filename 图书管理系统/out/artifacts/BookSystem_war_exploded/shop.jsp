<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2017/6/11
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/css/shop-basic.css"/>
    <link rel="stylesheet" type="text/css" href="assets/css/shop-recommend.css"/>
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/css/basic.css" rel="stylesheet" />
    <link href="assets/css/custom.css" rel="stylesheet" />
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    <title>书城</title>

</head>

<body>
<div id="container">
    <div id="top">
        <div class="top_menu">
            <ul>
                <li>
                    <a href="#" style="background-color: #333;">我的书城</a>
                </li>
                <li style="float:right">
                    <a href="/login.jsp">个人中心</a>
                </li>
            </ul>
        </div>
    </div>
    <div id="honourBox">
        <c:if test="${allBooks != null}">
            <c:forEach items="${allBooks}" var="book">
                <figure>
                    <img src="assets/img/1.jpg" alt="${book.name}">
                    <figcaption><strong class="caption"> &lt; ${book.name} &gt; </strong>${book.note}......
                    </figcaption>
                    <div>
                        <em class="ReleaseTime">上架日期:${book.credate}</em>
                        <span class="purchase"><button class="btn btn-danger"
                                onclick="purchase(${book.bid},null,${book.price});"> ${book.price}元
                        </button></span>
                    </div>
                    <br>

                    <div class="type">分类: ${book.item.name}</div>
                </figure>
            </c:forEach>
        </c:if>

    </div>

</div>
</body>

<script src="assets/js/jquery-1.10.2.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/jquery.metisMenu.js"></script>
<script src="assets/js/custom.js"></script>


<script>

    $.ajax({
        type: "GET",
        url: "<%=basePath%>pages/back/books/shopServlet/booksList",
        success: function (data) {
        },
        error: function (e) {
            alert(e);
        },
    });

    function purchase(bid, aid, price) {
        $.ajax({
            type: "POST",
            url: "<%=basePath%>pages/back/books/shopServlet/purchase",
            data: {
                "bid": bid,
                "aid": "admin",
                "price": price
            },
            success: function (data) {
                document.write(data);
            },
            error: function (e) {
                console.log(e);
            }
        });


    }


</script>

</html>