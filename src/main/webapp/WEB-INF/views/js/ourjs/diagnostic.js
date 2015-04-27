$(document).ready(function() {
	loadDiagnostic();
	addDiagnostic();
	showAddingDiagnosticTable();
});

function loadDiagnostic() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/diagnosticList",
		success : function(data) {
			loadDiagnosticData(data);
		},
		dataType : "json",
	});
}

function loadDiagnosticData(diagnostics) {
	var dataSet = [];
	for (var i = 0; i < diagnostics.length; i++) {
		var diagnostic = [];
		diagnostic.push(diagnostics[i].diagnostic_test_name);
		diagnostic.push('<i class="fa fa-usd"></i>'+diagnostics[i].cost);
		var modifyButton = "<a name=\"diagnostic_edit\" id=\""
				+ diagnostics[i].diagnostic_test_id
				+ "\" class=\"btn btn-warning btn-xs\" href=\"edit_diagnostic.html?diagnostic_id="
				+ diagnostics[i].diagnostic_test_id
				+ "\"><i class=\"fa fa-pencil-square-o fa-lg\"></i> Edit</a>";
		diagnostic.push(modifyButton);
		var deleteButton = "<a name=\"diagnostic_delete"
				+ "id=\""
				+ diagnostics[i].diagnostic_test_id
				+ "\" class=\"btn btn-danger btn-xs\" href=\"delete_diagnostic.html?diagnostic_id="
				+ diagnostics[i].diagnostic_test_id + "\""
				+ " ><i class=\"fa fa-trash-o fa-lg\"></i> Delete</a>";
		diagnostic.push(deleteButton);
		dataSet.push(diagnostic);
	}

	$('#dataTables-diagnostic').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Diagnostic Name",
			"class" : "center"
		}, {
			"title" : "Cost(US Dollars)",
			"class" : "center"
		}, {
			"title" : "Change",
			"class" : "center"
		}, {
			"title" : "Delete",
			"class" : "center"
		} ]
	});
}

function showAddingDiagnosticTable() {
	$("#btn-showAddingDiagnosticsTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}

function addDiagnostic() {
	$('#addDiagnostic')
			.on(
					'click',
					function() {
						var DiagnosticName = $('#diagnosticName').val().trim();
						if (DiagnosticName == "") {
							$('#nameMessage').html(
									"Please provide diagnostic test name.");
							return;
						}
						if (!/^[0-9A-Za-z]+$/.test(DiagnosticName)) {
							$('#nameMessage')
									.text(
											"Please provide diagnostic test name(only includes character and number).")
									.show();
							return;
						}

						var DiagnosticCost = $('#diagnosticCost').val();
						if (DiagnosticCost == "") {
							$('#costMessage').html(
									"Please provide diagnostic test cost.");
							return;
						}
						if (!(/^\d*(?:\.\d{0,2})?$/.test(DiagnosticCost))) {
							$('#costMessage')
									.html(
											"Please provide diagnostic test cost like 123.45 or 123");
							return;
						}

						if (DiagnosticCost > 999999.99) {
							$('#costMessage')
									.html(
											"Please provide diagnostic test cost which less or equal 999999.99");
							return;
						}

						$.ajax({
							type : "GET",
							url : "/EMR_Admin/diagnostic/addDiagnostic",
							data : 'diagnosticName=' + DiagnosticName
									+ '&diagnosticCost=' + DiagnosticCost,
							success : function(data) {
								// TODO
								if (data == "s") {
									$('#addingResult').html("Success!");
									$('#addingResult').show();
									location.reload(true);
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
