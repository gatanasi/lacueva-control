<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LaCueva Control</title>
<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/lacueva-control.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/lacueva-control-login.css" />">
<script src="//code.jquery.com/jquery-2.1.4.min.js" defer="defer"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" defer="defer"></script>
<script src="<c:url value="/resources/js/main.js" />" defer="defer"></script>
</head>
<body style="padding-top: 60px">
	<div class="container">
		<form class="form-signin">
			<h2 class="form-signin-heading">Inicie sesi&oacute;n</h2>
			<label for="inputUser" class="sr-only">Usuario</label> <input type="text" id="inputUser" class="form-control" placeholder="Usuario" required autofocus> <label for="inputPassword"
				class="sr-only">Contrase&ntilde;a</label> <input type="password" id="inputPassword" class="form-control" placeholder="Contrase&ntilde;a" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar sesi&oacute;n</button>
		</form>
	</div>

	<%@ include file="footer.jsp"%>