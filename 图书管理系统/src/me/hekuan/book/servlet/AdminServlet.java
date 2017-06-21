package me.hekuan.book.servlet;

import me.hekuan.book.factory.ServiceFactory;
import me.hekuan.book.vo.Admin;
import me.hekuan.book.vo.Books;
import me.hekuan.util.MD5Code;
import me.hekuan.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.System;
import java.util.List;

@WebServlet(name = "adminServlet", urlPatterns = "/pages/back/AdminServlet/*")
public class AdminServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp"; // 定义错误页面
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        System.out.println("status = " + status);
        if (status != null) {
            if ("login".equals(status)) {
                path = this.login(request);
            }
            if ("changeAdminPassword".equals(status)) {
                path = this.changeAdminPassword(request);
            }
            if ("logOut".equals(status)) {
                path = this.logOut(request);
            }
            if ("purchaseList".equals(status)) {
                path = this.purchaseList(request);
            }
            if ("deleteByPid".equals(status)) {
                path = this.deleteByPid(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public String deleteByPid(HttpServletRequest request){
        String msg = "";
        String url = "";
        Integer pid = Integer.parseInt(request.getParameter("pid"));
        //数据验证
        if (pid != null) {
            try {
                if (ServiceFactory.getIAdminServiceInstance().deleteByPid(pid)) {
                    msg = "删除成功!";
                    url = "/pages/index.jsp";
                } else {
                    msg = "删除失败!";
                    url = "/pages/index.jspt";
                }
            }catch (Exception e){

            }
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        return "/pages/forward.jsp";
    }

    public String purchaseList(HttpServletRequest request) {
        List<Books> all;
        String msg = "";
        String url = "";

        String aid = request.getParameter("aid");
        if (ValidateUtils.validateEmpty(aid)) {
            try {
                all = ServiceFactory.getIBooksServiceInstance().purchaseList(aid);
                request.getSession().setAttribute("allList", all);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "/pages/index.jsp";

    }

    /**
     * 实现登录
     *
     * @param request
     * @return
     */
    public String login(HttpServletRequest request) {
        String msg = ""; //表示提示信息
        String url = ""; // 表示跳转路径
        // 取得页面中传递过来数据
        String aid = request.getParameter("aid");
        String password = request.getParameter("password");
        // 判断数据是否为空
        if (ValidateUtils.validateEmpty(aid) && ValidateUtils.validateEmpty(password)) { // 表示数据存在
            Admin vo = new Admin();
            vo.setAid(aid); // 取得参数
            vo.setPassword(new MD5Code().getMD5ofStr(password + aid)); // 需要加盐处理
            try {
                if (ServiceFactory.getIAdminServiceInstance().login(vo)) {
                    request.getSession().setAttribute("aid", aid); // 保存aid
                    request.getSession().setAttribute("lastdate", vo.getLastdate()); // 保存aid
                    msg = "登录成功！";
                    url = "/pages/index.jsp";
                } else {
                    msg = "登录失败，错误的ID或密码!";
                    url = "/login.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "数据不能为空!";
            url = "/login.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    /**
     * 实现修改密码
     *
     * @param request
     * @return
     */
    public String changeAdminPassword(HttpServletRequest request) {
        String msg = ""; //表示提示信息
        String url = ""; // 表示跳转路径
        // 取得页面中传递过来数据
        String aid = (String) request.getSession().getAttribute("aid");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        // 判断数据是否为空
        if (ValidateUtils.validateEmpty(aid) && ValidateUtils.validateEmpty(password) && ValidateUtils.validateEmpty(newPassword)) { // 表示数据存在
            Admin vo = new Admin();
            vo.setAid(aid);
            vo.setPassword(new MD5Code().getMD5ofStr(password + aid)); // 需要加盐处理
            try {
                if (true) {
                    request.getSession().setAttribute("aid", aid); // 保存aid
                    vo.setPassword(new MD5Code().getMD5ofStr(newPassword + aid)); // 更改为新密码
                    if (ServiceFactory.getIAdminServiceInstance().changeAdminPassword(vo)) {
                        msg = "修改密码成功,需要重新登录!";
                        url = "/login.jsp";
                    } else {
                        msg = "未知错误!修改失败!";
                        url = "/login.jsp";
                    }

                } else {
                    msg = "账户与密码不匹配!";
                    url = "/login.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "有数据为空!";
            url = "/pages/back/changePassword.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String logOut(HttpServletRequest request) {
        String msg = ""; //表示提示信息
        String url = ""; // 表示跳转路径
        request.setAttribute("aid", null);
        msg = "注销成功";
        url = "/login.jsp";
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }


}
