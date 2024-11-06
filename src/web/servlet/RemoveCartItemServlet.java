package web.servlet;

import domain.Account;
import domain.Cart;
import domain.Item;
import domain.Log;
import persistence.CartDao;
import persistence.LogDao;
import persistence.implement.CartDaoImpl;
import persistence.implement.LogDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class RemoveCartItemServlet extends HttpServlet {
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String workingItemId = req.getParameter("workingItemId");

        Item item = cart.removeItemById(workingItemId);

        Account account = (Account) session.getAttribute("loginAccount");

        CartDao cartDao = new CartDaoImpl();

        if (account != null) {
            cartDao.removeItem(account.getUsername(), workingItemId);
        }

        if (item == null) {
            session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        } else {
            req.getRequestDispatcher(CART_FORM).forward(req, resp);
            if(session.getAttribute("loginAccount") != null)
            {
                Account loginAccount = (Account) session.getAttribute("loginAccount");
                Log log = new Log();
                log.setLogTime(new Date());
                log.setUserName(loginAccount.getUsername());
                log.setTitle("购物车信息");
                log.setContent("用户" + loginAccount.getUsername() + "将" + item.getProduct().getName() + " " + item.getProduct().getProductId() + "从购物车中移除");
                LogDao logDao = new LogDaoImpl();
                logDao.InsertLog(log);
            }
        }
    }
}
