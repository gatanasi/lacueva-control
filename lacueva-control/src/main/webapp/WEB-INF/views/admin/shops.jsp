<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Administraci&oacute;n de Locales</h3>
	</div>
	<div class="panel-body">
		<div>
			<table class="table table-striped table-bordered" id="shopsTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Caja</th>
						<th>Fecha</th>
						<th>Art&iacute;culos</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="5" rowspan="1" class="col-sm-1"><button id="createBtn" type="button" class="btn btn-default">Crear Local</button></td>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${shops}" var="shop">
						<tr data-id="${shop.id}">
							<td class="shopName text col-sm-2"><c:out value="${shop.shopName}" /></td>
							<td class="shopCash text col-sm-2"><c:out value="${shop.shopCash}" /></td>
							<td class="shopDate text col-sm-1"><c:out value="${shop.shopDate}" /></td>
							<td class="shopItems text col-sm-6"><c:out value="${shop.shopItems}" /></td>
							<td class="col-sm-1"><a href="#"> <span title="Modificar" class="editBtn glyphicon glyphicon-pencil col-sm-1"></span></a> <a href="#"><span title="Eliminar"
									class="delBtn glyphicon glyphicon-remove col-sm-1"></span></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<link href="<c:url value="/resources/css/bootstrap3-dialog/bootstrap-dialog.min.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap3-dialog/bootstrap-dialog.min.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap3-dialog/i18n.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/commons.js" />" defer="defer"></script>
<script type="text/javascript" src="<c:url value="/resources/js/admin/shops.js" />" defer="defer"></script>