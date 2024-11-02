<%@ include file="../common/top.jsp"%>
<div id="Catalog">
    <h3>Account Information</h3>
    <form action="changeform" method="post">
        Username:<input type="text" name="username" value="${sessionScope.loginAccount.username}"> <br />
        Email:<input type="text" name="email" value="${sessionScope.loginAccount.email}"> <br />
        Phone:<input type="text" name="phone" value="${sessionScope.loginAccount.phone}"> <br />
        Address1:<input type="text" name="address1" value="${sessionScope.loginAccount.address1}"> <br />
        Address2:<input type="text" name="address2" value="${sessionScope.loginAccount.address2}"> <br />
        City:<input type="text" name="city" value="${sessionScope.loginAccount.city}"> <br />
        State:<input type="text" name="state" value="${sessionScope.loginAccount.state}"> <br />
        Zip:<input type="text" name="zip" value="${sessionScope.loginAccount.zip}"> <br />
        Country:<input type="text" name="country" value="${sessionScope.loginAccount.country}"> <br />
        Language Preference:<input type="text" name="languagePreference" value="${sessionScope.loginAccount.languagePreference}"> <br />
        Favourite Category:<input type="text" name="favouriteCategoryId" value="${sessionScope.loginAccount.favouriteCategoryId}"> <br />
        Enable MyList:<input type="text" name="listOption" value="${sessionScope.loginAccount.listOption}"> <br />
        <input type="submit" name="newAccount" value="Save Account Information">
    </form>

</div>

<%@ include file="../common/bottom.jsp"%>

