<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>LaCueva Control</title>
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap/bootstrap-theme.min.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="formsContent">
		<h2>Editar Art&iacute;culo</h2>
		<form:form cssClass="form-horizontal" id="form" method="post" modelAttribute="item">
			<div class="header">
				<c:if test="${not empty message}">
					<div id="message" class="alert alert-success">${message}</div>
				</c:if>
				<s:bind path="*">
					<c:if test="${status.error}">
						<div id="message" class="alert alert-danger">El formulario contiene errores</div>
					</c:if>
				</s:bind>
			</div>
			<fieldset class="form-group">
				<hr>
				<form:label cssClass="col-sm-2 control-label" path="itemType">
		  			Tipo <form:errors path="itemType" cssClass="label label-danger" />
				</form:label>
				<div class="col-sm-10">
					<form:input cssClass="form-control" path="itemType" />
				</div>
				<form:label cssClass="col-sm-2 control-label" path="itemWeight">
		  			Peso <form:errors path="itemWeight" cssClass="label label-danger" />
				</form:label>
				<div class="col-sm-10">
					<form:input cssClass="form-control" path="itemWeight" />
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label> <form:checkbox path="itemBurnable" value="true" />Grabable
							</label>
						</div>
					</div>
				</div>
			</fieldset>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Aceptar</button>
				</div>
			</div>
		</form:form>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.min.js" />"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
	<script type="text/javascript">
	$(document).ready(
		function() {
		    $("#form").submit(
			    function() {
				$.post($(this).attr("action"), $(this)
					.serialize(), function(html) {
				    $("#formsContent").replaceWith(html);
				    $('html, body').animate({
					scrollTop : $("#message").offset().top
				    }, 500);
				});
				return false;
			    });
		});
    </script>
</body>
</html>