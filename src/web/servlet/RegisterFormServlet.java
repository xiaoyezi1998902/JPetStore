package web.servlet;

import domain.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterFormServlet extends HttpServlet {
        private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/register.jsp";
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signOn.jsp";
    private String username;
    private String password;
    private String verifyPassword;
    private String msg;
    private Account account;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        HttpSession session = req.getSession();
        account = (Account) session.getAttribute("account");
        account = null;
        session.setAttribute("account", account);

        //获得输入的验证码值
        String value1=req.getParameter("vCode");
        /*获取图片的值*/
        String value2=(String)session.getAttribute("checkcode");
        Boolean isSame = false;
        /*对比两个值（字母不区分大小写）*/
        if(value2.equalsIgnoreCase(value1)){
            isSame = true;
        }
        if(!validate()){
            req.setAttribute("registerMsg", this.msg);
            req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);
        }
        else {

            if(isSame){
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
            account.setBannerOption(true);
            account.setLanguagePreference("555");
            account.setFirstName("555");
            account.setLastName("555");
            account.setStatus("55");
            account.setFavouriteCategoryId("CATS");
            AccountService accountService = new AccountService();
            accountService.insertAccount(account);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            }else {
                this.msg="验证码错误";
                req.setAttribute("registerMsg", this.msg);
                req.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(req, resp);

        }}

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


