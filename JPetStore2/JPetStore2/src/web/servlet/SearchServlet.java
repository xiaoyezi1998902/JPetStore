package web.servlet;

import domain.Search;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    private CatalogService catalogService;
    private static final String Search ="/WEB-INF/jsp/catalog/search.jsp";
    private static final String Searchnotfound ="/WEB-INF/jsp/catalog/searchnotfound.jsp";
    private static final String Searchitem ="/WEB-INF/jsp/catalog/searchitem.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId=req.getParameter("keyword");
        catalogService=new CatalogService();
        List<Search> searchList =catalogService.getSearchList(productId);
        List<Search> nameSearchList =catalogService. nameSearchList(productId);
        Search itemSearchList =catalogService.itemSearchList(productId);

        if(!searchList.isEmpty() && itemSearchList==null && nameSearchList.isEmpty()){
            HttpSession session=req.getSession();
            session.setAttribute("searchList",searchList);
            req.getRequestDispatcher(Search).forward(req,resp);
        }
        else if(searchList.isEmpty()&&itemSearchList==null&&!nameSearchList.isEmpty()){
            HttpSession session=req.getSession();
            session.setAttribute("searchList",nameSearchList);
            req.getRequestDispatcher(Search).forward(req,resp);
        }
        else if(searchList.isEmpty()&&itemSearchList!=null&&nameSearchList.isEmpty()){
            HttpSession session=req.getSession();
            session.setAttribute("searchList",itemSearchList);
            req.getRequestDispatcher(Searchitem).forward(req,resp);

        }
        else req.getRequestDispatcher(Searchnotfound).forward(req,resp);
    }
}
