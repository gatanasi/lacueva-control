$(document).ready(function() {

	$("#createBtn").on("click", function() {
		createEntity(createItemTypeForm(""), "admin/itemTypes/create");
		return false;
	});
	$(".delBtn").on("click", function() {
		delRow($(this).closest('tr'), "admin/itemTypes/delete");
		return false;
	});
	$(".editBtn").on("click", function() {
		var tr = $(this).closest('tr');

		var itemId = $(tr).data('id');
		var itemTypeName = $(tr).find('.itemTypeName').text();

		var form = createItemTypeForm(itemTypeName);

		editRow(tr, form, "admin/itemTypes/edit");

		return false
	});
});

function createItemTypeForm(itemTypeName) {
	return '<div class="form-group">' + '<label for="itemTypeName">Nombre</label>' + '<input type="text" class="form-control" id="itemTypeName" value="' + itemTypeName + '">' + '</div>';
}

function getCreatedEntity() {
	var createdItemType = {};
	createdItemType.itemTypeName = $("#itemTypeName").val();

	return createdItemType;
}

function getUpdatedEntity(id) {
	var updatedItemType = {};
	updatedItemType.id = id;
	updatedItemType.itemTypeName = $("#itemTypeName").val();

	return updatedItemType;
}

function updateEditedRow(tr, updatedEntity) {
	$(tr).find('.itemTypeName').text(updatedEntity.itemTypeName);
}
