<%@ taglib prefix="c" uri="http://127.0.0.1:8080/c" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
<body>
<jsp:include page="header.jsp"></jsp:include>
<div id="wrapper">


    <!-- 此处编写内容  -->
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row text-center " style="padding-top:100px;">
                <div class="col-md-12">
                    <h2>更改密码</h2>
                </div>
            </div>
            <div class="row ">

                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

                    <div class="panel-body">
                        <form id="changePasswordForm" role="form"
                              action="<%=basePath%>pages/back/AdminServlet/changeAdminPassword" method="post">
                            <hr/>
                            <br/>

                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input id="aid" name="aid" type="text" class="form-control"
                                       placeholder="" value="${aid}" disabled="true"/>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input id="password" name="password" type="password" class="form-control"
                                       placeholder="原密码 " value=""/>
                            </div>
                            <div class="form-group input-group">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input id="newPassword" name="newPassword" type="password" class="form-control"
                                       placeholder="新密码"
                                       value=""/>
                            </div>

                            <div style="text-align: center">
                                <button type="submit" class="btn btn-primary" style="width: 100%">立即修改</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"></jsp:include>
<script src="assets/js/jquery-1.10.2.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/jquery.metisMenu.js"></script>
<script src="assets/js/custom.js"></script>
</body>
</html>
