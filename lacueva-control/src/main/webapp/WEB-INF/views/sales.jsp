<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LaCueva Control</title>
<link href="<c:url value="/resources/prueba.css" />" rel="stylesheet" type="text/css" />
</head>
<body>

	<h1>Ventas local ${shop.shopName}</h1>
	<table>
		<tr>
			<td width="0">Id</td>
			<td width="100">saleDate</td>
			<td width="100">itemType</td>
			<td width="100">saleQuantity</td>
			<td width="100">saleAmount</td>
		</tr>
		<c:forEach items="${sales}" var="sale">
			<tr>
				<td><c:out value="${sale.id}" /></td>
				<td><c:out value="${sale.saleDate}" /></td>
				<td><c:out value="${sale.saleItem.itemType}" /></td>
				<td><c:out value="${sale.saleQuantity}" /></td>
				<td><c:out value="${sale.saleAmount}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>