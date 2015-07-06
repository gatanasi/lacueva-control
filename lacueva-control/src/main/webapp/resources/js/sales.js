var count = $('#salesTable tbody tr').length;

$(document).ready(
		function() {

			var table = $('#salesTable').DataTable({
				"bLengthChange" : false,
				'bFilter' : false,
				"bSort" : false,
				'bInfo' : true,
				'bPaginate' : false,
			});

			$('#salesTable tbody').on('click', 'tr', function() {
				// ----- FOR SINGLE SELECTION --------
				// if ($(this).hasClass('selected')) {
				// $(this).removeClass('selected');
				// } else {
				// table.$('tr.selected').removeClass('selected');
				// $(this).addClass('selected');
				// }

				$(this).toggleClass('selected');
			});

			$('#addbtn').on(
					'click',
					function() {
						count++;
						table.row.add(
								[ '<input type="text" name="sale_date_' + count + '">', '<input type="text" name="item_type_' + count + '">', '<input type="text" name="sale_quantity_' + count + '">',
										'<input type="text" name="sale_amount_' + count + '">' ]).draw();
					});

			$('#delbtn').on('click', function() {
				count--;
				table.row('.selected').remove().draw(false);
			});
		});
