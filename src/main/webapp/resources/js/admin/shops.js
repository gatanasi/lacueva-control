$(document).ready(function() {

	$("#createBtn").on("click", function() {
		createEntity(createShopForm("", "", "", ""), "admin/shops/create");
		return false;
	});
	$(".delBtn").on("click", function() {
		delRow($(this).closest('tr'), "admin/shops/delete");
		return false;
	});
	$(".editBtn").on("click", function() {
		var tr = $(this).closest('tr');

		var shopId = $(tr).data('id');
		var shopName = $(tr).find('.shopName').text();
		var shopCash = $(tr).find('.shopCash').text();
		var shopDate = $(tr).find('.shopDate').text();
		var shopItems = $(tr).find('.shopItems').text();

		var form = createShopForm(shopName, shopCash, shopDate, shopItems);

		editRow(tr, form, "admin/shops/edit");

		return false
	});
});

function createShopForm(shopName, shopCash, shopDate, shopItems) {
	return '<div class="form-group">' + '<label for="shopName">Nombre</label>' + '<input type="text" class="form-control" id="shopName" value="' + shopName + '">' + '</div>'
			+ '<div class="form-group">' + '<label for="shopCash">Caja</label>' + '<input type="text" class="form-control" id="shopCash" value=' + shopCash + '>' + '</div>'
			+ '<div class="form-group">' + '<label for="shopDate">Fecha</label>' + '<input type="text" class="form-control" id="shopDate" value=' + shopDate + '>' + '</div>'
			+ '<div class="form-group">' + '<label for="shopItems">Art&iacute;culos</label>' + '<input type="text" class="form-control" id="shopItems" value=' + shopItems + '>' + '</div>';
}

function getCreatedEntity() {
	var createdShop = {};
	createdShop.shopName = $("#shopName").val();
	createdShop.shopCash = $("#shopCash").val();
	createdShop.shopDate = $("#shopDate").val();
	createdShop.shopItems = $("#shopItems").val();

	return createdShop;
}

function getUpdatedEntity(id) {
	var updatedShop = {};
	updatedShop.id = id;
	updatedShop.shopName = $("#shopName").val();
	updatedShop.shopCash = $("#shopCash").val();
	updatedShop.shopDate = $("#shopDate").val();
	updatedShop.shopItems = $("#shopItems").val();

	return updatedShop;
}

function updateEditedRow(tr, updatedEntity) {
	$(tr).find('.shopName').text(updatedEntity.shopName);
	$(tr).find('.shopCash').text(updatedEntity.shopCash);
	$(tr).find('.shopDate').text(updatedEntity.shopDate);
	$(tr).find('.shopItems').text(updatedEntity.shopItems);
}
