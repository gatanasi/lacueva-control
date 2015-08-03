<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Ventas local ${currShop.shopName}</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="salesTable">
				<thead>
					<tr>
						<th>Tipo</th>
						<th>Cantidad</th>
						<th>Importe total</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sales}" var="sale">
						<tr>
							<td><select name="item_type">
									<option value="<c:out value="${sale.saleItem.itemType}" />"></option>
							</select></td>
							<td><input type="text" name="sale_quantity" value="<c:out value="${sale.saleQuantity}" />"></td>
							<td><input type="text" name="sale_amount" value="<c:out value="${sale.saleAmount}" />"></td>
							<td><button name="delbtn" type="button" class="btn btn-warning">Eliminar</button></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="2" class="text-right" rowspan="1">Total:</th>
						<th rowspan="1" colspan="2"></th>
					</tr>
					<tr>
						<th colspan="4" rowspan="1"><button id="addbtn" type="button" class="btn btn-default">Agregar l&iacute;nea</button></th>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

<link href="<c:url value="/resources/css/datatables/dataTables.bootstrap.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/datatables/jquery.dataTables.js" />" defer></script>
<script type="text/javascript" src="<c:url value="/resources/js/datatables/dataTables.bootstrap.js" />" defer></script>
<script type="text/javascript" src="<c:url value="/resources/js/sales.js" />" defer></script>
