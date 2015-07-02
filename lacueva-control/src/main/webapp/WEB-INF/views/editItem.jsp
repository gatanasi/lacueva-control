<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	<div id="formsContent">
		<h2>Editar Art&iacute;culo</h2>

		<form:form id="form" method="post" modelAttribute="item" cssClass="cleanform">
			<div class="header">
				<h2>Form</h2>
				<c:if test="${not empty message}">
					<div id="message" class="success">${message}</div>
				</c:if>
				<s:bind path="*">
					<c:if test="${status.error}">
						<div id="message" class="error">Form has errors</div>
					</c:if>
				</s:bind>
			</div>
			<fieldset>
				<legend>Personal Info</legend>
				<form:label path="itemType">
		  			Tipo <form:errors path="itemType" cssClass="error" />
				</form:label>
				<form:input path="itemType" />

				<form:label path="itemWeight">
		  			Peso <form:errors path="itemWeight" cssClass="error" />
				</form:label>
				<form:input path="itemWeight" />
			</fieldset>
			<fieldset class="radio">
				<legend>Grabable</legend>
				<label><form:radiobutton path="itemBurnable" value="true" />Si</label> <label><form:radiobutton path="itemBurnable" value="false" />No</label>
			</fieldset>

			<p>
				<button type="submit">Submit</button>
			</p>
		</form:form>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#form").submit(function() {
					$.post($(this).attr("action"), $(this).serialize(), function(html) {
						$("#formsContent").replaceWith(html);
						$('html, body').animate({
							scrollTop : $("#message").offset().top
						}, 500);
					});
					return false;
				});
			});
		</script>
	</div>
</body>
</html>