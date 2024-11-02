package web.servlet;

import domain.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterFormServlet extends HttpServlet {
        private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/register.jsp";
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private String username;
    private String password;
    private String msg;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        if(!validate()){
            req.setAttribute("registerMsg", this.msg);
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
        }
        else {
            Account account = new Account();
            account.setUsername(this.username);
            account.setPassword(this.password);
            account.setListOption(true);
            account.setCity("555");
            account.setCountry("555");
            account.setState("55");
            account.setZip("555");
            account.setEmail("555");
            account.setPhone("555");
            account.setBannerName("555");
            account.setAddress1("555");
            account.setAddress2("555");
            account.setBannerOption(1);
            account.setLanguagePreference("555");
            account.setFirstName("555");
            account.setLastName("555");
            account.setStatus("55");
            account.setFavouriteCategoryId("CATS");
            AccountService accountService = new AccountService();
            accountService.insertAccount(account);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            }

    }
    private boolean validate(){
        if(this.username==null || this.username.equals("")){
            this.msg="用户名不能为空";
            return false;
        }
        if(this.password==null || this.password.equals("")){
            this.msg="密码不能为空";
            return false;
        }
        return true;

    }
}


