var home = 'resources/home.html';

$(document).ready(function() {

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