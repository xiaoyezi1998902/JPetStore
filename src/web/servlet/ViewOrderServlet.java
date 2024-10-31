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

public class ViewOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String msg = null;

        Account account = (Account) session.getAttribute("loginAccount");

        OrderService orderService = new OrderService();
        Order order = (Order) session.getAttribute("order");
        order = orderService.getOrder(order.getOrderId());

        if (account.getUsername().equals(order.getUsername())) {
            req.getRequestDispatcher(VIEW_ORDER).forward(req, resp);
        } else {
            order = null;
            msg = "You may only view your own orders.";
            req.setAttribute("errorMsg", msg);
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        }
    }
}
