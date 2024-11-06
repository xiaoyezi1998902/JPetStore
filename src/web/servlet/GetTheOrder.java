package web.servlet;

import domain.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetTheOrder extends HttpServlet {
    private static final String LOOK_THE_ORDER = "/WEB-INF/jsp/order/lookTheOrder.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int orderId = Integer.parseInt(req.getParameter("orderId"));

        OrderService orderService = new OrderService();

        Order order = orderService.getOrder(orderId);
        session.setAttribute("theOrder", order);

        req.getRequestDispatcher(LOOK_THE_ORDER).forward(req, resp);
    }
}
