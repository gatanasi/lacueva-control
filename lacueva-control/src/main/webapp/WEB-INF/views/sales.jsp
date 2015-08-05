<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Seleccione un local</h3>
		</div>
		<div class="panel-body">
			<ul id="shopList" class="nav nav-pills nav-justified">
				<c:forEach items="${shopList}" var="shop">
					<li data-id="${shop.id}" role="presentation"><a href="#" role="pill" data-toggle="pill">${shop.shopName}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</sec:authorize>
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
		<select id="itemNames" hidden="true">
			<c:forEach items="${currShop.shopItems}" var="item">
				<option value="${item.id}"><c:out value="${item.itemName}"></c:out></option>
			</c:forEach>
		</select> <input type="hidden" id="currShopId" value="${currShop.id}">
		<div>
			<table class="table table-striped table-bordered" id="salesTable">
				<thead>
					<tr>
						<th>Art&iacute;culo</th>
						<th>Cantidad</th>
						<th>Importe total</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="1" rowspan="1" class="text-right">Totales:</td>
						<td colspan="1" rowspan="1" class="sum" id="sumQty"></td>
						<td colspan="1" rowspan="1" class="sum" id="sumAmount"></td>
						<td colspan="1" rowspan="1" class=""></td>
					</tr>
					<tr>
						<td colspan="1" rowspan="1" class="col-sm-1"><button id="addBtn" type="button" class="btn btn-default">Agregar 10 l&iacute;neas</button></td>
						<td colspan="3" rowspan="1" class="col-sm-1"><button id="saveBtn" type="button" class="btn btn-default">Guardar</button>
							<div id="autosaveTimer" style="width: 100%;" class="stopwatch col-sm-10" data-timer="30"></div></td>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${sales}" var="sale">
						<tr data-id="${sale.id}">
							<td class="text col-sm-1"><select class="itemName" disabled="disabled">
									<c:forEach items="${currShop.shopItems}" var="item">
										<option value="${item.id}" ${sale.saleItem.id == item.id ? 'selected="selected"' : ' '}><c:out value="${item.itemName}"></c:out></option>
									</c:forEach>
							</select></td>
							<td class="saleQuantity text col-sm-5"><c:out value="${sale.saleQuantity}" /></td>
							<td class="saleAmount text col-sm-5"><c:out value="${sale.saleAmount}" /></td>
							<td class="col-sm-1"><a href="#"> <span title="Modificar" class="editBtn glyphicon glyphicon-pencil col-sm-1"></span></a> <a href="#"><span title="Eliminar"
									class="delBtn glyphicon glyphicon-remove col-sm-1"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<link href="<c:url value="/resources/css/pickadate/classic.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/pickadate/classic.date.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/timecircles/TimeCircles.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap3-dialog/bootstrap-dialog.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/pickadate/picker.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/pickadate/picker.date.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/pickadate/translations/es_ES.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/timecircles/TimeCircles.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap3-dialog/bootstrap-dialog.min.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap3-dialog/i18n.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/sales.js" />" defer="defer"></script>