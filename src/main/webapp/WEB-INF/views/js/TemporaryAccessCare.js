var id;
var relation_id;
$(document).ready(function() {
	getPatientID();
	$("#physician_input").on('keyup', function() {
		loadAutocomplete();
	});
	loadTemporaryAccessCare();
});

function loadAutocomplete() {
	var input = $("#physician_input").val();

	$.ajax({
		type : "GET",
		url : "/physician/autocomplete",
		data : "input=" + input,
		success : function(data) {
			var suggestion = [];
			for ( var i in data) {
				suggestion.push(data[i].physicianName);
			}

			$("#physician_input").autocomplete({
				source : suggestion
			});
		}
	});

}

function loadTemporaryAccessCare() {
	$.ajax({
		type : "GET",
		url : "/relation/temporaryCare",
		data : "patient_id=" + id,
		success : function(data) {
			loadTemporaryAccessCareData(data);
		},
		dataType : "json",
	});
}

function getPatientID() {
	id = getUrlParameter("patient_id");
}

function loadTemporaryAccessCareData(temporary) {
	var dataSet = [];
	for ( var i in temporary) {
		var access = [];
		access.push(temporary[i].physician_name);
		access.push(temporary[i].access_right);
		access.push(temporary[i].end_date);
		var button = "<button name=\"temporary_access\" id=\""
				+ temporary[i].relation_id
				+ "\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o fa-lg\"></i> Delete</button>";
		access.push(button);
		dataSet.push(access);
	}
	console.log(dataSet);
	$('#dataTables-temporary-physician').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Physician Name",
			"class" : "center"
		}, {
			"title" : "Access Right",
			"class" : "center"
		}, {
			"title" : "Expired Date",
			"class" : "center"
		}, {
			"title" : "Action",
			"class" : "center"
		} ]
	});
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