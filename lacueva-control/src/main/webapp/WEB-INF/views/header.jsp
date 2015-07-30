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
<script src="//code.jquery.com/jquery-2.1.4.min.js" defer="defer"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" defer="defer"></script>
<script src="<c:url value="/resources/js/spin/spin.min.js" />" defer="defer"></script>
<script src="<c:url value="/resources/js/spin/jquery.spin.js" />" defer="defer"></script>
<script src="<c:url value="/resources/js/main.js" />" defer="defer"></script>
</head>
<body style="padding-top: 60px">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/" />">LaCueva</a>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav" id="navmenu">
					<li id="navsales"><a href="#sales">Ventas</a></li>
					<li id="navinputs"><a href="#inputs">Ingresos</a></li>
					<li id="navwithdrawals"><a href="#withdrawals">Retiros</a></li>
					<li id="navblankdiscs"><a href="<c:url value="/initSession" />">V&iacute;rgenes</a></li>
					<li id="navstocks"><a href='#stocks'>Inventarios</a></li>
					<li id="navdashboard"><a href='#dashboard'>Resumen</a></li>
					<li id="navadmin" class="dropdown"><a href="#navadmin" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administraci&oacute;n <span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li id="navitems"><a href="#items">Art&iacute;culos</a></li>
							<li role="separator" class="divider"></li>
							<li id="navshops"><a href="#shops">Locales</a></li>
							<li role="separator" class="divider"></li>
							<li id="navprices"><a href="#prices">Precios</a></li>
							<li role="separator" class="divider"></li>
							<li id="navpromos"><a href="#promos">Promociones</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li id="navlogout"><a href="<c:url value="/logout" />" title="logout">Cerrar sesi&oacute;n</a></li>
				</ul>
			</div>
		</div>
	</nav>