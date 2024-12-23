package web.servlet;

import domain.Account;
import domain.Item;
import domain.Log;
import domain.Product;
import persistence.LogDao;
import persistence.implement.LogDaoImpl;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ProductFormServlet extends HttpServlet {
    private CategoryService categoryService;
    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        categoryService = new CategoryService();
        Product product =categoryService.getProduct(productId);
        List<Item> itemList = categoryService.getItemListByProduct(productId);

        HttpSession session = req.getSession();
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);
        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
        if(session.getAttribute("loginAccount") != null)
        {
            Account loginAccount = (Account) session.getAttribute("loginAccount");
            Log log = new Log();
            log.setLogTime(new Date());
            log.setUserName(loginAccount.getUsername());
            log.setTitle("浏览信息");
            log.setContent("用户" + loginAccount.getUsername() + "浏览了" + product.getName() + " " + product.getProductId());
            LogDao logDao = new LogDaoImpl();
            logDao.InsertLog(log);
        }
    }
}
