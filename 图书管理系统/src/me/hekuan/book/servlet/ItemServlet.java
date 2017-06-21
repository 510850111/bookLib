package me.hekuan.book.servlet;

import me.hekuan.book.factory.ServiceFactory;
import me.hekuan.book.vo.Item;
import me.hekuan.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "itemServlet", urlPatterns = "/pages/back/item/itemServlet/*")
public class ItemServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp"; // 定义错误页面
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("insert".equals(status)) {
                path = this.insert(request);
            }
            if ("list".equals(status)) {
                path = this.list(request);
            }
            if ("delect".equals(status)) {
                try {
                    path = this.delete(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if ("update".equals(status)) {
                try {
                    path = this.update(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }


    public String update(HttpServletRequest request) throws Exception {
        String msg = "";
        String url = "";
        Integer iid = Integer.parseInt(request.getParameter("iid"));
        String name = request.getParameter("name");
        String note = request.getParameter("note");

        if ((iid != null) && ValidateUtils.validateEmpty(name) && ValidateUtils.validateEmpty(note)) {
            Item vo = new Item();
            vo.setIid(iid);
            vo.setName(name);
            vo.setNote(note);
            if (ServiceFactory.getIItemServiceInstance().doUpdate(vo)) {
                msg = "修改成功!";
                url = "/pages/back/item/itemServlet/list";
            } else {
                msg = "修改失败!";
                url = "/pages/back/item/itemServlet/list";
            }
        } else {
            msg = "有数据为空!";
            url = "/pages/back/item/itemServlet/list";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        return "/pages/forward.jsp";
    }

    public String delete(HttpServletRequest request) throws Exception {
        String msg = "";
        String url = "";
        Integer iid = Integer.parseInt(request.getParameter("iid"));
        //数据验证
        if (iid != null) {
            Item vo = new Item();
            vo.setIid(iid);
            if (ServiceFactory.getIItemServiceInstance().deleteByIid(iid)) {
                msg = "删除成功!";
                url = "/pages/back/item/itemServlet/list";
            } else {
                msg = "删除失败!";
                url = "/pages/back/item/itemServlet/list";
            }
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        return "/pages/forward.jsp";

    }

    public String list(HttpServletRequest request) {
        try {
            request.setAttribute("allItems", ServiceFactory.getIItemServiceInstance().list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/back/item/item_list.jsp";
    }

    public String insert(HttpServletRequest request) {
        String msg = "";
        String url = "";

        String name = request.getParameter("name");
        String note = request.getParameter("note");
        //进行数据验证
        if (ValidateUtils.validateEmpty(name) && ValidateUtils.validateEmpty(note)) {
            Item vo = new Item();
            vo.setNote(note);
            vo.setName(name);
            try {
                if (ServiceFactory.getIItemServiceInstance().insert(vo)) {
                    msg = "操作成功!";
                    url = "/pages/back/item/item_insert.jsp";
                } else {
                    msg = "数据增加失败!请重试!";
                    url = "/pages/back/item/item_insert.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "数据不能为空!";
            url = "/pages/back/item/item_insert.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        return "/pages/forward.jsp";
    }
}
