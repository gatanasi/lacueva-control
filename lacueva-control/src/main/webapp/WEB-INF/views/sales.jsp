<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Ventas local ${shop.shopName}</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="itemsTable">
				<tr>
					<th>Fecha</th>
					<th>Tipo</th>
					<th>Cantidad</th>
					<th>Importe total</th>
				</tr>
				<c:forEach items="${sales}" var="sale">
					<tr>
						<td class="text"><c:out value="${sale.saleDate}" /></td>
						<td class="text"><c:out value="${sale.saleItem.itemType}" /></td>
						<td class="text"><c:out value="${sale.saleQuantity}" /></td>
						<td class="text"><c:out value="${sale.saleAmount}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>