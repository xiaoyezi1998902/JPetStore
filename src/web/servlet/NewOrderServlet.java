package web.servlet;

import domain.Cart;
import domain.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPING_FORM = "/WEB-INF/jsp/order/shippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";

    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Order order = (Order) session.getAttribute("order");
        Cart cart = (Cart) session.getAttribute("cart");
        boolean shippingAddressRequired = req.getParameter("shippingAddressRequired") != null;
        boolean confirmed = req.getParameter("confirmed") != null;
        String msg = null;

        if (shippingAddressRequired) {
            shippingAddressRequired = false;
            req.getRequestDispatcher(SHIPPING_FORM).forward(req, resp);
        } else if (!confirmed) {
            req.getRequestDispatcher(CONFIRM_ORDER).forward(req, resp);
        } else if (order != null) {
            this.orderService.insertOrder(order);
            cart = new Cart();
            session.setAttribute("cart", cart);
            msg = "Thank you, your order has been submitted.";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(VIEW_ORDER).forward(req, resp);
        } else {
            msg = "An error occurred processing your order (order was null).";
            req.setAttribute("msg", msg);
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        }
    }
}
