<%@ include file="header.jsp"%>
<div id="shops">
	<h1>Administraci&oacute;n de Locales</h1>
</div>
<div class="table-responsive">
	<table class="table table-condensed">
		<tr>
			<td width="100">Nombre</td>
			<td width="100">Caja</td>
			<td width="100">Art&iacute;culos</td>
		</tr>
		<c:forEach items="${shops}" var="shop">
			<tr>
				<td><c:out value="${shop.shopName}" /></td>
				<td><c:out value="${shop.shopCash}" /></td>

				<td><c:forEach items="${shop.shopItems}" var="shopItem">
						<c:out value="${shopItem.itemType}" /> | 
					</c:forEach></td>
			</tr>
		</c:forEach>
	</table>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<%@ include file="footer.jsp"%>