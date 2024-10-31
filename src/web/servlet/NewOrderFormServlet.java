package web.servlet;

import domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrderForm.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");

        List<String> cardList = new ArrayList<>();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");
        session.setAttribute("creditCardTypes", cardList);

        if (loginAccount == null) {
            resp.sendRedirect("signOnForm");
        } else {
            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req, resp);
        }
    }
}
