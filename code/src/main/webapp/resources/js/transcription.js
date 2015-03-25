$(document).ready(function() {
	$("#showimg").click(function() {
		console.log("here");
		$("#slider1_container").toggle("slow");
	});
	var transcriptionId = getUrlParameter("transcriptionId");
	getTranscriptionBasicContent(transcriptionId); // for 1st panel
	getDiagnosticAndSurgery(transcriptionId); // for 2nd panel
	getPrescription(transcriptionId);
})
function getTranscriptionBasicContent(transcriptionId) {
	$.ajax({
		type : "GET",
		url : "/transcription/getTranscription",
		data : "transcription_id=" + transcriptionId,
		success : function(data) {
			var patient_name = data.patient_name;
			var content = data.content;
			var physician_name = data.physician_name;
			var create_date = data.create_date;
			console.log(patient_name);
			$("#patient_name").text(patient_name);
			$("#physician_name").text(physician_name);
			$("#comment").text(content);
		},
		dataType : "json",
	});
}
function getDiagnosticAndSurgery(transcriptionId) {
	// Get diagnostic
	$.ajax({
		type : "GET",
		url : "/relation_transcription_diagnostic_test/getDiagnostic",
		data : "transcription_id=" + transcriptionId,
		success : function(data) {
			var diagnosticTest = "";
			for ( var i in data) {
				diagnosticTest += data[i].diagnostic_test_name + "<br>";
			}
			$("#diagnostic-test").empty();
			$("#diagnostic-test").append(diagnosticTest);
		},
		dataType : "json",
	});
	// Get surgery
	$.ajax({
		type : "GET",
		url : "/relation_transcription_surgery/getSurgery",
		data : "transcription_id=" + transcriptionId,
		success : function(data) {
			var surgery = "";
			for ( var i in data) {
				surgery += data[i].surgery_name + "<br>";
			}
			$("#surgery").empty();
			$("#surgery").append(surgery);
		},
		dataType : "json",
	});
}
function getPrescription(transcriptionId) {
	// Get prescription
	$.ajax({
		type : "GET",
		url : "/prescription/list",
		data : "transcription_id=" + transcriptionId,
		success : function(data) {
			loadPrescriptionTable(data);
		},
		dataType : "json",
	});
}
function loadPrescriptionTable(data) {
	var dataSet = [];
	for ( var i in data) {
		var prescription = {};
		prescription.prescription_id = data[i].prescription_id;
		prescription.patient_name = data[i].patient_name;
		prescription.physician_name = data[i].physician_name;
		prescription.create_date = data[i].create_date;
		dataSet.push(prescription);
	}
	var table = $('#example').DataTable({
		'data' : dataSet,
		'responsive' : true,
		"columns" : [ {
			"className" : 'details-control',
			"orderable" : false,
			"data" : null,
			"defaultContent" : ''
		}, {
			"data" : "prescription_id"
		}, {
			"data" : "patient_name"
		}, {
			"data" : "physician_name"
		}, {
			"data" : "create_date"
		} ],
		"order" : [ [ 1, 'asc' ] ]
	});

	// Add event listener for opening and closing details
	$('#example tbody').on('click', 'td.details-control', function() {
		var tr = $(this).closest('tr');
		var row = table.row(tr);

		if (row.child.isShown()) {
			// This row is already open - close it
			row.child.hide();
			tr.removeClass('shown');
		} else {
			// Open this row
			// row.child(format(row.data())).show();
			// tr.addClass('shown');
			format(row, tr, row.data());
		}
	});
}
function format(row, tr, d) {
	// `d` is the original data object for the row
	var prescription_id = d.prescription_id;
	$
			.ajax({
				type : "GET",
				url : "/prescription/item",
				data : "prescription_id=" + prescription_id,
				success : function(data) {
					var extension = '<table class="table table-bordered"><thead>'
							+ '<tr><th>Durg Id</th><th>Drug Name</th><th>Drug Amount</th></tr></thead><tbody>';
					for ( var i in data) {
						var onTr = '<tr><td>' + data[i].drug_id
								+ '</td><td class="drugname">'
								+ data[i].drug_name
								+ '</td><td class="drugamount">'
								+ data[i].amount + '</td></tr>';
						extension += onTr;
					}
					extension += '</tbody></table>';
					row.child(extension).show();
					tr.addClass('shown');
				},
				dataType : "json",
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