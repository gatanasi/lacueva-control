<%@ include file="header.jsp"%>
<div id="home">
	<h2>Inicio</h2>
	<p>Bienvenido a la web de control de LaCueva</p>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

<script>
	$(document).ready(function() {
		$('#myNav').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		});
	});
</script>
<%@ include file="footer.jsp"%>