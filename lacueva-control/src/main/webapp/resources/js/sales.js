var count = $('#salesTable tbody tr').length;

$(document).ready(function() {

	$("#addBtn").on("click", addRow);
	$(".delBtn").on("click", delRow);
	$(".editBtn").on("click", editRow);

	updateTotals();

	$('.date-input').pickadate({
		max : true,
		container : '#date-picker',
		selectYears : true,
		selectMonths : true,
		format : 'dd/mm/yyyy',
		formatSubmit : 'yyyy-mm-dd',
		onSet : function(event) {
			if (event.select) {
				window.location.href = "#sales/" + this.get('select', 'yyyy-mm-dd');
			}
		}
	})
});

function addRow() {
	count++;

	var selectItemType = '<select class="itemType">' + $("#itemType").clone().show().html() + '</select>';

	var newRow = '<tr>' + '<td class="text col-sm-1">' + selectItemType + '</td>' + '<td class="text col-sm-5">' + '<input type="text" class="qty"/></td>'
			+ '<td class="text col-sm-5"><input type="text" class="amount"/></td>' + '<td><a><span title="Eliminar" class="delNewBtn glyphicon glyphicon-remove col-sm-1"></span></a></td>' + '</tr>';

	$("#salesTable > tbody").append(newRow);

	$(".qty").off("focusout");
	$(".amount").off("focusout");

	$(".qty").on("focusout", updateTotals);
	$(".amount").on("focusout", updateTotals);

	$(".delNewBtn").off("click");
	$(".delNewBtn").on("click", delNewRow);
}

function delRow() {
	var tr = $(this).closest('tr');
	BootstrapDialog.show({
		type : BootstrapDialog.TYPE_WARNING,
		title : 'Eliminar',
		message : 'Está seguro que desea eliminar esta fila?',
		buttons : [ {
			id : 'btnConfirm',
			label : 'Confirmar',
			cssClass : 'btn-danger',
			autospin : true,
			action : function(dialog) {
				dialog.enableButtons(false);
				dialog.setClosable(false);
				dialog.getModalBody().html('Eliminando...');
				var request = $.ajax({
					url : "sales/delete/",
					method : "POST",
					dataType : "text",
					timeout : 10000,
					data : {
						id : $(tr).data('id')
					}
				});
				request.done(function(msg) {
					dialog.setType(BootstrapDialog.TYPE_SUCCESS);
					dialog.getModalBody().html(msg);

					$('#btnConfirm').remove();
					$('#btnCancel').text('Cerrar');

					$(tr).remove();

					updateTotals();
				});
				request.fail(function(jqXHR, textStatus) {
					dialog.getModalBody().html('Error: ' + textStatus);
					$('#btnConfirm').text('Reintentar');
				});
				request.always(function() {
					dialog.enableButtons(true);
					dialog.setClosable(true);
				});
			}
		}, {
			id : 'btnCancel',
			label : 'Cancelar',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});

	return false;
}

function delNewRow() {
	var tr = $(this).closest('tr');
	$(tr).remove();

	updateTotals();
}

function editRow() {
	var a = $(this).closest('a');

	BootstrapDialog.show({
		title : 'Editar',
		message : 'Edición de venta',
		buttons : [ {
			id : 'btnAccept',
			icon : 'glyphicon glyphicon-send',
			label : 'Aceptar',
			cssClass : 'btn-primary',
			autospin : true,
			action : function(dialog) {
				dialog.enableButtons(false);
				dialog.setClosable(false);
				dialog.getModalBody().html('Enviando modificaciones...');
				var request = $.ajax({
					url : "sales/edit/",
					method : "POST",
					data : {
						id : '15'
					}
				});
				request.done(function(msg) {
					$("#log").html(msg);
				});
				request.fail(function(jqXHR, textStatus) {
					dialog.getModalBody().html('Error: ' + jqXHR.html());
					dialog.enableButtons(true);
					dialog.getButton('btnAccept').stopSpin();
					dialog.setClosable(true);
				});
			}
		}, {
			label : 'Cancelar',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});

	return false;
}

function sumOfColumns(table, columnIndex) {
	var tot = 0;
	table.find("tr").children("td:nth-child(" + columnIndex + ")").each(function() {
		$this = $(this);
		if (!$this.hasClass("sum") && $this.html() != "") {
			var value = parseInt($this.html());
			if (isNaN(value)) {
				value = parseInt($this.find('input').val());
				if (isNaN(value)) {
					value = 0;
				}
			}
			tot += value;
		}
	});
	return tot;
}

function updateTotals() {
	$('#sumQty').text(sumOfColumns($('#salesTable'), 2));
	$('#sumAmount').text(sumOfColumns($('#salesTable'), 3));
}
