<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Administraci&oacute;n de Locales</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="shopsTable">
				<tr>
					<th>Nombre</th>
					<th>Caja</th>
					<th>Art&iacute;culos</th>
				</tr>
				<c:forEach items="${shops}" var="shop">
					<tr>
						<td class="text"><c:out value="${shop.shopName}" /></td>
						<td class="text"><c:out value="${shop.shopCash}" /></td>

						<td class="text"><c:forEach items="${shop.shopItems}" var="shopItem">
								<c:out value="${shopItem.itemName}" /> | 
					</c:forEach></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
