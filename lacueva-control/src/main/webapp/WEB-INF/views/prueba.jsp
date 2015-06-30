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
	<div id='cssmenu'>
		<ul>
			<li><a href='active'><span>Inicio</span></a></li>
			<li><a href='#'><span>Ventas</span></a></li>
			<li><a href='#'><span>Ingresos</span></a></li>
			<li><a href='#'><span>Retiros</span></a></li>
			<li><a href='#'><span>V&iacute;rgenes</span></a></li>
			<li><a href='#'><span>Inventarios</span></a></li>
			<li><a href='#'><span>Resumen</span></a></li>
			<li class='has-sub'><a href='#'><span>Administraci&oacute;n</span></a>
				<ul>
					<li class='has-sub'><a href='#'><span>Items</span></a>
						<ul>
							<li><a href='#'><span>Sub Product</span></a></li>
							<li class='last'><a href='#'><span>Sub Product</span></a></li>
						</ul></li>
					<li><a href='#'><span>Locales</span></a></li>
				</ul></li>
			<li><a href='#'><span>Cerrar sesi&oacute;n</span></a></li>
		</ul>
	</div>
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/jqueryui/jquery-ui.min.js" />"></script>
	<script type="text/javascript">
	(function($) {
	    $(document)
		    .ready(
			    function() {
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