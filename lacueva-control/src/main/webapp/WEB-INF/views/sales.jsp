<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Ventas en ${currShop.shopName}</h3>
		<c:if test="${not empty message}">
			<div id="message" class="alert alert-success">${message}</div>
		</c:if>
	</div>
	<div class="form-group">
		<div class="input-group">
			<label for="datePicker" class="text col-sm-1">Fecha: </label> <input type="date" class="date-input form-control col-sm-2" id="datePicker"
				data-value="<fmt:formatDate pattern="yyyy-MM-dd" 
            value="${searchDate}" />" />
		</div>
		<div id="date-picker" class="col-sm-2"></div>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="salesTable">
				<thead>
					<tr>
						<th>Art&iacute;culo</th>
						<th>Cantidad</th>
						<th>Importe total</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sales}" var="sale">
						<tr data-id="${sale.id}">
							<td class="text col-sm-1"><select id="itemType" name="itemType" disabled>
									<c:forEach items="${sales}" var="val">
										<option value="${val.saleItem.itemType}" ${sale.saleItem.itemType == val.saleItem.itemType ? 'selected' : ' '}><c:out value="${val.saleItem.itemType}"></c:out></option>
									</c:forEach>
							</select></td>
							<td class="text col-sm-5"><c:out value="${sale.saleQuantity}" /></td>
							<td class="text col-sm-5"><c:out value="${sale.saleAmount}" /></td>
							<td><a href="#sales/edit/<fmt:formatDate pattern="yyyy-MM-dd" value="${searchDate}" />/${sale.id}"> <span title="Modificar" class="editBtn glyphicon glyphicon-pencil col-sm-1"></span></a>
								<a href="sales/delete/<fmt:formatDate pattern="yyyy-MM-dd" value="${searchDate}" />/${sale.id}"><span title="Eliminar" class="delBtn glyphicon glyphicon-remove col-sm-1"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="1" class="text-right sum" rowspan="1">Totales:</td>
						<td colspan="1" id="sumQty" class="sum" rowspan="1"></td>
						<td colspan="1" id="sumAmount" class="sum" rowspan="1"></td>
						<td colspan="1" rowspan="1" class="sum"></td>
					</tr>
					<tr>
						<td colspan="4" rowspan="1"><button id="addBtn" type="button" class="btn btn-default">Agregar l&iacute;nea</button></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
<link href="<c:url value="/resources/css/pickadate/classic.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/pickadate/classic.date.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/pickadate/picker.js" />" defer></script>
<script type="text/javascript" src="<c:url value="/resources/js/pickadate/picker.date.js" />" defer></script>
<script type="text/javascript" src="<c:url value="/resources/js/pickadate/translations/es_ES.js" />" defer></script>
<script type="text/javascript" src="<c:url value="/resources/js/sales.js" />" defer></script>
