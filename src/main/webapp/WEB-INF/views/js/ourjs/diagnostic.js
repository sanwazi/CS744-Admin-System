$(document).ready(function() {
	loadDiagnostic();
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
		var modifyButton = "<button name=\"diagnostic_modify\" id=\""
				+ diagnostics[i].diagnostic_test_id
				+ "\" class=\"btn btn-warning btn-xs\"><i class=\"fa fa-pencil-square-o fa-lg\"></i> Change</button>";
		diagnostic.push(modifyButton);
		var deleteButton = "<button name=\"diagnostic_delete\" id=\""
				+ diagnostics[i].diagnostic_test_id
				+ "\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o fa-lg\"></i> Delete</button>";
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
		}]
	});
}
