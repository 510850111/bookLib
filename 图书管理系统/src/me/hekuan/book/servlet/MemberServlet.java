package me.hekuan.book.servlet;

import me.hekuan.book.factory.ServiceFactory;
import me.hekuan.book.vo.Member;
import me.hekuan.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "memberServlet",urlPatterns = "/pages/back/member/MemberServlet/*")
public class MemberServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp"; // 定义错误页面
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
        if(status != null){
            if ("insert".equals(status)){
               path = this.insert(request);
            }
        }
        request.getRequestDispatcher(path).forward(request,response);
    }

    public String insert(HttpServletRequest request) {
        String url = "";
        String msg = "";
        //接收数据
        String mid = request.getParameter("mid");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int sex = Integer.parseInt(request.getParameter("sex"));
        String phoneNumber = request.getParameter("phoneNumber");
        // 验证数据是否为空
        if(ValidateUtils.validateEmpty(mid) &&
                ValidateUtils.validateEmpty(name) &&
                ValidateUtils.validateEmpty(phoneNumber) ){
            Member vo = new Member();
            vo.setMid(mid);
            vo.setName(name);
            vo.setAge(age);
            vo.setSex(sex);
            vo.setPhoneNumber(phoneNumber);
            try {
                if (ServiceFactory.getIMemberServiceInstance().insert(vo)){
                    url = "/pages/back/member/member_insert.jsp";
                    msg = "用户数据增加成功!";
                }else{
                    url = "/pages/back/member/member_insert.jsp";
                    msg = "用户数据增加失败!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            url = "/pages/back/member/member_insert.jsp";
            msg = "数据不能为空!";
        }
        request.setAttribute("url",url);
        request.setAttribute("msg",msg);
        return "/pages/forward.jsp";
    }
}
