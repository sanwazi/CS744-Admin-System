var id;
var emr_id;
var age;
var birthday;
var gender;
var name;
var race;
var occupation;
var address;
var height;
var weight;
var blood_group;
var vaccinations;
var diabetes;
var allergies;
$(document).ready(function() {
	bindCollapse();
	collapse();
	getPatientID();
	getPatientBasicInfo();
	getEmrBasicInfo();
	getPysicianList();
})
function getPatientID() {
	id = getUrlParameter("patient_id");
}
function bindCollapse() {
	jQuery(function($) {
		$('.panel-heading.clickable')
				.on(
						"click",
						function(e) {
							if ($(this).hasClass('panel-collapsed')) {
								// expand the panel
								$(this).parents('.panel').find('.panel-body')
										.slideDown();
								$(this).removeClass('panel-collapsed');
								$(this).find('i').removeClass(
										'glyphicon-chevron-down').addClass(
										'glyphicon-chevron-up');
							} else {
								// collapse the panel
								$(this).parents('.panel').find('.panel-body')
										.slideUp();
								$(this).addClass('panel-collapsed');
								$(this).find('i').removeClass(
										'glyphicon-chevron-up').addClass(
										'glyphicon-chevron-down');
							}
						});
	});
}
function collapse() {
	$('.panel-heading.clickable').parents('.panel').find('.panel-body')
			.slideUp();
	$('.panel-heading.clickable').addClass('panel-collapsed');
	$('.panel-heading.clickable').find('i').removeClass('glyphicon-chevron-up')
			.addClass('glyphicon-chevron-down');
}
function getPatientBasicInfo() {
	$.ajax({
		type : "GET",
		url : "/emr/getPatient",
		data : "patientId=" + id,
		success : function(data) {
			age = data["patient_age"];
			gender = data["patient_gender"];
			name = data["patient_name"];
			birthday = data["patient_birthday"];
			$('#name').val(name);
			$('#birth').val(birthday);
			$('#age').val(age);
			$('#gender').val(gender);
		},
		dataType : "json",
	});

}

function getEmrBasicInfo() {
	$.ajax({
		type : "GET",
		url : "/emr/viewEmr",
		data : "patientId=" + id,
		success : function(data) {
			emr_id = data["emr_id"];
			race = data["race"];
			occupation = data["occupation"];
			address = data["address"];
			height = data["height"];
			weight = data["weight"];
			blood_group = data["blood_group"];
			vaccinations = data["vaccinations"];
			diabetes = data["diabetes"];
			allergies = data["allergies"];
			$('#race').val(race);
			$('#occupation').val(occupation);
			$('#address').val(address);
			$('#height').val(height);
			$('#weight').val(weight);
			$('#blood_group').val(blood_group);
			$('#vaccinations').val(vaccinations);
			$('#diabetes').val(diabetes);
			$('#allergies').val(allergies);
			bindCreateTranscriptionButtonAction();
			getTranscriptionList(emr_id);
		},
		dataType : "json",
	});
}
function bindCreateTranscriptionButtonAction() {
	$("#create_emr").attr('href',
			'create_transcription.html?patientId=' + id + "&emrId=" + emr_id);
}
function getTranscriptionList(emr_id) {
	$.ajax({
		type : "GET",
		url : "/emr/getTranscription?emr_id=" + emr_id,
		success : function(data) {
			loadTranscription(data);
		},
		dataType : "json",
	});
}
function loadTranscription(data) {
	console.log(data);
	var dataSet = [];
	for ( var i in data) {
		var transcrptionItem = [];
		transcrptionItem.push(data[i].abstraction);
		transcrptionItem.push(data[i].create_date);
		var button = getTranscriptionButton(data[i].transcription_id);
		transcrptionItem.push(button);
		dataSet.push(transcrptionItem);
	}
	console.log(dataSet);
	// if ($.fn.dataTable.isDataTable('#dataTables-example1')) {
	// table = $('#dataTables-example1').DataTable({
	// "responsive" : true,
	// "data" : dataSet,
	// "columns" : [ {
	// "title" : "Abstract"
	// }, {
	// "title" : "Date"
	// }, {
	// "title" : "Action"
	// } ]
	// });
	// } else {
	// table = $('#dataTables-example1').DataTable({
	// "paging" : false,
	// "responsive" : true,
	// "data" : dataSet,
	// "columns" : [ {
	// "title" : "Abstract"
	// }, {
	// "title" : "Date"
	// }, {
	// "title" : "Action"
	// } ]
	// });
	// }
	$('#trans-table').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Abstract"
		}, {
			"title" : "Date"
		}, {
			"title" : "Action"
		} ]
	});
}
function getTranscriptionButton(transcription_id) {
	var button = "<a name=\"transcription\" href=\"transcription.html?transcriptionId="
			+ transcription_id
			+ "\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i>View</a>";
	return button;
}
function getPysicianList() {

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
