<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2022/11/7
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/top.jsp" %>


<div id="BackLink">

    <a href="mainForm">Return to Main Menu</a>
</div>

<div id="Catalog">

<%--    <h2>${sessionScope.search.name}</h2>--%>

    <table>
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>&nbsp;</th>
        </tr>


        <c:forEach var="search" items="${sessionScope.searchList}">
            <tr>
                <td>
                    <a href="itemForm?itemId=${search.itemId}">${search.itemId}</a>
                </td>
                <td>${search.productId}</td>
                <td>${search.attribute1} ${search.name}</td>
                <td><fmt:formatNumber value="${search.listPrice}"
                                      pattern="$#,##0.00"/></td>
                <td>
                    <a href="addItemToCart?workingItemId=${search.itemId}" class="Button">Add to Cart</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
            </td>
        </tr>
    </table>

</div>

<%@ include file="../common/bottom.jsp" %>
