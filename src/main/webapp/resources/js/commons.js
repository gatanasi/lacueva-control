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

function delRow(tr, deleteUrl) {
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
					url : deleteUrl,
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

function createEntity(form, createUrl) {
	BootstrapDialog.show({
		title : 'Crear',
		message : form,
		buttons : [ {
			id : 'btnAccept',
			icon : 'glyphicon glyphicon-send',
			label : 'Aceptar',
			cssClass : 'btn-primary',
			autospin : true,
			action : function(dialog) {
				var createdEntity = getCreatedEntity();

				dialog.enableButtons(false);
				dialog.setClosable(false);
				dialog.getModalBody().html('Enviando solicitud...');
				var request = $.ajax({
					url : createUrl,
					method : "POST",
					data : JSON.stringify(createdEntity),
					contentType : "application/json; charset=utf-8",
					dataType : "text",
					timeout : generalTimeout
				});
				request.done(function(msg) {
					dialog.setType(BootstrapDialog.TYPE_SUCCESS);
					dialog.getModalBody().html(msg);

					$('#btnAccept').remove();
					$('#btnCancel').text('Cerrar');

					location.reload(true);
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

function editRow(tr, form, editUrl) {
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
				var updatedEntity = getUpdatedEntity($(tr).data('id'));

				dialog.enableButtons(false);
				dialog.setClosable(false);
				dialog.getModalBody().html('Enviando modificaciones...');
				var request = $.ajax({
					url : editUrl,
					method : "POST",
					data : JSON.stringify(updatedEntity),
					contentType : "application/json; charset=utf-8",
					dataType : "text",
					timeout : generalTimeout
				});
				request.done(function(msg) {
					dialog.setType(BootstrapDialog.TYPE_SUCCESS);
					dialog.getModalBody().html(msg);

					$('#btnAccept').remove();
					$('#btnCancel').text('Cerrar');

					updateEditedRow(tr, updatedEntity);
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
