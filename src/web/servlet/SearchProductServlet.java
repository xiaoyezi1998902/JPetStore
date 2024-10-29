package web.servlet;

import domain.Product;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private static final String SEARCH_PRODUCTS_FORM = "/WEB-INF/jsp/catalog/searchProducts.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("keyword");
        CategoryService categoryService = new CategoryService();
        List<Product> productList = categoryService.searchProductList(productName);

        HttpSession session = req.getSession();
        session.setAttribute("productList", productList);
        req.getRequestDispatcher(SEARCH_PRODUCTS_FORM).forward(req, resp);
    }
}
