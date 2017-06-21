<%@ taglib prefix="c" uri="http://127.0.0.1:8080/c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <title>图书列表</title>
    <link href="assets/css/bootstrap.css" rel="stylesheet"/>
    <link href="assets/css/font-awesome.css" rel="stylesheet"/>
    <link href="assets/css/basic.css" rel="stylesheet"/>
    <link href="assets/css/custom.css" rel="stylesheet"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
</head>
<body>
<div id="wrapper">
    <jsp:include page="/pages/back/header.jsp"></jsp:include>

    <!-- 此处编写内容  -->
    <div id="page-wrapper">
        <div id="page-inner">
            <c:if test="${allBooks != null}">
                <table class="table table-border">
                    <tr>
                        <th>编号</th>
                        <th>分类名称</th>
                        <th>图书名称</th>
                        <th>创建日期</th>
                        <th>管理员</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${allBooks}" var="book">
                        <tr>
                            <td>${book.bid}</td>
                            <td>${book.item.name}</td>
                            <td>${book.name}</td>
                            <td>${book.credate}</td>
                            <td>${book.admin.aid}</td>
                            <td>
                                <c:if test="${book.status == 1}">上架</c:if>
                                <c:if test="${book.status == 0}">已下架</c:if>
                            </td>
                            <td>
                                <button class="btn btn-primary"
                                        onclick=updateBook(${book.bid},"${book.name}","${book.status}")>修改
                                </button>
                                <span>&nbsp;&nbsp;
                                    <button class="btn btn-danger" onclick="deleteByBid(${book.bid})">删除</button>
                                </span>
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


<jsp:include page="/pages/back/footer.jsp"></jsp:include>
<script src="assets/js/jquery-1.10.2.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/jquery.metisMenu.js"></script>
<script src="assets/js/custom.js"></script>

<script type="text/javascript" src="validate/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="validate/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="validate/js/jquery.metadata.js"></script>
<script type="text/javascript" src="validate/js/additional-methods.min.js"></script>
<script type="text/javascript" src="validate/js/Message_zh_CN.js"></script>
<script type="text/javascript" src="validate/item_insert.js"></script>

<script>
    function updateBook(bid, name,status) {
        var path = "<%=basePath%>pages/back/item/itemServlet/update";
        var name = window.prompt("当前编号:" + bid + "\n\n" + "请输入图书名称:", name);
        var status = window.prompt("当前编号:" + bid + "\n\n" + "图书名称:" + name + "\n\n" + "请输入状态:上架为1/下架为0", status);
        if(status != "0" || status != "1"){
            alert("您输入的状态为:"+status +typeof status+ ",不符合规则,请重新输入.");
            return;
        }
        $.ajax({
            type: 'POST',
            url: path,
            data: {
                'iid': bid,
                'name': name,
                'status': status
            },
            success: function (data) {
                document.write(data);
            },
            error: function (e) {
                alert(e);
            }
        });
    }

    function deleteByBid(bid) {
        var path = "<%=basePath%>pages/back/books/AdminServlet/deleteByBid";
        if (confirm("确定要删除吗?")) {
            $.ajax({
                type: 'POST',
                url: path,
                data: {
                    'bid': bid
                },
                success: function (data) {
                    document.write(data);
                },
                error: function (e) {
                    alert(e);
                }
            });
        }
    }


</script>
</body>
</html>
