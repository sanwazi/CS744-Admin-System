//{"diagnostic_id":1,"diagnostic_name":"Hand diagnostic","cost":0}

$(document).ready(function() {
	var diagnostic_id = getUrlParameter("diagnostic_id");
	getdiagnosticBasicContent(diagnostic_id);
	listeningUpdateButton();
});

function getdiagnosticBasicContent(diagnostic_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/diagnostic/getdiagnosticById",
		data : "diagnostic_id=" + diagnostic_id,
		success : function(data) {
			var diagnostic_name = data.diagnostic_test_name;
			var cost = data.cost;
			fillTextboxes(diagnostic_name, cost);
		},
		dataType : "json",
	});
}
function listeningUpdateButton() {
	$("#update_diagnostic")
			.on(
					'click',
					function() {
						var diagnostic_id = getUrlParameter("diagnostic_id");
						var diagnostic_name = $("#diagnostic_name").val()
								.trim();
						if (diagnostic_name == "") {
							$('#nameMessage').html(
									"Please provide diagnostic test name.");
							return;
						}
						if (!(/^[0-9A-Za-z| ]+$/.test(diagnostic_name))) {
							$('#nameMessage')
									.text(
											"Please provide diagnostic test name(only includes character and number).")
									.show();
							return;
						}
						var cost = $("#diagnostic_cost").val();
						if (cost == "") {
							$('#costMessage').html(
									"Please provide diagnostic test cost.");
							return;
						}
						if (!(/^\d*(?:\.\d{0,2})?$/.test(cost))) {
							$('#costMessage')
									.html(
											"Please provide diagnostic test cost like 123.45 or 123");
							return;
						}

						if (cost > 999999.99) {
							$('#costMessage')
									.html(
											"Please provide diagnostic test cost which less or equal 999999.99");
							return;
						}
						$.ajax({
							type : "GET",
							url : "/EMR_Admin/diagnostic/updateDiagnostic",
							data : "diagnostic_id=" + diagnostic_id
									+ "&diagnostic_name=" + diagnostic_name
									+ "&cost=" + cost,
							success : function(data) {

								if (data == "s") {
									$('#addingResult').html("Success!");
									$('#addingResult').show();
									// loadAllDrug();
									setTimeout(jump, 1000);

								} else if (data == "d") {
									$('#addingResult').html(
											"Failure! Duplicated Name!");
									$('#addingResult').show();
								}
							},
							dataType : "text",
						});
					});
}

function jump() {
	location.href = "diagnostic.html";
}

function fillTextboxes(diagnostic_name, cost) {
	$("#diagnostic_name").val(diagnostic_name);
	$("#diagnostic_cost").val(cost);
}

function getUrlParameter(sParam) {

	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');

	for (var i = 0; i < sURLVariables.length; i++) {

		var sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] == sParam) {

			return sParameterName[1];

		}
	}
}