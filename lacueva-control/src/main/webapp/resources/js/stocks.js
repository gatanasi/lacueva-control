var count = $('#salesTable tbody tr').length;

$(document).ready(function() {

	$("#addBtn").bind("click", addRow);
	$(".delBtn").bind("click", delRow);

	$('div.loading').remove();
	$('#bodyDiv').show();

	updateTotals();
});

function addRow() {
	count++;
	table.row.add(
			[ '<select name="item_type' + count + '">', '<input type="text" name="sale_quantity' + count + '">', '<input type="text" name="sale_amount' + count + '">',
					'<button name="delbtn" type="button" class="btn btn-warning">Eliminar</button>' ]).draw();
	updateTotals();
}

function delRow() {
	count--;

	var r = confirm("Esta seguro que desea eliminar la fila?");
	if (r == true) {
		var tr = $(this).closest('tr');
		$(tr).remove();
	}
	updateTotals();
}

function sumOfColumns(table, columnIndex) {
	var tot = 0;
	table.find("tr").children("td:nth-child(" + columnIndex + ")").each(function() {
		$this = $(this);
		if (!$this.hasClass("sum") && $this.html() != "") {
			tot += parseInt($this.html());
		}
	});
	return tot;
}

function do_sums() {
	$("tr.sum").each(function(i, tr) {
		$tr = $(tr);
		$tr.children().each(function(i, td) {
			$td = $(td);
			var table = $td.parent().parent().parent();
			if ($td.hasClass("sum")) {
				$td.html(sumOfColumns(table, i + 1));
			}
		})
	});
}

function updateTotals() {
	$('#sumQty').text(sumOfColumns($('#salesTable'), 2));
	$('#sumAmount').text(sumOfColumns($('#salesTable'), 3));
}