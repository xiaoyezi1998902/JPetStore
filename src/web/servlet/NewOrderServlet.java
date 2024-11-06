package web.servlet;

import domain.*;
import persistence.CartDao;
import persistence.LogDao;
import persistence.implement.CartDaoImpl;
import persistence.implement.LogDaoImpl;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPING_FORM = "/WEB-INF/jsp/order/shippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrderForm.jsp";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";

    private OrderService orderService = new OrderService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Order order = (Order) session.getAttribute("order");

        Cart cart = (Cart) session.getAttribute("cart");
        boolean shippingAddressRequired = req.getParameter("shippingAddressRequired") != null;
        String confirmed = req.getParameter("confirmed");
        String looked = req.getParameter("looked");
        String msg = null;
        Account account = (Account) session.getAttribute("loginAccount");
        Date date = new Date(System.currentTimeMillis());

        if (order == null) {
            order = new Order();
            order.initOrder(account, cart);

            order.setCardType(req.getParameter("order.cardType"));
            order.setCreditCard(req.getParameter("order.creditCard"));
            order.setExpiryDate(req.getParameter("order.expiryDate"));
            order.setBillToFirstName(req.getParameter("order.billToFirstName"));
            order.setBillToLastName(req.getParameter("order.billToLastName"));
            order.setBillAddress1(req.getParameter("order.billAddress1"));
            order.setBillAddress2(req.getParameter("order.billAddress2"));
            order.setBillCity(req.getParameter("order.billCity"));
            order.setBillState(req.getParameter("order.billState"));
            order.setBillZip(req.getParameter("order.billZip"));
            order.setBillCountry(req.getParameter("order.billCountry"));

            session.setAttribute("order", order);
        }

        if (looked == null) {
            looked = "";
        }

        if (looked.equals("true") && (confirmed == null || confirmed.equals(""))) {
            order.setUsername(account.getUsername());
            order.setOrderDate(date);
            order.setShipToFirstName(req.getParameter("order.shipToFirstName"));
            order.setShipToLastName(req.getParameter("order.shipToLastName"));
            order.setShipAddress1(req.getParameter("order.shipAddress1"));
            order.setShipAddress2(req.getParameter("order.shipAddress2"));
            order.setShipCity(req.getParameter("order.shipCity"));
            order.setShipState(req.getParameter("order.shipState"));
            order.setShipZip(req.getParameter("order.shipZip"));
            order.setShipCountry(req.getParameter("order.shipCountry"));
            order.setCourier("UPS");
            order.setStatus("P");
            order.setLocale("onlineShop");

            order.setTotalPrice(cart.getSubTotal());
        }

        CartDao cartDao = new CartDaoImpl();

        if (shippingAddressRequired) {
            shippingAddressRequired = false;
            req.getRequestDispatcher(SHIPPING_FORM).forward(req, resp);
        } else if (confirmed == null || confirmed.equals("")) {
            req.getRequestDispatcher(CONFIRM_ORDER).forward(req, resp);
        } else if (order != null) {
            this.orderService.insertOrder(order);
            cart = new Cart();
            session.setAttribute("cart", cart);
            cartDao.clearTheCart(account.getUsername());

            Log log = new Log();
            log.setLogTime(new Date());
            log.setUserName(account.getUsername());
            log.setTitle("订单信息");
            log.setContent("用户" + account.getUsername() + "创建了id号为" + order.getOrderId() + "的订单");
            LogDao logDao = new LogDaoImpl();
            logDao.InsertLog(log);

            msg = "Thank you, your order has been submitted.";
            req.setAttribute("errorMsg", msg);
            req.getRequestDispatcher(VIEW_ORDER).forward(req, resp);
        } else {
            msg = "An error occurred processing your order (order was null).";
            req.setAttribute("errorMsg", msg);
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
