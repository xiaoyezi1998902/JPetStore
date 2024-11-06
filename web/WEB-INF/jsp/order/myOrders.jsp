<%@ include file="../common/top.jsp"%>

<div id="Content">

    <h2>My Orders</h2>

    <table>
        <tr>
            <th>Order ID</th>
            <th>Date</th>
            <th>Total Price</th>
        </tr>

        <c:forEach var="order" items="${sessionScope.orderList}">
            <tr>
                <td><a href="getTheOrder?orderId=${order.orderId}">${order.orderId}</a> </td>
                <td>${order.orderDate}</td>
                <td>${order.totalPrice}</td>
            </tr>
        </c:forEach>

    </table>

</div>

<%@ include file="../common/bottom.jsp"%>
