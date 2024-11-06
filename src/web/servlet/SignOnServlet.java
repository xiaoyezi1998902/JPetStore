package web.servlet;

import domain.*;
import persistence.CartDao;
import persistence.LogDao;
import persistence.implement.CartDaoImpl;
import persistence.implement.LogDaoImpl;
import service.AccountService;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class SignOnServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signOn.jsp";

    private String username;
    private String password;
    private String msg;
    private Account account;
    @Override
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
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            }
        else {
            AccountService accountService = new AccountService();
            Account loginAccount=accountService.getAccount(username,password);
            if(loginAccount==null){
                this.msg="用户名或密码错误";
                req.setAttribute("signOnMsg", this.msg);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            } else {
                if (isSame){
                    session.setAttribute("loginAccount", loginAccount);

                    if (loginAccount.isListOption()){
                        CategoryService catalogService=new CategoryService();
                        List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                        session.setAttribute("myList", myList);
                    }
                    Log log = new Log();
                    log.setLogTime(new Date());
                    log.setUserName(username);
                    log.setTitle("登录信息");
                    log.setContent("用户" + username + "登录了账号");
                    LogDao logDao = new LogDaoImpl();
                    logDao.InsertLog(log);

                    CartDao cartDao = new CartDaoImpl();

                    List<CartItem> cartItemList = cartDao.getCartItemsByUserId(username);

                    Cart cart = new Cart();
                    cart.setCartItemList(cartItemList);
                    session.setAttribute("cart", cart);
                    session.setAttribute("reminder", null);

                    resp.sendRedirect("mainForm");
                }
                else {
                    this.msg="验证码错误";
                    req.setAttribute("signOnMsg", this.msg);
                    req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
                 }
            }
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
