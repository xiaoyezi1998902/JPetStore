<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2022/11/9
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <td>${sessionScope.searchList.description}</td>
        </tr>
        <tr>
            <td><b> ${sessionScope.searchList.itemId} </b></td>
        </tr>
        <tr>
            <td><b><font size="4"> ${sessionScope.searchList.attribute1}
                ${sessionScope.searchList.attribute2} ${sessionScope.searchList.attribute3}
                ${sessionScope.searchList.attribute4} ${sessionScope.searchList.attribute5}
                ${sessionScope.searchList.name} </font></b></td>
        </tr>
        <tr>
            <td>${sessionScope.searchList.name}</td>
        </tr>
        <tr>
            <td><c:if test="${sessionScope.searchList.quantity <= 0}">
                Back ordered.
            </c:if> <c:if test="${sessionScope.searchList.quantity > 0}">
                ${sessionScope.searchList.quantity} in stock.
            </c:if></td>
        </tr>
        <tr>
            <td><fmt:formatNumber value="${sessionScope.searchList.listPrice}"
                                  pattern="$#,##0.00" /></td>
        </tr>

        <tr>
            <td>
                <a href="addItemToCart?workingItemId=${sessionScope.searchList.itemId}" class="Button">Add to Cart</a>
            </td>
        </tr>
    </table>

</div>

<%@ include file="../common/bottom.jsp"%>
