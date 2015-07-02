<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>LaCueva Control</title>
<link href="<c:url value="/resources/css/prueba.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li><a href='#inicio'><span>Inicio</span></a></li>
			<li><a href='#sales'><span>Ventas</span></a></li>
			<li><a href='inputs.jsp'><span>Ingresos</span></a></li>
			<li><a href='#sales'><span>Retiros</span></a></li>
			<li><a href='#blankDiscs'><span>V&iacute;rgenes</span></a></li>
			<li><a href='#stocks'><span>Inventarios</span></a></li>
			<li><a href='#dashboard'><span>Resumen</span></a></li>
			<li><a href="<c:url value="/form" />" title="forms">Forms</a></li>
			<li class='has-sub'><a href="<c:url value="/admin" />" title="admin"><span>Administraci&oacute;n</span></a>
				<ul>
					<li class='has-sub'><a href="<c:url value="/admin/items" />" title="adminItems"><span>Items</span></a>
						<ul>
							<li><a href="<c:url value="/admin/items/create" />" title="adminItemsCreate"><span>Create</span></a></li>
							<li class='last'><a href='#'><span>Sub Product</span></a></li>
						</ul></li>
					<li><a href="<c:url value="/admin/shops" />" title="adminShops"><span>Locales</span></a></li>
				</ul></li>
			<li><a href='#logout'><span>Cerrar sesi&oacute;n</span></a></li>
		</ul>

		<div id="inicio">
			<h2>Inicio</h2>
			<p>Bienvenido a la web de control de LaCueva</p>
		</div>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jqueryui/jquery-ui.min.js" />"></script>
	<script>
	MvcUtil = {};
	MvcUtil.showSuccessResponse = function(text, element) {
	    MvcUtil.showResponse("success", text, element);
	};
	MvcUtil.showErrorResponse = function showErrorResponse(text, element) {
	    MvcUtil.showResponse("error", text, element);
	};
	MvcUtil.showResponse = function(type, text, element) {
	    var responseElementId = element.attr("id") + "Response";
	    var responseElement = $("#" + responseElementId);
	    if (responseElement.length == 0) {
		responseElement = $(
			'<span id="' + responseElementId + '" class="' + type + '" style="display:none">'
				+ text + '</span>').insertAfter(element);
	    } else {
		responseElement
			.replaceWith('<span id="' + responseElementId + '" class="' + type + '" style="display:none">'
				+ text + '</span>');
		responseElement = $("#" + responseElementId);
	    }
	    responseElement.fadeIn("slow");
	};
	MvcUtil.xmlencode = function(xml) {
	    //for IE 
	    var text;
	    if (window.ActiveXObject) {
		text = xml.xml;
	    }
	    // for Mozilla, Firefox, Opera, etc.
	    else {
		text = (new XMLSerializer()).serializeToString(xml);
	    }
	    return text.replace(/\&/g, '&' + 'amp;').replace(/</g, '&' + 'lt;')
		    .replace(/>/g, '&' + 'gt;').replace(/\'/g, '&' + 'apos;')
		    .replace(/\"/g, '&' + 'quot;');
	};
    </script>
	<script type="text/javascript">
	(function($) {
	    $(document)
		    .ready(
			    function() {

				$("#cssmenu").tabs();

				$("#cssmenu").bind("tabsselect",
					function(event, ui) {
					    window.location.hash = ui.tab.hash;
					});

				$('#cssmenu')
					.prepend(
						'<div id="indicatorContainer"><div id="pIndicator"><div id="cIndicator"></div></div></div>');
				var activeElement = $('#cssmenu>ul>li:first');

				$('#cssmenu>ul>li').each(function() {
				    if ($(this).hasClass('active')) {
					activeElement = $(this);
				    }
				});

				var posLeft = activeElement.position().left;
				var elementWidth = activeElement.width();
				posLeft = posLeft + elementWidth / 2 - 6;
				if (activeElement.hasClass('has-sub')) {
				    posLeft -= 6;
				}

				$('#cssmenu #pIndicator').css('left', posLeft);
				var element, leftPos, indicator = $('#cssmenu pIndicator');

				$("#cssmenu>ul>li")
					.hover(
						function() {
						    element = $(this);
						    var w = element.width();
						    if ($(this).hasClass(
							    'has-sub')) {
							leftPos = element
								.position().left
								+ w / 2 - 12;
						    } else {
							leftPos = element
								.position().left
								+ w / 2 - 6;
						    }
						    $('#cssmenu #pIndicator')
							    .css('left',
								    leftPos);
						},
						function() {
						    $('#cssmenu #pIndicator')
							    .css('left',
								    posLeft);
						});

				$('#cssmenu>ul')
					.prepend(
						'<li id="menu-button"><a>Menu</a></li>');
				$("#menu-button").click(function() {
				    if ($(this).parent().hasClass('open')) {
					$(this).parent().removeClass('open');
				    } else {
					$(this).parent().addClass('open');
				    }
				});
			    });
	})(jQuery);
    </script>
</body>
</html>