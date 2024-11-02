<%@ include file="../common/top.jsp"%>
<div id="Catalog">
    <form action="registerform" method="post">
        <p>User Information</p>
        <c:if test="${requestScope.registerMsg !=null}">
            <p><font color="red">${requestScope.signOnMsg}</font></p>
        </c:if>
        <p>
            Username:<input type="text" name="username"> <br />
            Password:<input type="text" name="password"> <br/>
            Repeat password:<input type="text" name="repeatedPassword">
        </p>
        <input type="submit" name="newAccount" value="Save Account Information">
    </form>
</div>

<%@ include file="../common/bottom.jsp"%>