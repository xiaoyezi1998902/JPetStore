package web.servlet;

import domain.Account;
import domain.Cart;
import domain.CartItem;
import persistence.CartDao;
import persistence.implement.CartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        Account account = (Account) session.getAttribute("loginAccount");
        CartDao cartDao = new CartDaoImpl();

        while(cartItems.hasNext()) {
            CartItem cartItem = (CartItem)cartItems.next();
            String itemId = cartItem.getItem().getItemId();

            try {
                int quantity = Integer.parseInt(req.getParameter(itemId));
                CartItem cartItem1 = cart.setQuantityByItemId(itemId, quantity);
                if (account != null) {
                    cartDao.updateCart(account.getUsername(), itemId, quantity, cartItem1.getTotal());
                }

                if (quantity < 1) {
                    cartItems.remove();
                    if (account != null) {
                        cartDao.removeItem(account.getUsername(), itemId);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
