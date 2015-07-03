<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LaCueva Control</title>
<link href="<c:url value="/resources/css/bootstrap/bootstrap.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap/bootstrap-theme.min.css" />" rel="stylesheet" type="text/css" />
</head>
<body style="padding-top: 60px">
	<nav id="myNav" class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Menu</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#home">LaCueva</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="#home">Inicio</a></li>
					<li><a href="#sales">Ventas</a></li>
					<li><a href="#inputs">Ingresos</a></li>
					<li><a href="#withdrawals">Retiros</a></li>
					<li><a href='#blankDiscs'>V&iacute;rgenes</a></li>
					<li><a href='#stocks'>Inventarios</a></li>
					<li><a href='#dashboard'>Resumen</a></li>
					<li class="dropdown"><a href="#admin" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administraci&oacute;n <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="admin/#items">Art&iacute;culos</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="admin/#shops">Locales</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="admin/#prices">Precios</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="admin/#promos">Promociones</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#logout">Cerrar sesi&oacute;n</a></li>
				</ul>
			</div>
		</div>
	</nav>