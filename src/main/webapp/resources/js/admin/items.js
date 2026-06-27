var itemTypeNameOptions = $("#itemTypeName").clone().show().html();

$(document).ready(function() {

	$("#createBtn").on("click", function() {
		createEntity(createItemForm("", itemTypeNameOptions, "", "", ""), "admin/items/create");
		return false;
	});
	$(".delBtn").on("click", function() {
		delRow($(this).closest('tr'), "admin/items/delete");
		return false;
	});
	$(".editBtn").on("click", function() {
		var tr = $(this).closest('tr');

		var itemId = $(tr).data('id');
		var itemName = $(tr).find('.itemName').text();
		var itemTypeTable = $(tr).find('.itemType').clone().html();
		var itemWeight = $(tr).find('.itemWeight').text();
		var itemBurnable = $(tr).find('.itemBurnable').text();
		var itemPriority = $(tr).find('.itemPriority').text();

		var form = createItemForm(itemName, itemTypeTable, itemWeight, itemBurnable, itemPriority);

		editRow(tr, form, "admin/items/edit");

		return false
	});
});

function createItemForm(itemName, itemType, itemWeight, itemBurnable, itemPriority) {
	return '<div class="form-group">' + '<label for="itemName">Nombre</label>' + '<input type="text" class="form-control" id="itemName" value="' + itemName + '">' + '</div>'
			+ '<div class="form-group">' + '<label for="itemType">Tipo</label>' + '<select id="itemType">' + itemType + '</select>' + '</div>' + '<div class="form-group">'
			+ '<label for="itemWeight">Peso</label>' + '<input type="text" class="form-control" id="itemWeight" value=' + itemWeight + '>' + '</div>' + '<div class="form-group">'
			+ '<label for="itemBurnable">Grabable</label>' + '<input type="text" class="form-control" id="itemBurnable" value=' + itemBurnable + '>' + '</div>' + '<div class="form-group">'
			+ '<label for="itemPriority">Prioridad</label>' + '<input type="text" class="form-control" id="itemPriority" value=' + itemPriority + '>' + '</div>';
}

function getCreatedEntity() {
	var itemType = {};
	itemType.id = $("#itemType").val();

	var createdItem = {};
	createdItem.itemName = $("#itemName").val();
	createdItem.itemType = itemType;
	createdItem.itemWeight = $("#itemWeight").val();
	createdItem.itemBurnable = $("#itemBurnable").val();
	createdItem.itemPriority = $("#itemPriority").val();

	return createdItem;
}

function getUpdatedEntity(id) {
	var itemType = {};
	itemType.id = $("#itemType").val();

	var updatedItem = {};
	updatedItem.id = id;
	updatedItem.itemName = $("#itemName").val();
	updatedItem.itemType = itemType;
	updatedItem.itemWeight = $("#itemWeight").val();
	updatedItem.itemBurnable = $("#itemBurnable").val();
	updatedItem.itemPriority = $("#itemPriority").val();

	return updatedItem;
}

function updateEditedRow(tr, updatedEntity) {
	$(tr).find('.itemName').text(updatedEntity.itemName);
	$('option:selected', $(tr).find('.itemType')).removeAttr('selected');
	$(tr).find('.itemType option[value=' + updatedEntity.itemType.id + ']').attr("selected", true);
	$(tr).find('.itemWeight').text(updatedEntity.itemWeight);
	$(tr).find('.itemBurnable').text(updatedEntity.itemBurnable);
	$(tr).find('.itemPriority').text(updatedEntity.itemPriority);
}
