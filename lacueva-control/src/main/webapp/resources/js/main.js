MvcUtil = {};
MvcUtil.showSuccessResponse = function(text, element) {
	MvcUtil.showResponse("success", text, element);
};
MvcUtil.showErrorResponse = function showErrorResponse(text, element) {
	MvcUtil.showResponse("error", text, element);
};
MvcUtil.showResponse = function(type, text, element) {
	var responseElementId = element.attr("id") + "Response";
	alert(element);
	alert(text);
	alert(element.attr("id"));
	var responseElement = $("#" + responseElementId);
	if (responseElement.length == 0) {
		responseElement = $('<span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>').insertAfter(element);
	} else {
		responseElement.replaceWith('<span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>');
		responseElement = $("#" + responseElementId);
	}
	responseElement.fadeIn("slow");
};
MvcUtil.xmlencode = function(xml) {
	// for IE
	var text;
	if (window.ActiveXObject) {
		text = xml.xml;
	}
	// for Mozilla, Firefox, Opera, etc.
	else {
		text = (new XMLSerializer()).serializeToString(xml);
	}
	return text.replace(/\&/g, '&' + 'amp;').replace(/</g, '&' + 'lt;').replace(/>/g, '&' + 'gt;').replace(/\'/g, '&' + 'apos;').replace(/\"/g, '&' + 'quot;');
};

$(document).ready(function() {
	/*
	 * Activate clicked Menu option
	 */
	$('#navmenu li a').click(function() {

		var menuOpt = $(this).attr('href').substring(1);
		if (menuOpt != 'navadmin') {
			$('#navmenu li').removeClass();
			$('#nav' + menuOpt).addClass('active');

			$('#bodyDiv').load(menuOpt);
		}

		// mvcAjax($(this))
	});

	function mvcAjax(link) {
		$.ajax({
			url : link.attr("href"),
			dataType : "text",
			success : function(text) {
				MvcUtil.showSuccessResponse(text, link);
			},
			error : function(xhr) {
				MvcUtil.showErrorResponse(xhr.responseText, link);
			}
		});
		return;
	}

	// Include CSRF token as header in JQuery AJAX requests
	// See
	// http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-include-csrf-token-ajax
	// var token = $("meta[name='_csrf']").attr("content");
	// var header = $("meta[name='_csrf_header']").attr("content");
	// $(document).ajaxSend(function(e, xhr, options) {
	// xhr.setRequestHeader(header, token);
	// });
});
