<%@ include file="../common/top.jsp"%>
<div id="Catalog">
    <form action="registerForm" method="post">
        <p>User Information</p>
        <c:if test="${requestScope.registerMsg !=null}">
            <p><font color="red">${requestScope.registerMsg}</font></p>
        </c:if>
            <p>
                Username:<input type="text" name="username"> <br />
                Password:<input type="text" name="password"> <br/>
               VerificationCode:
                        <input type="text" name="vCode" size="5" maxlength="4"/>
                        <a href="registerForm"><img border="0" src="verificationCode" name="checkcode"></a>
            </p>
            <input type="submit" name="newAccount" value="Save Account Information">
        </form>
</div>

<%@ include file="../common/bottom.jsp"%>

