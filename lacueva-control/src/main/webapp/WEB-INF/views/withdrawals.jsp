<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Ventas local ${currShop.shopName}</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="withdrawalsTable">
				<thead>
					<tr>
						<th>Fecha</th>
						<th>Tipo</th>
						<th>Cantidad</th>
						<th>Importe total</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<tr class="mountable-model">
						<td><input type="text" name="saleDate[]" class="form-control"></td>
						<td><select name="saleItem[]" class="form-control">
								<option value=""></option>
						</select></td>
						<td><input type="text" name="saleQuantity[]" class="form-control"></td>
						<td><input type="text" name="saleAmount[]" class="form-control"></td>
						<td><button id="delbtn" type="button" class="btn btn-default mountable-remove-line-2">Eliminar</button></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5"><button id="addbtn" type="button" class="btn btn-default">Agregar l&iacute;nea</button></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/mountable/mountable.js" />" defer></script>
<script type="text/javascript" src="<c:url value="/resources/js/withdrawals.js" />" defer></script>
