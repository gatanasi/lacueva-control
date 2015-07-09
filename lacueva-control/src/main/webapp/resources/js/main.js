$(document).ready(function() {

	loadContent('');

	$(window).on('hashchange', function() {
		loadContent(location.hash.slice(1));
	});

});

function loadContent(hash) {
	var url = window.location.href;

	if (hash === '' || hash === url) {
		hash = 'resources/home.html';
	}

	$('html, body').animate({
		scrollTop : 0
	}, '600', 'swing');

	$('#bodyDiv').hide();
	$('#loadingDiv').spin();

	$('#bodyDiv').load(hash, function(response, status, xhr) {
		$('#loadingDiv').spin(false);
		$('#bodyDiv').show();
	});
}