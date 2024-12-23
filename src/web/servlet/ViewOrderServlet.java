package web.servlet;

import domain.Account;
import domain.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewOrderServlet extends HttpServlet {
    private static final String VIEW_MY_ORDER = "/WEB-INF/jsp/order/myOrders.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String msg = null;

        Account account = (Account) session.getAttribute("loginAccount");

        OrderService orderService = new OrderService();

        List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
        session.setAttribute("orderList", orderList);

        req.getRequestDispatcher(VIEW_MY_ORDER).forward(req, resp);
    }
}
