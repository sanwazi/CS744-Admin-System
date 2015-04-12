$(document).ready(function() {

	var relation_msp_id = getUrlParameter("relation_msp_id");
	getRelationBasicContent(relation_msp_id);
	listeningDeleteButton();
});

function getRelationBasicContent(relation_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/relation/getRelation_mspById",
		data : "relation_id=" + relation_id,
		success : function(data) {
			fillTextboxes(data);
		},
		dataType : "json",
	});
}
function listeningDeleteButton() {
	$("#delete_relation").on('click', function() {
		var relation_id = getUrlParameter("relation_msp_id");
		$.ajax({
			type : "GET",
			url : "/EMR_Admin/relation/deleteMSPRelation",
			data : "relation_id=" + relation_id,
			success : function(data) {

				if (data == "s") {
					$('#deletingResult').html("Success!");
					$('#deletingResult').show();
					setTimeout(jump, 1000);

				} else if (data == "d") {
					$('#deletingResult').html("Failure! Something is Wrong!");
					$('#deletingResult').show();
				}
			},
			dataType : "text",
		});
	});
}

function jump() {
	location.href = "Physician_Medical_Staff.html";
}

function fillTextboxes(relation) {
	$("#physician_name").val(relation.physician_name);
	$("#medical_staff_name").val(relation.ms_name);
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
