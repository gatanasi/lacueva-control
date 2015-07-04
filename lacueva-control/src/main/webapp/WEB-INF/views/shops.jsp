<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Administraci&oacute;n de Locales</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-condensed">
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
								<c:out value="${shopItem.itemType}" /> | 
					</c:forEach></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>