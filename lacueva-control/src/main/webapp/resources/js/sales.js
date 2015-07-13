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

	var newRow = '<tr>' + '<td class="text col-sm-1"><select class="itemType"></td>' + '<td class="text col-sm-5">' + '<input type="text" class="qty"/></td>'
			+ '<td class="text col-sm-5"><input type="text" class="amount"/></td>' + '<td><a><span title="Eliminar" class="delBtn glyphicon glyphicon-remove col-sm-1"></span></a></td>' + '</tr>';

	$("#salesTable > tbody").append(newRow);

	$(".qty").off("focusout");
	$(".amount").off("focusout");

	$(".qty").on("focusout", updateTotals);
	$(".amount").on("focusout", updateTotals);

	$(".delBtn").off("click");
	$(".delBtn").on("click", delRow);
}

function delRow() {
	BootstrapDialog.show({
		type : BootstrapDialog.TYPE_WARNING,
		title : 'Eliminar',
		message : 'Está seguro que desea eliminar esta fila?',
		data : {
			'href' : $(".delBtn").closest('a').data('href')
		},
		buttons : [ {
			label : 'Confirmar',
			cssClass : 'btn-danger',
			action : function(dialog) {
				window.location = dialog.getData('href');
				dialog.close();
			}
		}, {
			label : 'Cancelar',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});
}

function editRow() {
	BootstrapDialog.show({
		title : 'Editar',
		message : 'I send ajax request!',
		buttons : [ {
			icon : 'glyphicon glyphicon-send',
			label : 'Aceptar',
			cssClass : 'btn-primary',
			autospin : true,
			action : function(dialog) {
				dialog.enableButtons(false);
				dialog.setClosable(false);
				dialog.getModalBody().html('Enviando modificaciones...');
				setTimeout(function() {
					dialog.close();
				}, 5000);
			}
		}, {
			label : 'Cancelar',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});
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
