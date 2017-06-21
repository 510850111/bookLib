package me.hekuan.book.servlet;

import me.hekuan.book.factory.ServiceFactory;
import me.hekuan.book.vo.Books;
import me.hekuan.util.Validate.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "shopServlet", urlPatterns = "/pages/back/books/shopServlet/*")
public class ShopServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp"; // 定义错误页面
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            System.out.println("status:"+status);
            if ("booksList".equals(status)) {
                path = this.booksList(request);
            }
            if("purchase".equals(status)){
                path = this.purchase(request);
            }

        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public String purchase(HttpServletRequest request) {
        String msg = "";
        String url = "";
        Integer bid = Integer.parseInt(request.getParameter("bid"));
        String aid = request.getParameter("aid");
        String price = request.getParameter("price");
        String note = request.getParameter("note");
        if (note == null){note = "";}


        if (bid != null && ValidateUtils.validateEmpty(aid)) {
            Books vo = new Books();
            vo.setBid(bid);
            vo.setPrice(Integer.parseInt(price));
            vo.setNote(note);
            vo.setStatus(1);

            try {
                if (ServiceFactory.getIBooksServiceInstance().purchaseByBid(vo, aid)) {
                    msg = "购买成功";
                    url = "/shop.jsp";
                } else {
                    msg = "购买失败";
                    url = "/shop.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String booksList(HttpServletRequest request) {
        String msg = "";
        String url = "/";
        String name = "书名";

        BooksServlet booksServlet = new BooksServlet();
        Map<String,Object> map = booksServlet.listSplitByShopServlet();

        request.getSession().setAttribute("allBooks", map.get("allBooks"));
        request.getSession().setAttribute("allRecorders", map.get("allCounts"));

        request.getSession().setAttribute("msg", name);
        request.getSession().setAttribute("url", url);
        return "/pages/forward.jsp";
    }
}
