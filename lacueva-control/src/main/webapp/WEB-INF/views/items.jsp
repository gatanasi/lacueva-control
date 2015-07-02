<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>LaCueva Control</title>
<link href="<c:url value="/resources/css/prueba.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet" type="text/css" />
</head>
<body>

	<h1>Administraci&oacute;n de Art&iacute;culos</h1>
	<table>
		<tr>
			<td width="100">Tipo</td>
			<td width="100">Peso</td>
			<td width="100">Grabable</td>
		</tr>
		<c:forEach items="${items}" var="item">
			<tr>
				<td><c:out value="${item.itemType}" /></td>
				<td><c:out value="${item.itemWeight}" /></td>
				<td><c:out value="${item.itemBurnable}" /></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>