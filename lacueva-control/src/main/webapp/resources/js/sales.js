var itemNameOptions = $("#itemNames").clone().show().html();
var priceList;
var promoList;

$(document).ready(function() {

	$('#shopList li[data-id=' + $("#currShopId").val() + ']').tab('show');

	$('#shopList li').on("click", changeShop);
	$("#addBtn").on("click", addRow);
	$("#saveBtn").on("click", saveRows);
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

	getPrices();
	getPromos();
});

function changeShop() {
	var request = $.ajax({
		url : "changeShop/",
		method : "POST",
		dataType : "text",
		timeout : 10000,
		data : {
			id : $(this).data('id')
		}
	});
	request.done(function(msg) {
		location.reload(true);
	});
	request.fail(function(jqXHR, textStatus, errorThrown) {
		console.log('Error: ' + errorThrown);
	});
};

function getPrices() {
	var request = $.ajax({
		url : "prices/priceList",
		method : "GET",
		data : {
			'shop' : $("#currShopId").val()
		},
		contentType : "application/json; charset=utf-8",
		dataType : "text",
		timeout : 10000
	});
	request.done(function(msg) {
		priceList = jQuery.parseJSON(msg);
	});
	request.fail(function(jqXHR, textStatus, errorThrown) {
		console.log('Error: ' + errorThrown);
	});
};

function getPromos() {
	var request = $.ajax({
		url : "promos/promoList",
		method : "GET",
		data : {
			'shop' : $("#currShopId").val()
		},
		contentType : "application/json; charset=utf-8",
		dataType : "text",
		timeout : 10000
	});
	request.done(function(msg) {
		promoList = jQuery.parseJSON(msg);
	});
	request.fail(function(jqXHR, textStatus, errorThrown) {
		console.log('Error: ' + errorThrown);
	});
};

function getPriceByItem(itemId) {
	var itemPrice = 0.00;
	$.each(priceList, function(key, value) {
		if (value.priceItem.id == itemId) {
			itemPrice = parseFloat(value.priceValue);
			return false;
		}
	});
	return itemPrice;
};

function getPromoByItemAndQty(itemId, qty) {
	var itemPromo;
	var promoQty = 0;
	var maxApplicablePromo = 0;
	$.each(promoList, function(key, value) {
		if (value.promoItem.id == itemId) {
			promoQty = parseInt(value.promoQuantity);
			if (promoQty <= qty) {
				if (maxApplicablePromo < promoQty) {
					maxApplicablePromo = promoQty;
					itemPromo = value;
				}
			}
		}
	});
	return itemPromo;
};

function calculateFinalPrice(itemId, qty) {
	var finalPrice = 0.00;
	var remainingItems = parseInt(qty);
	var itemPromo = getPromoByItemAndQty(itemId, remainingItems);
	while ((remainingItems > 1) && (itemPromo != null)) {
		finalPrice += parseFloat(itemPromo.promoValue);
		remainingItems -= parseInt(itemPromo.promoQuantity);
		itemPromo = getPromoByItemAndQty(itemId, remainingItems);
	}

	if (remainingItems > 0) {
		finalPrice += (getPriceByItem(itemId) * remainingItems);
	}

	return finalPrice;
};

function setSaleAmount() {
	var tr = $(this).closest('tr');

	var itemId = $(tr).find('.itemName').val();
	var qty = parseInt($(tr).find('.saleQuantity input').val());

	if (getPriceByItem(itemId) > 0) {
		if (!isNaN(qty)) {
			var finalPrice = calculateFinalPrice(itemId, qty);
			$(tr).find('.saleQuantity input').val(qty);
			$(tr).find('.saleAmount input').val(finalPrice);
		} else {
			$(tr).find('.saleAmount input').val('');
		}
		$(tr).find('.saleQuantity input').prop('disabled', false);
		$(tr).find('.saleAmount input').prop('disabled', true);
	} else {
		$(tr).find('.saleQuantity input').val(1);
		$(tr).find('.saleQuantity input').prop('disabled', true);
		$(tr).find('.saleAmount input').val('');
		$(tr).find('.saleAmount input').prop('disabled', false);
	}
};

function addRow() {

	for (i = 0; i < 10; i++) {
		var newRow = '<tr>' + '<td class="text col-sm-1">' + '<select class="itemName">' + itemNameOptions + '</select>' + '</td>' + '<td class="saleQuantity text col-sm-5">'
				+ '<input type="text" class="form-control"/></td>' + '<td class="saleAmount text col-sm-5"><input type="text" class="form-control" disabled/></td>'
				+ '<td class="col-sm-1"><a href="#"><span title="Eliminar" class="delNewBtn glyphicon glyphicon-remove col-sm-1"></span></a></td>' + '</tr>';

		$("#salesTable > tbody").append(newRow);
	}

	$(".saleQuantity input").off("focusout");
	$(".itemName").off("change");

	$(".saleQuantity input").on("focusout", setSaleAmount);
	$(".itemName").on("change", setSaleAmount);

	$(".delNewBtn").off("click");
	$(".delNewBtn").on("click", delNewRow);
};

function saveRows() {
	$("#salesTable").find("tr:not([data-id])").each(function() {
		var qty = parseFloat($(this).find('.saleQuantity input').val()) || 0;
		if (qty > 0) {
			saveSingleRow($(this));
		}
	});
};

