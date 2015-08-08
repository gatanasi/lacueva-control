var itemTypeNameOptions = $("#itemTypeName").clone().show().html();

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

	var itemId = $(tr).data('id');
	var itemName = $(tr).find('.itemName').text();
	var itemTypeTable = $(tr).find('.itemType').clone().html();
	var itemWeight = $(tr).find('.itemWeight').text();
	var itemBurnable = $(tr).find('.itemBurnable').text();
	var itemPriority = $(tr).find('.itemPriority').text();

	var form = '<div class="form-group">' + '<label for="itemName">Nombre</label>' + '<input type="text" class="form-control" id="itemName" value="' + itemName + '">' + '</div>'
			+ '<div class="form-group">' + '<label for="itemType">Tipo</label>' + '<select id="itemType">' + itemTypeTable + '</select>' + '</div>' + '<div class="form-group">'
			+ '<label for="itemWeight">Peso</label>' + '<input type="text" class="form-control" id="itemWeight" value=' + itemWeight + '>' + '</div>' + '<div class="form-group">'
			+ '<label for="itemBurnable">Grabable</label>' + '<input type="text" class="form-control" id="itemBurnable" value=' + itemBurnable + '>' + '</div>' + '<div class="form-group">'
			+ '<label for="itemPriority">Prioridad</label>' + '<input type="text" class="form-control" id="itemPriority" value=' + itemPriority + '>' + '</div>';

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
				var itemType = {};
				itemType.id = $("#itemType").val();

				var updatedItem = {};
				updatedItem.id = itemId;
				updatedItem.itemName = $("#itemName").val();
				updatedItem.itemType = itemType;
				updatedItem.itemWeight = $("#itemWeight").val();
				updatedItem.itemBurnable = $("#itemBurnable").val();
				updatedItem.itemPriority = $("#itemPriority").val();

				dialog.enableButtons(false);
				dialog.setClosable(false);
				dialog.getModalBody().html('Enviando modificaciones...');
				var request = $.ajax({
					url : "admin/items/edit",
					method : "POST",
					data : JSON.stringify(updatedItem),
					contentType : "application/json; charset=utf-8",
					dataType : "text",
					timeout : generalTimeout
				});
				request.done(function(msg) {
					dialog.setType(BootstrapDialog.TYPE_SUCCESS);
					dialog.getModalBody().html(msg);

					$('#btnAccept').remove();
					$('#btnCancel').text('Cerrar');

					$(tr).find('.itemName').text(updatedItem.itemName);
					$('option:selected', $(tr).find('.itemType')).removeAttr('selected');
					$(tr).find('.itemType option[value=' + itemType.id + ']').attr("selected", true);
					$(tr).find('.itemWeight').text(updatedItem.itemWeight);
					$(tr).find('.itemBurnable').text(updatedItem.itemBurnable);
					$(tr).find('.itemPriority').text(updatedItem.itemPriority);
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
