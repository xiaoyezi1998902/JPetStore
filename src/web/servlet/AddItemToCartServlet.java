package web.servlet;

import domain.Account;
import domain.Cart;
import domain.CartItem;
import domain.Item;
import persistence.CartDao;
import persistence.implement.CartDaoImpl;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private String msg;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        String workingItemId = req.getParameter("workingItemId");

        // 可能是未登录用户
        if (cart == null) {
            cart = new Cart();
            msg = "您还未登录，加入购物车的物品信息无法保存哦！";
            session.setAttribute("reminder", msg);
        }

        CartDao cartDao = new CartDaoImpl();
        Account account = (Account) session.getAttribute("loginAccount");
        Item item = categoryService.getItem(workingItemId);

        if (cart.containsItemId(workingItemId)) {
            CartItem cartItem = cart.incrementQuantityByItemId(workingItemId);
            cartItem.getItem().setProductId(item.getProduct().getProductId());
            cartItem.setDescription(item.getAttribute1() + " " + item.getProduct().getName());

            if (account != null) {
                cartDao.updateCart(account.getUsername(), workingItemId, cartItem.getQuantity(), cartItem.getTotal());
            }
        } else {
            boolean isInStock = categoryService.isItemInStock(workingItemId);

            CartItem cartItem = cart.addItem(item, isInStock);
            cartItem.getItem().setProductId(item.getProduct().getProductId());
            cartItem.setDescription(item.getAttribute1() + " " + item.getProduct().getName());

            if (account != null) {
                cartDao.addItemToCart(cartItem, account.getUsername());
            }
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cartForm");
    }
}
