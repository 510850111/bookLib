<%--<%@ taglib prefix="c" uri="http://127.0.0.1:8080/c" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset = UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2017/5/25
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <base href="<%=basePath%>">
    <title>个人中心</title>
    <link href="assets/css/bootstrap.css" rel="stylesheet"/>
    <link href="assets/css/font-awesome.css" rel="stylesheet"/>
    <link href="assets/css/basic.css" rel="stylesheet"/>
    <link href="assets/css/custom.css" rel="stylesheet"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
</head>
<body >
<div id="wrapper">
    <jsp:include page="back/header.jsp"></jsp:include>

    <div id="page-wrapper">
        <div id="page-inner">
            <c:if test="${allList != null}">
                <table class="table table-border">
                    <tr>
                        <th>订单号</th>
                        <th>图书编号</th>
                        <th>购买者</th>
                        <th>价格</th>
                        <th>创建日期</th>
                        <th>状态</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${allList}" var="all">
                        <tr>
                            <td>${all.item.iid}</td>
                            <td>${all.bid}</td>
                            <td>${aid}</td>
                            <td>${all.price}</td>
                            <td>${all.credate}</td>
                            <c:if test="${all.status == 1}"><td>已付款</td></c:if>
                            <c:if test="${all.status == 0}"><td>已购买但未付款</td></c:if>
                            <td>${all.note}</td>
                            <td>
                                <button class="btn btn-danger" onclick="deletePurchaseListByPid(${all.item.iid})">删除
                                </button>
                            </td>

                        </tr>
                    </c:forEach>
                </table>
                <div class="col-md-5 col-md-offset-3">
                    <jsp:include page="/pages/split_bar.jsp"></jsp:include>
                </div>
            </c:if>
        </div>
    </div>
</div>


<%--<jsp:include page="footer.jsp"></jsp:include>--%>
<script src="assets/js/jquery-1.10.2.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/jquery.metisMenu.js"></script>
<script src="assets/js/custom.js"></script>
</body>
<script>

    $.ajax({
        type: "POST",
        url: "<%=basePath%>pages/back/AdminServlet/purchaseList",
        data: {
            "aid": "admin"
        },
        success: function (data) {
//            document.write(data);

        },
        error: function (e) {
            console.log(e);
        }
    });

    function deletePurchaseListByPid(pid) {
        $.ajax({
            type: "POST",
            url: "<%=basePath%>pages/back/AdminServlet/deleteByPid",
            data:{
                "pid":pid
            },
            success: function (data) {
                document.write(data);
            },
            error: function (e) {
//                alert(e);
                console.log(e);
            }
        });
    }

</script>
</html>


