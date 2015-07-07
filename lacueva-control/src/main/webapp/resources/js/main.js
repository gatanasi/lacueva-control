$(document).ready(function() {
	/*
	 * Activate clicked Menu option
	 */
	function loadContent(hash) {
		if (hash === '') {
			hash = 'resources/home.html';
		}

		$('html, body').animate({
			scrollTop : 0
		}, '600', 'swing');

		$('#bodyDiv').hide();
		$('<div class="loading">Cargando...</div>').appendTo('body');

		$('#bodyDiv').load(hash);
	}

	$(window).on('hashchange', function() {
		loadContent(location.hash.slice(1));
	});

	var url = window.location.href;
	var hash = url.substring(url.indexOf("#") + 1);

	if (hash === url) {
		hash = 'resources/home.html';
	}

	$('#bodyDiv').hide();
	$('<div class="loading">Cargando...</div>').appendTo('body');

	$('#bodyDiv').load(hash);
});
