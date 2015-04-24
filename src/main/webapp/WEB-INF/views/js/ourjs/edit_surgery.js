//{"surgery_id":1,"surgery_name":"Hand Surgery","cost":0}

$(document).ready(function() {
	var surgery_id = getUrlParameter("surgery_id");
	getSurgeryBasicContent(surgery_id);
	listeningUpdateButton();
});

function getSurgeryBasicContent(surgery_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/surgery/getSurgeryById",
		data : "surgery_id=" + surgery_id,
		success : function(data) {
			var surgery_name = data.surgery_name;
			var cost = data.cost;
			fillTextboxes(surgery_name, cost);
		},
		dataType : "json",
	});
}
function listeningUpdateButton() {
	$("#update_surgery")
			.on(
					'click',
					function() {
						var surgery_id = getUrlParameter("surgery_id");
						var surgery_name = $("#surgery_name").val().trim();
						var cost = $("#surgery_cost").val();
						if (surgery_name == "") {
							$('#nameMessage').html(
									"Please provide surgery name.");
							return;
						}
						if (!(/^[0-9A-Za-z| ]+$/.test(surgery_name))) {
							$('#nameMessage')
									.text(
											"Please provide surgery name(only includes character and number).")
									.show();
							return;
						}
						if (cost == "") {
							$('#costMessage').html(
									"Please provide surgery cost.");
							return;
						}
						if (!(/^\d*(?:\.\d{0,2})?$/.test(cost))) {
							$('#costMessage')
									.html(
											"Please provide surgery cost like 123.45 or 123");
							return;
						}

						if (cost > 999999.99) {
							$('#costMessage')
									.html(
											"Please provide surgery cost which less or equal 999999.99");
							return;
						}
						$.ajax({
							type : "GET",
							url : "/EMR_Admin/surgery/updateSurgery",
							data : "surgery_id=" + surgery_id
									+ "&surgery_name=" + surgery_name
									+ "&cost=" + cost,
							success : function(data) {

								if (data == "s") {
									$('#addingResult').html("Success!");
									$('#addingResult').show();
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
	location.href = "surgery.html";
}

function fillTextboxes(surgery_name, cost) {
	$("#surgery_name").val(surgery_name);
	$("#surgery_cost").val(cost);
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