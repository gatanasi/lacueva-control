var home = 'home';
var generalTimeout = 20000;

function keepSessionAlive() {
	$.post('ping');
}

$(document).ready(function() {

	$("#navmenu").find("li").on("click", "a:not([class='dropdown-toggle'])", function() {
		$("#navmenu").find("li").removeClass('active');
		$(this).parent().addClass('active');
		$('.navbar-collapse.in').collapse('hide');
	});

	var keepAlive = $("#intervalsDiv").data('keepaliveid');
	if (keepAlive == '') {
		keepAlive = setInterval(keepSessionAlive, 300000);
		$("#intervalsDiv").attr('data-keepaliveid', keepAlive);
	}

	$(window).on('hashchange', function() {
		loadHashContent(location.hash.slice(1));
	});

	var url = window.location.href;
	var hash = url.substring(url.indexOf("#") + 1);

	if (hash === url) {
		hash = home;
	}

	loading(hash);
});

function loadHashContent(hash) {
	if (hash === '') {
		hash = home;
	}

	$('html, body').animate({
		scrollTop : 0
	}, '600', 'swing');

	loading(hash);
}

function loading(hash) {
	$('#bodyDiv').hide();
	$('#loadingDiv').spin();

	$('#bodyDiv').load(hash, function(response, status, xhr) {
		$('#loadingDiv').spin(false);
		$('#bodyDiv').show();
	});
}