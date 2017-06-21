package me.hekuan.book.servlet;

import me.hekuan.book.factory.ServiceFactory;
import me.hekuan.book.vo.Admin;
import me.hekuan.book.vo.Books;
import me.hekuan.book.vo.Item;
import me.hekuan.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "booksServlet", urlPatterns = "/pages/back/books/AdminServlet/*")
public class BooksServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp"; // 定义错误页面
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            System.out.println("status = " + status);
            if ("insertPro".equals(status)) {
                path = this.insertPro(request);
            } else if ("insert".equals(status)) {
                try {
                    path = this.insert(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("listSplit".equals(status)) {
                path = this.listSplit(request, response);
            } else if ("deleteByBid".equals(status)) {
                try {
                    path = this.deleteByBid(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        request.getRequestDispatcher(path).forward(request, response);

    }

    public String deleteByBid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        String url = "";
        Integer bid = Integer.parseInt(request.getParameter("bid"));
        //数据验证
        if (bid != null) {
            Books vo = new Books();
            vo.setBid(bid);
            if (ServiceFactory.getIBooksServiceInstance().deleteByBid(bid)) {
                msg = "删除成功!";
                url = "/pages/back/books/AdminServlet/listSplit";
            } else {
                msg = "删除失败!";
                url = "/pages/back/books/AdminServlet/listSplit";
            }
        }

        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        return "/pages/forward.jsp";
    }

    public String listSplit(HttpServletRequest request, HttpServletResponse response) {
        Integer currentPage = 1;
        Integer lineSize = 10;

        try {
            currentPage = Integer.parseInt(request.getParameter("cp"));
        } catch (Exception e) {
        }
        try {
            lineSize = Integer.parseInt(request.getParameter("ls"));
        } catch (Exception e) {
        }
        String keyWord = request.getParameter("kw");
        String column = request.getParameter("col");

        if (keyWord == null) {
            keyWord = "";
        }
        if (column == null) {
            column = "name";
        }
        try {
            Map<String, Object> map = ServiceFactory.getIBooksServiceInstance().listBySplit(column, keyWord, currentPage, lineSize);
            request.getSession().setAttribute("allBooks", map.get("allBooks"));
            request.getSession().setAttribute("allRecorders", map.get("allCounts"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("url", "/pages/back/books/AdminServlet/listSplit?cp=1&ls=10");
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);

        return "/pages/back/books/books_list.jsp";
    }

    public Map<String, Object> listSplitByShopServlet() {
        Integer currentPage = 1;
        Integer lineSize = 10;

        String keyWord = "";
        String column = "name";
        Map<String, Object> retMap = null;

        try {
            Map<String, Object> map = ServiceFactory.getIBooksServiceInstance().listBySplit(column, keyWord, currentPage, lineSize);
            retMap = map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }

    public String insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = "";
        String url = "";

        //取得页面中的数据
        String name = request.getParameter("name");
        String aid = (String) request.getSession().getAttribute("aid");
        Integer iid = Integer.parseInt(request.getParameter("iid"));
        String note = request.getParameter("note");
        Integer price = Integer.parseInt(request.getParameter("price"));


        //判断数据是否为空
        if (ValidateUtils.validateEmpty(name) && ValidateUtils.validateEmpty(aid) && ValidateUtils.validateEmpty(iid.toString()) && ValidateUtils.validateEmpty(note) && (price != null)) {

            Books vo = new Books();
            vo.setName(name);
            vo.setPrice(price);

            Admin admin = new Admin();
            admin.setAid(aid);
            vo.setAdmin(admin);

            Item item = new Item();
            item.setIid(iid);
            vo.setItem(item);
            vo.setCredate(new Date());
            vo.setStatus(1);//表示上架,0表示下架
            vo.setNote(note);
            if (ServiceFactory.getIBooksServiceInstance().insert(vo)) {
                msg = "操作成功!";
                url = "/pages/back/books/AdminServlet/insertPro";
            }
        } else {
            msg = "你输入的信息有误,请重新输入!";
            url = "/pages/back/books/AdminServlet/insertPro";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);

        return "/pages/forward.jsp";
    }

    public String insertPro(HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = ServiceFactory.getIBooksServiceInstance().findByAdminAndItem();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("allItems", map.get("allItems"));
        request.setAttribute("allAdmins", map.get("allAdmins"));
        return "/pages/back/books/books_insert.jsp";
    }
}
