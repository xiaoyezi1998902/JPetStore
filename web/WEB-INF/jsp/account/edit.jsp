<%@ include file="../common/top.jsp"%>
<div id="Catalog">
    <h3>Account Information</h3>
    <table>
        <td> Username:</td>
        <td>${sessionScope.loginAccount.username}</td>
        <tr>
            <td>Email:</td>
            <td>${sessionScope.loginAccount.email}</td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td>${sessionScope.loginAccount.phone}</td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td>${sessionScope.loginAccount.address1}</td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td>${sessionScope.loginAccount.address2}</td>
        </tr>
        <tr>
            <td>City:</td>
            <td>${sessionScope.loginAccount.city}</td>
        </tr>
        <tr>
            <td>State:</td>
            <td>${sessionScope.loginAccount.state}</td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td>${sessionScope.loginAccount.zip}</td>>
        </tr>
        <tr>
            <td>Country:</td>
            <td>${sessionScope.loginAccount.country}</td>
        </tr>
    </table>

    <h3>Profile Information</h3>

    <table>
        <tr>
            <td>Language Preference:</td>
            <td>${sessionScope.loginAccount.languagePreference}</td>
        </tr>
        <tr>
            <td>Favourite Category:</td>
            <td>${sessionScope.loginAccount.favouriteCategoryId}</td>
        </tr>
        <tr>
            <td>Enable MyList</td>
            <td>${sessionScope.loginAccount.listOption}</td>
        </tr>
    </table>
    <a href="changeForm"><input type="submit" name="new" value="change Account Information"></a><br>
    <a href="viewOrder">myOrder</a>
</div>

<%@ include file="../common/bottom.jsp"%>

