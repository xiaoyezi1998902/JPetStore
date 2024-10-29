<%@ include file="../common/top.jsp" %>


<div id="BackLink">

    <a href="categoryForm?categoryId=${sessionScope.category.categoryId}">
        Return to ${sessionScope.product.name}</a>
</div>

<div id="Catalog">

    <h2>${sessionScope.product.name}</h2>

    <table>
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach var="item" items="${sessionScope.itemList}">
            <tr>
                <td>
                    <a href="itemForm?itemId=${item.itemId}">${item.itemId}</a>
                </td>
                <td>${item.product.productId}</td>
                <td>${item.attribute1} ${item.attribute2} ${item.attribute3}
                        ${item.attribute4} ${item.attribute5} ${sessionScope.product.name}</td>
                <td><fmt:formatNumber value="${item.listPrice}"
                                      pattern="$#,##0.00"/></td>
                <td>
                    <form action="addItemToCart?workingItemId=${item.itemId}" method="post">
                            <%--            <a href="addItemToCart" class="Button"></a>--%>
                        <input type="submit" value="Add to Cart">
                    </form>
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






