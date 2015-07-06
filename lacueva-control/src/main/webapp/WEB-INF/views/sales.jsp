<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Ventas local ${currShop.shopName}</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="salesTable">
				<thead>
					<tr>
						<th>Fecha</th>
						<th>Tipo</th>
						<th>Cantidad</th>
						<th>Importe total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sales}" var="sale">
						<tr>
							<td class="text"><c:out value="${sale.saleDate}" /></td>
							<td class="text"><input type="text" name="item_type_" value="<c:out value="${sale.saleItem.itemType}" />"></td>
							<td class="text"><input type="text" name="sale_quantity_${sale.id}" value="<c:out value="${sale.saleQuantity}" />"></td>
							<td class="text"><input type="text" name="sale_amount_" value="<c:out value="${sale.saleAmount}" />"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input type="submit">
		</div>
	</div>
</div>
<div id="controlButtons">
	<button value="add" id="addbtn">Add row</button>
	<button value="delete" id="delbtn">Delete row</button>
</div>

<link href="<c:url value="/resources/css/datatables/jquery.dataTables.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/datatables/jquery.dataTables.min.js" />" defer></script>
<script type="text/javascript" src="<c:url value="/resources/js/sales.js" />" defer></script>
