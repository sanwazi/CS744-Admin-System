$(document).ready(function() {
	var treatment_id = getUrlParameter("treatment_id");
	gettreatmentBasicContent(treatment_id);
	listeningUpdateButton();
});

function gettreatmentBasicContent(treatment_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/treatment/getTreatment",
		data : "treatment_id=" + treatment_id,
		success : function(data) {
			var treatment_name = data.treatment_name;
			var cost = data.treatment_price;
			var can_do = data.can_medical_staff;
			fillTextboxes(treatment_name, cost, can_do);
		},
		dataType : "json",
	});
}
function listeningUpdateButton() {
	$("#update_treatment")
			.on(
					'click',
					function() {
						var treatment_id = getUrlParameter("treatment_id");
						var treatment_name = $("#treatment_name").val().trim();
						var cost = $("#treatment_cost").val();
						if (treatment_name == "") {
							$('#nameMessage').html(
									"Please provide treatment name.");
							return;
						}
						console.log(treatment_name);
						if (!(/^[0-9A-Za-z| ]+$/.test(treatment_name))) {
							$('#nameMessage')
									.text(
											"Please provide treatment name(only includes character and number).")
									.show();
							return;
						}
						if (cost == "") {
							$('#costMessage').html(
									"Please provide treatment cost.");
							return;
						}
						if (!(/^\d*(?:\.\d{0,2})?$/.test(cost))) {
							$('#costMessage')
									.html(
											"Please provide treatment cost like 123.45 or 123");
							return;
						}

						if (cost > 999999.99) {
							$('#costMessage')
									.html(
											"Please provide treatment cost which less or equal 999999.99");
							return;
						}
						var can_do = $('#can_ms_do input:radio:checked').val();
						$.ajax({
							type : "GET",
							url : "/EMR_Admin/treatment/updateTreatment",
							data : "treatment_id=" + treatment_id
									+ "&treatment_name=" + treatment_name
									+ "&cost=" + cost + "&can_ms_do=" + can_do,
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
	location.href = "treatment.html";
}

function fillTextboxes(treatment_name, cost, can_do) {
	$("#treatment_name").val(treatment_name);
	$("#treatment_cost").val(cost);
	if (can_do == "yes")
		document.getElementById("yes").checked = true;
	else
		document.getElementById("no").checked = true;
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