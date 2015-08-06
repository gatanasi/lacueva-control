$(document).ready(function() {

	$("#createBtn").on("click", createItem);
	$(".delBtn").on("click", delRow);
	$(".editBtn").on("click", editRow);

});

function getCurrentDate() {
	var today = new Date();

	var month = today.getMonth() + 1;
	var day = today.getDate();

	var todayString = today.getFullYear() + '-' + (('' + month).length < 2 ? '0' : '') + month + '-' + (('' + day).length < 2 ? '0' : '') + day;

	return todayString;
};

function displayErrorMsg() {
	BootstrapDialog.show({
		type : BootstrapDialog.TYPE_ERROR,
		title : 'Error',
		message : 'Ha ocurrido un error, por favor vuelva a cargar la página',
		buttons : [ {
			id : 'btnClose',
			label : 'Cerrar',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});
};

function createItem() {

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
					url : "admin/items/delete",
					method : "POST",
					dataType : "text",
					timeout : generalTimeout,
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
					url : "sales/edit",
					method : "POST",
					data : JSON.stringify(updatedSale),
					contentType : "application/json; charset=utf-8",
					dataType : "text",
					timeout : generalTimeout
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
