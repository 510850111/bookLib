<%@ taglib prefix="c" uri="http://127.0.0.1:8080/c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
            <span class="sr-only">个人中心</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/pages/index.jsp">个人中心</a>
    </div>
    <!--顶部-->
    <div class="header-right">
        <%--<a href="/login.jsp" class="btn btn-danger" title="Logout"> 退出系统</a>--%>
            <button onclick="javascript:window.location.href='/'"  class="btn btn-primary">我的书城</button>
            &nbsp;&nbsp;
           <span> <button type="button" class="btn btn-danger" onclick="logOut()">退出登录</button></span>
    </div>
</nav>
<!-- 导航 -->
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <div class="user-img-div">
                    <img src="assets/img/user.png" class="img-thumbnail" />

                    <div class="inner-text">
                        管理员: <c:if test="${aid != null}">
                        ${aid}
                    </c:if>
                        <br />
                        <small>上次登录日期:
                            <c:if test="${lastdate != null}">
                                ${lastdate}
                            </c:if>
                        </small>
                    </div>
                </div>
            </li>

                <ul class="nav nav-second-level">
                    <li>
                        <a class="fa" href="/pages/back/changePassword.jsp">更改密码</a>
                    </li>

                </ul>
            <!--用户信息-->
            <li>
                <a href="#"><i class="fa fa-desktop "></i>用户管理 <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="/pages/back/member/member_insert.jsp"><i class="fa fa-toggle-on"></i>用户录入</a>
                    </li>
                </ul>
            </li>
            <!--分类信息-->
            <li>
                <a href="#"><i class="fa fa-yelp "></i>分类管理 <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="pages/back/item/item_insert.jsp"><i class="fa fa-coffee"></i>增加分类</a>
                    </li>
                    <li>
                        <a href="/pages/back/item/itemServlet/list"><i class="fa fa-flash "></i>分类列表</a>
                    </li>
                </ul>
            </li>
            <!--图书信息-->
            <li>
                <a href="#"><i class="fa fa-yelp "></i>图书管理 <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="/pages/back/books/AdminServlet/insertPro"><i class="fa fa-coffee"></i>增加图书</a>
                    </li>
                    <li>
                        <a href="/pages/back/books/AdminServlet/listSplit"><i class="fa fa-flash "></i>图书列表</a>
                    </li>
                </ul>
            </li>
            <%--<!--借书登记-->--%>
            <%--<li>--%>
                <%--<a href="#"><i class="fa fa-bicycle "></i>借书登记 <span class="fa arrow"></span></a>--%>
                <%--<ul class="nav nav-second-level">--%>
                    <%--<li>--%>
                        <%--<a href="form.html"><i class="fa fa-desktop "></i>借书信息录入 </a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
        </ul>
    </div>
</nav>

<script>
    function logOut(){
        $.ajax({
            type:"POST",
            url:"<%=basePath%>pages/back/AdminServlet/logOut",
            success :  function(data){
                document.write(data);

            },error:function(e){
                alert(e);
            }
        });
    }
</script>