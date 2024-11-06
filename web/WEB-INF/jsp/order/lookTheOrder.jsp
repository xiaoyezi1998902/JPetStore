<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <th align="center" colspan="2">Order #${sessionScope.theOrder.orderId}
                <fmt:formatDate value="${sessionScope.theOrder.orderDate}"
                                pattern="yyyy/MM/dd hh:mm:ss" /></th>
        </tr>
        <tr>
            <th colspan="2">Payment Details</th>
        </tr>
        <tr>
            <td>Card Type:</td>
            <td><c:out value="${sessionScope.theOrder.cardType}" /></td>
        </tr>
        <tr>
            <td>Card Number:</td>
            <td><c:out value="${sessionScope.theOrder.creditCard}" /> * Fake
                number!</td>
        </tr>
        <tr>
            <td>Expiry Date (MM/YYYY):</td>
            <td><c:out value="${sessionScope.theOrder.expiryDate}" /></td>
        </tr>
        <tr>
            <th colspan="2">Billing Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td><c:out value="${sessionScope.theOrder.billToFirstName}" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><c:out value="${sessionScope.theOrder.billToLastName}" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><c:out value="${sessionScope.theOrder.billAddress1}" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><c:out value="${sessionScope.theOrder.billAddress2}" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><c:out value="${sessionScope.theOrder.billCity}" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><c:out value="${sessionScope.theOrder.billState}" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><c:out value="${sessionScope.theOrder.billZip}" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><c:out value="${sessionScope.theOrder.billCountry}" /></td>
        </tr>
        <tr>
            <th colspan="2">Shipping Address</th>
        </tr>
        <tr>
            <td>First name:</td>
            <td><c:out value="${sessionScope.theOrder.shipToFirstName}" /></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><c:out value="${sessionScope.theOrder.shipToLastName}" /></td>
        </tr>
        <tr>
            <td>Address 1:</td>
            <td><c:out value="${sessionScope.theOrder.shipAddress1}" /></td>
        </tr>
        <tr>
            <td>Address 2:</td>
            <td><c:out value="${sessionScope.theOrder.shipAddress2}" /></td>
        </tr>
        <tr>
            <td>City:</td>
            <td><c:out value="${sessionScope.theOrder.shipCity}" /></td>
        </tr>
        <tr>
            <td>State:</td>
            <td><c:out value="${sessionScope.theOrder.shipState}" /></td>
        </tr>
        <tr>
            <td>Zip:</td>
            <td><c:out value="${sessionScope.theOrder.shipZip}" /></td>
        </tr>
        <tr>
            <td>Country:</td>
            <td><c:out value="${sessionScope.theOrder.shipCountry}" /></td>
        </tr>
        <tr>
            <td>Courier:</td>
            <td><c:out value="${sessionScope.theOrder.courier}" /></td>
        </tr>
        <tr>
            <td colspan="2">Status: <c:out value="${sessionScope.theOrder.status}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <table>
                    <tr>
                        <th>Item ID</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Cost</th>
                    </tr>
                    <c:forEach var="lineItem" items="${sessionScope.theOrder.lineItems}">
                        <tr>
                            <td>
                                <a href="itemForm?itemId=${lineItem.item.itemId}">${lineItem.item.itemId}</a>
                            </td>
                            <td><c:if test="${lineItem.item != null}">
                                ${lineItem.item.attribute1}
                                ${lineItem.item.attribute2}
                                ${lineItem.item.attribute3}
                                ${lineItem.item.attribute4}
                                ${lineItem.item.attribute5}
                                ${lineItem.item.product.name}
                            </c:if> <c:if test="${lineItem.item == null}">
                                <i>{description unavailable}</i>
                            </c:if></td>

                            <td>${lineItem.quantity}</td>
                            <td><fmt:formatNumber value="${lineItem.unitPrice}"
                                                  pattern="$#,##0.00" /></td>
                            <td><fmt:formatNumber value="${lineItem.total}"
                                                  pattern="$#,##0.00" /></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th colspan="5">Total: <fmt:formatNumber
                                value="${sessionScope.theOrder.totalPrice}" pattern="$#,##0.00" /></th>
                    </tr>
                </table>
            </td>
        </tr>

    </table>

</div>

<%@ include file="../common/bottom.jsp"%>