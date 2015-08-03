<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
	-->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>LaCueva Control</title>
<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<c:url value="/resources/css/lacueva-control.css" />">
<link rel="stylesheet" href="<c:url value="/resources/css/lacueva-control-login.css" />">
<script src="//code.jquery.com/jquery-2.1.4.min.js" defer="defer"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" defer="defer"></script>
</head>
<body style="padding-top: 60px">
	<div class="container">
		<form:form class="form-signin" method="POST">
			<h2 class="form-signin-heading">Inicie sesi&oacute;n</h2>
			<c:if test="${not empty param.err}">
				<div class="alert alert-danger">Usuario o contrase&ntilde;a inv&aacute;lidos.</div>
			</c:if>
			<c:if test="${not empty param.out}">
				<div class="alert alert-success">Has cerrado sesi&oacute;n exitosamente.</div>
			</c:if>
			<c:if test="${not empty param.time}">
				<div class="alert alert-warning">Su sesi&oacute;n ha expirado.</div>
			</c:if>
			<label for="username" class="sr-only">Usuario</label>
			<input type="text" id="username" name="username" class="form-control" placeholder="Usuario" required="required" autofocus="autofocus">
			<label for="password" class="sr-only">Contrase&ntilde;a</label>
			<input type="password" id="password" name="password" class="form-control" placeholder="Contrase&ntilde;a" required="required">
			<button class="btn btn-lg btn-primary btn-block" type="submit" formaction="<c:url value="/login.do" />">Iniciar sesi&oacute;n</button>
		</form:form>
	</div>

	<%@ include file="footer.jsp"%>