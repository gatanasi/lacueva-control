<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resources/prueba.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id='cssmenu'>
		<ul>
			<li><a href='#'><span>Home</span></a></li>
			<li class='active has-sub'><a href='#'><span>Products</span></a>
				<ul>
					<li class='has-sub'><a href='#'><span>Product 1</span></a>
						<ul>
							<li><a href='#'><span>Sub Product</span></a></li>
							<li class='last'><a href='#'><span>Sub Product</span></a></li>
						</ul></li>
					<li class='has-sub'><a href='#'><span>Product 2</span></a>
						<ul>
							<li><a href='#'><span>Sub Product</span></a></li>
							<li class='last'><a href='#'><span>Sub Product</span></a></li>
						</ul></li>
				</ul></li>
			<li><a href='#'><span>About</span></a></li>
			<li class='last'><a href='#'><span>Contact</span></a></li>
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