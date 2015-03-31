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
		diagnostic.push(diagnostics[i].diagnostic_test_id);
		diagnostic.push(diagnostics[i].diagnostic_test_name);
		diagnostic.push(diagnostics[i].cost);
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
			"title" : "Diagnostic ID",
			"class" : "center"
		}, {
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
	$('#addDiagnostic').on(
			'click',
			function() {
				var DiagnosticName = $('#diagnosticName').val();
				var DiagnosticCost = $('#diagnosticCost').val();
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
							$('#addingResult')
									.html("Failure! Duplicated Name!");
							$('#addingResult').show();
						}
					},
					dataType : "text",
				});
			});
}
