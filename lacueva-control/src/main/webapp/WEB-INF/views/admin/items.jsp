<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Administraci&oacute;n de Art&iacute;culos</h3>
	</div>
	<div class="panel-body">
		<select id="itemTypeName" hidden="true">
			<c:forEach items="${itemTypes}" var="itemType">
				<option value="${itemType.id}"><c:out value="${itemType.itemTypeName}"></c:out></option>
			</c:forEach>
		</select>
		<div>
			<table class="table table-striped table-bordered" id="itemsTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Tipo</th>
						<th>Peso</th>
						<th>Grabable</th>
						<th>Prioridad</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="6" rowspan="1" class="col-sm-1"><button id="createBtn" type="button" class="btn btn-default">Crear Art&iacute;culo</button></td>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${items}" var="item">
						<tr data-id="${item.id}">
							<td class="itemName text col-sm-3"><c:out value="${item.itemName}" /></td>
							<td class="text col-sm-3"><select class="itemType" disabled="disabled">
									<c:forEach items="${itemTypes}" var="itemType">
										<option value="${itemType.id}" ${item.itemType.id == itemType.id ? 'selected="selected"' : ' '}><c:out value="${itemType.itemTypeName}"></c:out></option>
									</c:forEach>
							</select></td>
							<td class="itemWeight text col-sm-3"><c:out value="${item.itemWeight}" /></td>
							<td class="itemBurnable text col-sm-1"><c:out value="${item.itemBurnable}" /></td>
							<td class="itemPriority text col-sm-1"><c:out value="${item.itemPriority}" /></td>
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
<script type="text/javascript" src="<c:url value="/resources/js/admin/items.js" />" defer="defer"></script>