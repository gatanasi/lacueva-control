var testData = [ {
	"id" : 1,
	"saleDate" : 1436294809891,
	"saleShop" : {
		"id" : 1,
		"shopDate" : "2015-07-07",
		"shopName" : "Shop1",
		"shopCash" : 2000,
		"shopItems" : [ {
			"id" : 1,
			"itemType" : "DVD",
			"itemWeight" : 16.4,
			"itemBurnable" : true
		}, {
			"id" : 3,
			"itemType" : "BD",
			"itemWeight" : 16.4,
			"itemBurnable" : true
		}, {
			"id" : 11,
			"itemType" : "Insumos",
			"itemWeight" : null,
			"itemBurnable" : false
		} ]
	},
	"saleItem" : {
		"id" : 1,
		"itemType" : "DVD",
		"itemWeight" : 16.4,
		"itemBurnable" : true
	},
	"saleQuantity" : 15,
	"saleAmount" : 180.0
}, {
	"id" : 2,
	"saleDate" : 1436294810418,
	"saleShop" : {
		"id" : 1,
		"shopDate" : "2015-07-07",
		"shopName" : "Shop1",
		"shopCash" : 2000,
		"shopItems" : [ {
			"id" : 1,
			"itemType" : "DVD",
			"itemWeight" : 16.4,
			"itemBurnable" : true
		}, {
			"id" : 3,
			"itemType" : "BD",
			"itemWeight" : 16.4,
			"itemBurnable" : true
		}, {
			"id" : 11,
			"itemType" : "Insumos",
			"itemWeight" : null,
			"itemBurnable" : false
		} ]
	},
	"saleItem" : {
		"id" : 3,
		"itemType" : "BD",
		"itemWeight" : 16.4,
		"itemBurnable" : true
	},
	"saleQuantity" : 3,
	"saleAmount" : 120.0
} ];

var count = $('#salesTable tbody tr').length;

$(document).ready(
		function() {

			var table = $('#salesTable').DataTable({
				"bLengthChange" : false,
				"bPaginate" : false,
				"bProcessing" : true,
				"bServerSide" : false,
				"bSort" : true,
				"bInfo" : true,
				"columns" : [ null, null, null, null, {
					"data" : null,
					"defaultContent" : testData
				} ],
				"language" : {
					"url" : "resources/i18n/Spanish.lang"
				},
				"initComplete" : function() {
					/*
					 * var api = this.api();
					 * 
					 * 
					 * $(api.column(2).footer()).html(
					 * api.column(2).data().reduce( function(a, b) { return a +
					 * b; }));
					 * 
					 * $(api.column(3).footer()).html(
					 * api.column(3).data().reduce( function(a, b) { return a +
					 * b; }));
					 */
				},
				"footerCallback" : function(row, data, start, end, display) {
					var api = this.api(), data;

					// Remove the formatting to get
					// integer data for summation
					var intVal = function(i) {
						return typeof i === 'string' ? i.replace(/[\$,]/g, '') * 1 : typeof i === 'number' ? i : 0;
					};

					// Total over this page
					pageTotal = api.column(3, {
						page : 'current'
					}).data().reduce(function(a, b) {
						return intVal(a) + intVal(b);
					}, 0);

					// Update footer
					$(api.column(3).footer()).html('$' + pageTotal);
				}
			});

			$('#addbtn').on(
					'click',
					function() {
						count++;
						table.row.add(
								[ '<select name="item_type' + count + '">', '<input type="text" name="sale_quantity' + count + '">', '<input type="text" name="sale_amount' + count + '">',
										'<button name="delbtn" type="button" class="btn btn-warning">Eliminar</button>' ]).draw();

					});

			$('#salesTable').on('click', 'button', function() {
				count--;
				var tr = $(this).closest('tr');
				var row = table.row(tr);
				row.remove().draw(false);
			});
		});
