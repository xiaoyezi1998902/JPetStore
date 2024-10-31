<%@ include file="../common/top.jsp"%>

<div id="Catalog">
<form action="newOrder" method="post">
    <table>
        <tr>
            <th colspan=2>Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td>
                <select name="order.cardType">
                <c:forEach var="cardType" items="${sessionScope.creditCardTypes}">
                    <option value="${cardType}">${cardType}</option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td><input type="text" name="order.creditCard" /> * Use a fake
                number!</td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><input type="text" name="order.expiryDate" /></td>
        </tr>
        <tr>
            <th colspan=2>Billing Address</th>
        </tr>

        <tr>
            <td>First name:</td>
            <td><input type="text" name="order.billToFirstName" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="order.billToLastName" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><input type="text" size="40" name="order.billAddress1" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><input type="text" size="40" name="order.billAddress2" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><input type="text" name="order.billCity" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><input type="text" size="4" name="order.billState" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><input type="text" size="10" name="order.billZip" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><input type="text" size="15" name="order.billCountry" /></td>
        </tr>

        <tr>
            <td colspan=2><input type="checkbox" name="shippingAddressRequired" />
                Ship to different address...</td>
        </tr>

    </table>

    <input type="submit" value="Continue" />

</form></div>

<%@ include file="../common/bottom.jsp"%>
