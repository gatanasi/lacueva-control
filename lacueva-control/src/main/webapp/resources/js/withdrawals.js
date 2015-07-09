$(document).ready(function() {
	/*
	 * $.get("withdrawals/prueba", { id : "1" }, function(data) {
	 * alert(data.attributes); alert(data.text);
	 * $('#withdrawalsTable').mounTable(data); });
	 * 
	 */
	$.ajax({
		url : "withdrawals/prueba",
		data : {
			id : "1"
		},
		success : function(data) {
			$('#withdrawalsTable').mounTable([ data ], {
				/* Options to your new line button */
				addLine : {
					/* New line button selector */
					button : "#addbtn",

					/* Callback function */
					onClick : function() {
						console.log('Line added!');
					}
				},

				/* Options to your delete line button */
				deleteLine : {
					/* Delete line button selector */
					button : ".mountable-remove-line-2",

					/* Callback function */
					onClick : function() {
						if (confirm("Está seguro?")) {
							return true;
						}
					}
				}
			});

		},
		error : function(data, status, er) {
			alert("error: " + data + " status: " + status + " er:" + er);
		}
	});

});
