<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Ingresos</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="inputsTable">
				<tr>
					<th>Fecha</th>
					<th>Art&iacute;culo</th>
					<th>Cantidad</th>
					<th>Proveedor</th>
					<th>Local Destino</th>
				</tr>
				<c:forEach items="${inputs}" var="input">
					<tr>
						<td class="text"><c:out value="${input.inputDate}" /></td>
						<td class="text"><c:out value="${input.inputItem.itemType}" /></td>
						<td class="text"><c:out value="${input.inputQuantity}" /></td>
						<td class="text"><c:out value="${input.inputProvider}" /></td>
						<td class="text"><c:out value="${input.inputShop.shopName}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
