var count = $('#salesTable tbody tr').length;

$(document).ready(function() {

	$("#addBtn").on("click", addRow);
	$(".delBtn").on("click", delRow);

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
	count--;
	var r = confirm("Esta seguro que desea eliminar la fila?");
	if (r == true) {
		var tr = $(this).closest('tr');
		$(tr).remove();

		updateTotals();
	}
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
