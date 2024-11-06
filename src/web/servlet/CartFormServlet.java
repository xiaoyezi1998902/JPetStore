package web.servlet;

import domain.Account;
import domain.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CartFormServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");

        // 可能是未登录用户
        if (account == null) {
            String msg = "您还未登录，加入购物车的物品信息无法保存哦！";
            session.setAttribute("reminder", msg);
        }

        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
