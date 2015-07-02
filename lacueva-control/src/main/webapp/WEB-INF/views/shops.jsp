<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>LaCueva Control</title>
<link href="<c:url value="/resources/css/bootstrap/bootstrap.css" />" rel="stylesheet" type="text/css" />
</head>
<body>

	<h1>Administraci&oacute;n de Locales</h1>
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
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
</body>
</html>