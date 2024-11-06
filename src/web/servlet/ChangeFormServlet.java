package web.servlet;

import domain.Account;
import domain.Product;
import service.AccountService;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChangeFormServlet extends HttpServlet {
    private static final String CHANGE_FORM = "/WEB-INF/jsp/account/change.jsp";
    private static final String EDIT_FORM = "/WEB-INF/jsp/account/edit.jsp";
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private int bannerOption;
    private String bannerName;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CHANGE_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.email = req.getParameter("email");
        this.status = req.getParameter("status");
        this.address1 = req.getParameter("address1");
        this.address2 = req.getParameter("address2");
        this.city = req.getParameter("city");
        this.state = req.getParameter("state");
        this.zip = req.getParameter("zip");
        this.country = req.getParameter("country");
        this.phone = req.getParameter("phone");
        this.favouriteCategoryId = req.getParameter("favouriteCategoryId");
        this.languagePreference = req.getParameter("languagePreference");
        this.listOption = Boolean.parseBoolean(req.getParameter("listOption"));

        HttpSession session = req.getSession();

        Account account = (Account) session.getAttribute("loginAccount");
        AccountService accountService = new AccountService();

        account.setUsername(this.username);
        account.setEmail(this.email);
        account.setStatus(this.status);
        account.setAddress1(this.address1);
        account.setAddress2(this.address2);
        account.setCity(this.city);
        account.setState(this.state);
        account.setZip(this.zip);
        account.setCountry(this.country);
        account.setPhone(this.phone);
        account.setFavouriteCategoryId(this.favouriteCategoryId);
        account.setLanguagePreference(this.languagePreference);
        account.setListOption(this.listOption);
        account.setBannerOption(true);
        // 注意：bottom中用户喜欢的内容也需要变动
        account.setBannerName("<image src=\"images/banner_" + this.favouriteCategoryId.toLowerCase() + ".gif\">");
        accountService.updateAccount(account);

        // 注意：用户喜欢的列表也需要变动
        if (account.isListOption()){
            CategoryService catalogService=new CategoryService();
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            session.setAttribute("myList", myList);
        } else {
            session.setAttribute("myList", null);  // 有可能更改为不需要提示列表
        }

        req.getRequestDispatcher(EDIT_FORM).forward(req, resp);
    }
}
