<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Administraci&oacute;n de Tipos de Art&iacute;culo</h3>
	</div>
	<div class="panel-body">
		<div>
			<table class="table table-striped table-bordered" id="itemTypesTable">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<td colspan="2" rowspan="1" class="col-sm-1"><button id="createBtn" type="button" class="btn btn-default">Crear Tipo de Art&iacute;culo</button></td>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${itemTypes}" var="itemType">
						<tr data-id="${itemType.id}">
							<td class="itemTypeName text col-sm-3"><c:out value="${itemType.itemTypeName}" /></td>
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
<script type="text/javascript" src="<c:url value="/resources/js/admin/itemTypes.js" />" defer="defer"></script>