<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Administraci&oacute;n de Art&iacute;culos</h3>
	</div>
	<div class="panel-body">
		<div id="table">
			<table class="table table-striped table-bordered" id="itemsTable">
				<tr>
					<th>Nombre</th>
					<th>Tipo</th>
					<th>Peso</th>
					<th>Grabable</th>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
				</tr>
				<c:forEach items="${items}" var="item">
					<tr>
						<td class="text">${item.itemName}</td>
						<td class="text">${item.itemType.itemTypeName}</td>
						<td class="text">${item.itemWeight}</td>
						<td class="text">${item.itemBurnable}</td>
						<td><a href="#" class="edit"> <span title="Modificar Art&iacute;culo" class="glyphicon glyphicon-pencil" id="edit"></span></a></td>
						<td><a href="#" class="delete"><span title="Eliminar Art&iacute;culo" class="glyphicon glyphicon-remove" id="delete"></span></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<input type="hidden" id="urlEditItem" value="<c:url value='/admin/items/edit/'/>" />
<input type="hidden" id="urlDeleteItem" value="<c:url value='/admin/items/delete/'/>" />
<input type="hidden" id="urlListItems" value="<c:url value='/admin/items/list/'/>" />
<script type="text/javascript">
	$(document).ready(function() {

		$(".modificar").bind("click", Modificar);
		$(".eliminar").bind("click", Eliminar);

		function Modificar() {
			var tr = $(this).parent().parent();
			var id = tr.children("td:nth-child(1)").text();
			var texto = tr.children("td:nth-child(2)").text();

			$.ajax({
				url : $("#urlEditItem").val() + id,
				dataType : "html",
				success : function(response) {
					$("#camposNotificacionModificada").remove();
					$("#alertOk").hide();
					$("#aviso").empty();
					$("#camposNotificacion").html(response);
					$("#mensaje").val(texto);
				},
				error : function(error) {
					alert(error.responseText);

				}
			});
		}
		;

		function Eliminar() {
			var tr = $(this).parent().parent();
			var id = tr.children("td:nth-child(1)").text();
			var txt = tr.children("td:nth-child(2)").text();
			var apli = $("#aplicaciones").val();
			var r = confirm("Esta seguro que desea eliminar esta notificacion? Notificacion:  " + txt);
			if (r == true) {

				$.ajax({
					url : $("#urlDeleteItem").val() + id,
					success : function(data) {
						if (data == false) {
							alert("Ocurrio un error, Por favor intente nuevamente");
						}

						$.ajax({
							type : "GET",
							url : $("#urlListItems").val() + apli,
							dataType : "html",
							success : function(response) {
								$("#tabla").html(response);
								$("#camposNotificacion").empty();
								$("#alertOk").hide();
								$("#alertOk").show();
								$("#aviso").empty();
								$("#aviso").append(" <span class='glyphicon glyphicon-ok' >");
								$("#aviso").append("  Notificacion eliminada con exito");
							},
							error : function(error) {
								alert(error.responseText);
							}
						});

					},
					error : function(error) {
						alert(error.responseText);

					}
				});
			}
			;
		}
	});
</script>