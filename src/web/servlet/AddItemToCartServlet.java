package web.servlet;

import domain.*;
import persistence.CartDao;
import persistence.LogDao;
import persistence.implement.CartDaoImpl;
import persistence.implement.LogDaoImpl;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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
        }

        CartDao cartDao = new CartDaoImpl();
        Account account = (Account) session.getAttribute("loginAccount");
        Item item = categoryService.getItem(workingItemId);

        // 可能是未登录用户
        if (account == null) {
            msg = "您还未登录，加入购物车的物品信息无法保存哦！";
            session.setAttribute("reminder", msg);
        }

        if (cart.containsItemId(workingItemId)) {
            CartItem cartItem = cart.incrementQuantityByItemId(workingItemId);
            cartItem.getItem().setProductId(item.getProduct().getProductId());
            cartItem.setDescription(item.getAttribute1() + " " + item.getProduct().getName());

            if (account != null) {
                cartDao.updateCart(account.getUsername(), workingItemId, cartItem.getQuantity(), cartItem.getTotal());

                //新增购物车信息日记记录
                Log log = new Log();
                log.setLogTime(new Date());
                log.setUserName(account.getUsername());
                log.setTitle("购物车信息");
                log.setContent("用户" + account.getUsername() + "将" + item.getProduct().getName() + " " + item.getProduct().getProductId() + "添加到购物车中");
                LogDao logDao = new LogDaoImpl();
                logDao.InsertLog(log);
            }
        } else {
            boolean isInStock = categoryService.isItemInStock(workingItemId);

            CartItem cartItem = cart.addItem(item, isInStock);
            cartItem.getItem().setProductId(item.getProduct().getProductId());
            cartItem.setDescription(item.getAttribute1() + " " + item.getProduct().getName());

            if (account != null) {
                cartDao.addItemToCart(cartItem, account.getUsername());

                //新增购物车信息日记记录
                Log log = new Log();
                log.setLogTime(new Date());
                log.setUserName(account.getUsername());
                log.setTitle("购物车信息");
                log.setContent("用户" + account.getUsername() + "将" + item.getProduct().getName() + " " + item.getProduct().getProductId() + "添加到购物车中");
                LogDao logDao = new LogDaoImpl();
                logDao.InsertLog(log);
            }

        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cartForm");
    }
}
