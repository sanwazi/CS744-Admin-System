$(document).ready(function() {
	loadDiagnostic();
	addDiagnostic();
	showAddingDiagnosticTable();
	deleteDiagnostic();
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

function deleteDiagnostic() {
	$('button[name=diagnostic_delete]').each(
			function() {
				var diagnostic_Id = $(this).attr("id");
				var $button = $(this);
				$.ajax({
					type : "GET",
					url : "/EMR_Admin/diagnostic/deleteDiagnostic",
					data : 'diagnosticId=' + diagnostic_Id,							
					success : function(data) {
						// TODO
						if (data == "s") {
							$('#addingResult').html("Delete Success!");
							$('#addingResult').show();
							location.reload(true);
						} else if (data == "d") {
							$('#addingResult')
									.html("Delete Failure!");
							$('#addingResult').show();
						}
					},
					dataType : "text",
				});
			})
}

