<%@ include file="../common/top.jsp"%>

<div id="BackLink">
    <a href="productForm?productId=${sessionScope.product.productId}">Return to ${sessionScope.product.productId}</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <td>${sessionScope.product.description}</td>
        </tr>
        <tr>
            <td><b> ${requestScope.item.itemId} </b></td>
        </tr>
        <tr>
            <td><b><font size="4"> ${requestScope.item.attribute1}
                ${requestScope.item.attribute2} ${requestScope.item.attribute3}
                ${requestScope.item.attribute4} ${requestScope.item.attribute5}
                ${requestScope.product.name} </font></b></td>
        </tr>
        <tr>
            <td>${sessionScope.product.name}</td>
        </tr>
        <tr>
            <td><c:if test="${requestScope.item.quantity <= 0}">
                Back ordered.
            </c:if> <c:if test="${requestScope.item.quantity > 0}">
                ${requestScope.item.quantity} in stock.
            </c:if></td>
        </tr>
        <tr>
            <td><fmt:formatNumber value="${requestScope.item.listPrice}"
                                  pattern="$#,##0.00" /></td>
        </tr>

        <tr>
            <td>
            <a href="addItemToCart?workingItemId=${requestScope.item.itemId}" class="Button">Add to Cart</a>
            </td>
        </tr>
    </table>

</div>

<%@ include file="../common/bottom.jsp"%>



