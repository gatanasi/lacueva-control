<%@ include file="header.jsp"%>
<h1>Ventas local ${shop.shopName}</h1>
<table>
	<tr>
		<td>Id</td>
		<td>saleDate</td>
		<td>itemType</td>
		<td>saleQuantity</td>
		<td>saleAmount</td>
	</tr>
	<c:forEach items="${sales}" var="sale">
		<tr>
			<td><c:out value="${sale.id}" /></td>
			<td><c:out value="${sale.saleDate}" /></td>
			<td><c:out value="${sale.saleItem.itemType}" /></td>
			<td><c:out value="${sale.saleQuantity}" /></td>
			<td><c:out value="${sale.saleAmount}" /></td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<%@ include file="footer.jsp"%>