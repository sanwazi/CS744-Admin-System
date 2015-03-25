var patientId;
var emrId;
var transcriptionId;
$(document).ready(function() {
	patientId = getUrlParameter("patientId");
	emrId = getUrlParameter("emrId");
	createNewTranscription();
});
function registerUpdateTranscriptionButton() {
	$('#updateTranscription').click(
			function() {
				var content = $('#comment').val();
				var abstraction = $('#abstraction').val();
				$.ajax({
					type : "POST",
					url : "/transcription/update",
					data : 'content=' + content + '&abstraction=' + abstraction
							+ '&transcriptionId=' + transcriptionId,
					success : function(data) {
						window.location.href = "/pages/emr.html?patient_id="
								+ patientId+'#orange';
					},
					dataType : "text",
				});
			});
}
function createNewTranscription() {
	var query = "emrId=" + emrId + "&patientId=" + patientId;
	$.ajax({
		type : "GET",
		url : "/transcription/create",
		data : query,
		success : function(data) {
			transcriptionId = data;
			getTranscriptionBasicContent(transcriptionId); // for 1st panel
			registerSurgeryInput();
			registerDiagnosticTestInput();
			$("#createPrescription").on('click', function() {
				createPrescription();
			});
			registerUpdateTranscriptionButton();
		},
		dataType : "text",
	});
}
function registerSurgeryInput() {
	$("#surgeryInput").on(
			"keyup",
			function() {
				console.log("here");
				var input = $('#surgeryInput').val();
				$.ajax({
					type : "GET",
					url : "/surgery/autocomplete",
					data : 'input=' + input,
					success : function(data) {
						var suggestion = [];
						for ( var i in data) {
							suggestion.push(data[i].surgery_id + "_"
									+ data[i].surgery_name);
						}
						$("#surgeryInput").autocomplete({
							source : suggestion
						});
					},
					dataType : "json",
				});
			});
	registerSurgeryAddButton();
}
function registerSurgeryAddButton() {
	$('#addSurgery').on(
			'click',
			function() {
				var id_surgery = $('#surgeryInput').val();
				var surgeryId = id_surgery.split('_')[0];
				var surgeryName = id_surgery.split('_')[1];
				$.ajax({
					type : "GET",
					url : "/surgery/add",
					data : 'surgeryName=' + surgeryName + '&transcriptionId='
							+ transcriptionId + '&surgeryId=' + surgeryId,
					success : function(data) {
						$('#surgery').append('<br>' + id_surgery);
					},
					dataType : "text",
				});
			});
}
function registerDiagnosticTestInput() {
	console.log("here");
	$("#diagnosticTestInput").on(
			"keyup",
			function() {
				console.log("here");
				var input = $('#diagnosticTestInput').val();
				$.ajax({
					type : "GET",
					url : "/diagnostictest/autocomplete",
					data : 'input=' + input,
					success : function(data) {

						var suggestion = [];
						for ( var i in data) {
							suggestion.push(data[i].diagnostic_test_id + "_"
									+ data[i].diagnostic_test_name);
						}
						$("#diagnosticTestInput").autocomplete({
							source : suggestion
						});
					},
					dataType : "json",
				});
			});
	registerDiagnostictestAddButton();
}
function registerDiagnostictestAddButton() {
	$('#addTest').on(
			'click',
			function() {
				var id_test = $('#diagnosticTestInput').val();
				var testId = id_test.split('_')[0];
				var testName = id_test.split('_')[1];
				$.ajax({
					type : "GET",
					url : "/diagnostictest/add",
					data : 'testName=' + testName + '&transcriptionId='
							+ transcriptionId + '&testId=' + testId,
					success : function(data) {
						$('#diagnostictest').append('<br>' + id_test);
					},
					dataType : "text",
				});
			});
}
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
			$("#patient_name").val(patient_name);
			$("#physician_name").val(physician_name);
			$("#comment").val(content);
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
function createPrescription() {
	if (!$("#panelcontent").is(':visible')) {
		$.ajax({
			type : "GET",
			url : "/prescription/add",
			data : "transcriptionId=" + transcriptionId + "&patientId="
					+ patientId,
			success : function(data) {
				$("#panelcontent").show("slow");
				var prescriptionId = data;
				registerAddDrug(prescriptionId);
			},
			dataType : "text",
		});
	}
}
function registerAddDrug(prescriptionId) {
	$("#drugInput").on("keyup", function() {
		console.log("here");
		var input = $('#drugInput').val();
		$.ajax({
			type : "GET",
			url : "/drug/searchWithInput",
			data : 'drugInput=' + input,
			success : function(data) {
				var suggestion = [];
				for ( var i in data) {
					suggestion.push(data[i].drug_id + "_" + data[i].drug_name);
				}
				$("#drugInput").autocomplete({
					source : suggestion
				});
			},
			dataType : "json",
		});
	});
	registerDrugAddButton(prescriptionId);
}
function registerDrugAddButton(prescriptionId) {
	$('#addDrug').on(
			'click',
			function() {
				var id_drug = $('#drugInput').val();
				var drugId = id_drug.split('_')[0];
				var drugName = id_drug.split('_')[1];
				var amount = $('#amount').val();
				$.ajax({
					type : "GET",
					url : "/drug/add",
					data : 'drugName=' + drugName + '&prescriptionId='
							+ prescriptionId + '&drugId=' + drugId + '&amount='
							+ amount,
					success : function(data) {
						var row = '<tr>';
						row += '<td>' + id_drug + '</td>';
						row += '<td>' + amount + '</td>';
						row += '</tr>';
						var $rowDom = $.parseHTML(row);
						$('#drugTableContent').append($rowDom);
						$rowDom.show('slow');
					},
					dataType : "text",
				});
			});
}