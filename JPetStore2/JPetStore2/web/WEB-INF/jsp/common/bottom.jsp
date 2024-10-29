<%@ page import="domain.Account" %>
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="http://www.csu.edu.cn">www.csu.edu.cn</a>
    </div>
    <div id="Banner">
        <c:if test="${sessionScope.loginAccount != null }">
            <%
                Account loginAccount=(Account) session.getAttribute("loginAccount");
//                System.out.println(loginAccount.isBannerOption());
//                System.out.println(loginAccount.getBannerName());
            %>
                <c:if test="${sessionScope.loginAccount.bannerOption}">
                    ${sessionScope.loginAccount.bannerName}
                </c:if>
        </c:if>
    </div>
</div>


<script src="js/productAuto.js"></script>
</body>
</html>