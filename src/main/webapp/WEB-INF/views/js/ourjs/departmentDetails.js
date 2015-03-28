$(document).ready(function() {
	loadPatient();
});

function loadPatient() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/getDepart",
		success : function(data) {
			loadDepartmentPhysicians(data);
			//loadTemporaryPatient(data.temporary);
			//updateButton();
		},
		dataType : "json",
	});
}
function loadDepartmentPhysicians(primary) {
	var dataSet = [];
	for ( var i in primary) {
		var patientItem = [];
		patientItem.push(primary[i].patient_name);
		patientItem.push(primary[i].patient_gender);
		patientItem.push(convertTimestampToDate(primary[i].patient_birthday));
		patientItem.push(primary[i].relation_type);
		if (primary[i].access_right == '11') {
			patientItem.push('read/write');
		} else if (primary[i].access_right == '10') {
			patientItem.push('read');
		}
		// patientItem.push(primary[i].access_right);
		var button = getEmrButton(primary[i].patient_id);
		patientItem.push(button);
		dataSet.push(patientItem);
	}
	// console.log(dataSet);
	$('#dataTables-primary').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Patient Name"
		}, {
			"title" : "Patient Gender"
		}, {
			"title" : "Patient Birthday"
		}, {
			"title" : "Relation Type",
			"class" : "center"
		}, {
			"title" : "Access Level",
			"class" : "center"
		}, {
			"title" : "Action",
			"class" : "center"
		} ]
	});
}
function loadTemporaryPatient(temporary) {
	// console.log(temporary)
	var dataSet = [];
	for ( var i in temporary) {
		var patientItem = [];
		patientItem.push(temporary[i].patient_name);
		patientItem.push(temporary[i].patient_gender);
		patientItem.push(convertTimestampToDate(temporary[i].patient_birthday));
		patientItem.push(temporary[i].relation_type);
		patientItem.push(temporary[i].start_date);
		patientItem.push(temporary[i].end_date);
		if (temporary[i].access_right == '11') {
			patientItem.push('read/write');
		} else if (temporary[i].access_right == '10') {
			patientItem.push('read');
		}
		// patientItem.push(temporary[i].access_right);
		patientItem.push(temporary[i].primary_physician_name)
		var button = getEmrButton(temporary[i].patient_id);
		console.log(button);
		patientItem.push(button);
		dataSet.push(patientItem);
	}
	// console.log(dataSet);
	$('#dataTables-temporary').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Patient Name"
		}, {
			"title" : "Patient Gender"
		}, {
			"title" : "Patient Birthday"
		}, {
			"title" : "Relation Type",
			"class" : "center"
		}, {
			"title" : "Start Date",
			"class" : "center"
		}, {
			"title" : "End Date",
			"class" : "center"
		}, {
			"title" : "Access Level",
			"class" : "center"
		}, {
			"title" : "Primary Physician",
			"class" : "center"
		}, {
			"title" : "Action",
			"class" : "center"
		} ]
	});
}
function getEmrButton(patient_id) {
	var button = "<a name=\"emr\" id=\""
			+ patient_id
			+ "\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i>EMR</a>";
	return button;
}
function updateButton() {
	$('a[name=emr]').each(function() {
		var patient_id = $(this).attr("id");
		var $button = $(this);
		$.ajax({
			type : "GET",
			url : "/emr/check",
			data : "patientId=" + patient_id,
			success : function(data) {
				if (data['hasEmr'] == 'true') {
					$button.attr("href", "emr.html?patient_id=" + patient_id);
				} else if (data['hasEmr'] == "false") {
					$button.attr('class', 'btn btn-danger btn-xs');
					$button.attr('href', 'create_emr.html?id=' + patient_id);

				}
			},
			dataType : "json",
		});
	})
}
function convertTimestampToDate(unix_timestamp) {
	// console.log(unix_timestamp);
	// var date = new Date(unix_timestamp * 1000);
	// console.log(date.toGMTString());
	// return date.toGMTString();
	return unix_timestamp;
}