function saveSingleRow(tr) {
	var item = {};
	item.id = $(tr).find('.itemName').val();

	var currShop = {};
	currShop.id = $("#currShopId").val();

	var newSale = {};
	newSale.saleDate = new Date();
	newSale.saleShop = currShop;
	newSale.saleItem = item;
	newSale.saleQuantity = $(tr).find('.saleQuantity input').val();
	newSale.saleAmount = $(tr).find('.saleAmount input').val();

	var request = $.ajax({
		url : "sales/create/",
		method : "POST",
		data : JSON.stringify(newSale),
		contentType : "application/json; charset=utf-8",
		dataType : "text",
		timeout : 10000,
	});
	request
			.done(function(msg) {
				$(tr).attr('data-id', msg);
				$(tr).find('.itemName option[value=' + item.id + ']').attr("selected", true);
				$(tr).find('.itemName').prop("disabled", true);

				var qty = newSale.saleQuantity;
				var amount = newSale.saleAmount;
				var buttons = '<span title="Modificar" class="editBtn glyphicon glyphicon-pencil col-sm-1"></span></a> <a href="#"><span title="Eliminar" class="delBtn glyphicon glyphicon-remove col-sm-1"></span></a>';

				$(tr).find('.saleQuantity input').replaceWith(qty);
				$(tr).find('.saleAmount input').replaceWith(amount);
				$(tr).find('.delNewBtn').replaceWith(buttons);

				$(".delBtn").off("click");
				$(".editBtn").off("click");

				$(".delBtn").on("click", delRow);
				$(".editBtn").on("click", editRow);

				updateTotals();
			});
	request.fail(function(jqXHR, textStatus, errorThrown) {
		console.log('Error: ' + errorThrown);
		$(tr).find('.saleQuantity').removeClass("has-error").addClass("has-error");
		$(tr).find('.saleAmount').removeClass("has-error").addClass("has-error");
	});
};

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
				request.fail(function(jqXHR, textStatus, errorThrown) {
					dialog.getModalBody().html('Error: ' + errorThrown);
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
};

function delNewRow() {
	var tr = $(this).closest('tr');
	$(tr).remove();

	updateTotals();

	return false;
};

function editRow() {
	var tr = $(this).closest('tr');

	var saleId = $(tr).data('id');
	var itemName = $(tr).find('.itemName').clone().html();
	var saleQuantity = $(tr).find('.saleQuantity').text();
	var saleAmount = $(tr).find('.saleAmount').text();

	var form = '<div class="form-group">' + '<label for="itemName">Artículo</label>' + '<select id="itemName">' + itemName + '</select>' + '</div>' + '<div class="form-group">'
			+ '<label for="saleQuantity">Cantidad</label>' + '<input type="text" class="form-control" id="saleQuantity" value=' + saleQuantity + '>' + '</div>' + '<div class="form-group">'
			+ '<label for="saleAmount">Importe</label>' + '<input type="text" class="form-control" id="saleAmount" value=' + saleAmount + '>' + '</div>';

	BootstrapDialog.show({
		title : 'Editar',
		message : form,
		buttons : [ {
			id : 'btnAccept',
			icon : 'glyphicon glyphicon-send',
			label : 'Aceptar',
			cssClass : 'btn-primary',
			autospin : true,
			action : function(dialog) {
				var item = {};
				item.id = $("#itemName").val();

				var currShop = {};
				currShop.id = $("#currShopId").val();

				var updatedSale = {};
				updatedSale.id = saleId;
				updatedSale.saleDate = new Date();
				updatedSale.saleShop = currShop;
				updatedSale.saleItem = item;
				updatedSale.saleQuantity = $("#saleQuantity").val();
				updatedSale.saleAmount = $("#saleAmount").val();

				dialog.enableButtons(false);
				dialog.setClosable(false);
				dialog.getModalBody().html('Enviando modificaciones...');
				var request = $.ajax({
					url : "sales/edit/",
					method : "POST",
					data : JSON.stringify(updatedSale),
					contentType : "application/json; charset=utf-8",
					dataType : "text",
					timeout : 10000,
				});
				request.done(function(msg) {
					dialog.setType(BootstrapDialog.TYPE_SUCCESS);
					dialog.getModalBody().html(msg);

					$('#btnAccept').remove();
					$('#btnCancel').text('Cerrar');

					$('option:selected', $(tr).find('.itemName')).removeAttr('selected');
					$(tr).find('.itemName option[value=' + item.id + ']').attr("selected", true);

					$(tr).find('.saleQuantity').text(updatedSale.saleQuantity);
					$(tr).find('.saleAmount').text(updatedSale.saleAmount);

					updateTotals();
				});
				request.fail(function(jqXHR, textStatus, errorThrown) {
					dialog.getModalBody().html('Error: ' + errorThrown);
					dialog.getButton('btnAccept').stopSpin();
					$('#btnAccept').text('Reintentar');
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
};

function sumOfColumns(table, columnIndex) {
	var tot = 0;
	table.find("tr").children("td:nth-child(" + columnIndex + ")").each(function() {
		$this = $(this);
		if (!$this.hasClass("sum") && $this.html() != "") {
			var value = parseFloat($this.html());
			if (isNaN(value)) {
				value = 0;
			}
			tot += value;
		}
	});
	return tot;
};

function updateTotals() {
	$('#sumQty').text(sumOfColumns($('#salesTable'), 2));
	$('#sumAmount').text(sumOfColumns($('#salesTable'), 3));
};
