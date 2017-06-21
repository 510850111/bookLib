<%@ page import="me.hekuan.util.MD5Code" %>
<%--
  Created by IntelliJ IDEA.
  User: Oscar
  Date: 2017/5/25
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <%
//        System.out.println(new MD5Code().getMD5ofStr("adminhekuan"));
    %>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>个人中心</title>
    <link href="assets/css/bootstrap.css" rel="stylesheet"/>
    <link href="assets/css/font-awesome.css" rel="stylesheet"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>

    <script type="text/javascript" src="validate/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="validate/js/jquery.validate.min.js"></script>
    <script type="text/javascript" src="validate/js/jquery.metadata.js"></script>
    <script type="text/javascript" src="validate/js/additional-methods.min.js"></script>
    <script type="text/javascript" src="validate/js/Message_zh_CN.js"></script>
    <script type="text/javascript" src="validate/js/login.js"></script>
</head>
<body style="background-color: #E2E2E2;">
<div class="container">
    <div class="row text-center " style="padding-top:100px;">
        <div class="col-md-12">
            <h2>个人中心</h2>
        </div>
    </div>
    <div class="row ">

        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">

            <div class="panel-body">
                <form id="loginForm" role="form" action="<%=basePath%>pages/back/AdminServlet/login" method="post">
                    <hr/>
                    <br/>

                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input id="aid" name="aid" type="text" class="form-control" placeholder="账号 " value="admin"/>
                    </div>
                    <div class="form-group input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input id="password" name="password" type="password" class="form-control" placeholder="密码" value="admin"/>
                    </div>

                    <div style="text-align: center">
                        <button type="button" class="btn btn-primary" style="width: 48%" value="">注册</button>
                        <button type="submit" class="btn btn-primary" style="width: 48%" value="">登陆</button>
                    </div>

                    <hr/>
                    <div style="text-align: center"><a href="#">忘记密码 ? </a> | <a href="#">联系我们：support@hekuan.me</a></div>
                </form>
            </div>

        </div>
    </div>
</div>
</body>
</html>
