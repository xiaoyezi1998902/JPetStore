package web.servlet;

import domain.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOnServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private String username;
    private String password;
    private String msg;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        if(!validate()){
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            }
        else {
            AccountService accountService = new AccountService();
            Account loginAccount = accountService.getAccount(username,password);
            if(loginAccount == null){
                this.msg="用户名或密码错误";
                req.setAttribute("signOnMsg", this.msg);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("loginAccount", loginAccount);
                //Object a=session.getAttribute("loginAccount");
                //if (loginAccount.isListOption()){
                    //CatalogService catalogService=new CatalogService;
                    //list<Product> myList =catalogService.getProductListByCatagory(loginAccount.getFavouriteCatagoryId());
                    //session.setAttribute("mylist", mylist);
                //}
                resp.sendRedirect("mainForm");
            }
        }
    }
    private boolean validate() {
        if(this.username==null || this.username.equals("")) {
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
