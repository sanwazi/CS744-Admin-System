$(document).ready(function() {
	loadRelation();
	showAddingRelationTable();
	addPrimaryRelation();
	$("#patient_input").on('keyup', function() {
		loadPatientAutocomplete();
	});
	$("#physician_input").on('keyup', function() {
		loadPhysicianAutocomplete();
	});
	addPrimaryRelation();
});

function loadRelation() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/relation/physician_patient_list",
		success : function(data) {
			loadRelationData(data);
		},
		dataType : "json",
	});
}

function loadRelationData(relations) {
	var dataSet = [];
	for (var i = 0; i < relations.length; i++) {
		var relation = [];
		relation.push(relations[i].physician_name);
		relation.push(relations[i].patient_name);

		if (relations[i].relation_type == "PRIMARY_CARE") {
			var type = "<button type = button"
					+ "\" class=\"btn btn-primary btn-xs\">Primary</button>";
			relation.push(type);
		} else {
			var type = "<button type = button"
					+ "\" class=\"btn btn-success btn-xs\">Temporary</button>";
			relation.push(type);
		}

		var modifyButton = "<button name=\"relation_modify\" id=\""
				+ relations[i].relation_id
				+ "\" class=\"btn btn-warning btn-xs\"><i class=\"fa fa-pencil-square-o fa-lg\"></i> Edit</button>";
		relation.push(modifyButton);
		var deleteButton = "<button name=\"relation_delete\" id=\""
				+ relations[i].relation_id
				+ "\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o fa-lg\"></i> Delete</button>";
		relation.push(deleteButton);
		dataSet.push(relation);
	}

	$('#dataTables-physician-patient').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Physician",
			"class" : "center"
		}, {
			"title" : "Patient",
			"class" : "center"
		}, {
			"title" : "Care Privilege",
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
function showAddingRelationTable() {
	$("#btn-showAddingRelationTable").on('click', function() {
		$("#panelcontent-relation").show("slow");
	});
}
function addPrimaryRelation() {
	$('#addPrimaryRelation')
			.on(
					'click',
					function() {

						var patientName = $('#patient_input').val();
						var physicianName = $('#physician_input').val();
						if (patientName.indexOf('_') != -1) {
							var patient_name = patientName.split('_')[1];
							var patient_id = patientName.split('_')[0];
							var physician_name = physicianName.split('_')[1];
							var physician_id = physicianName.split('_')[0];
							$
									.ajax({
										type : "GET",
										url : "/EMR_Admin/relation/addPrimaryRelation",
										data : "patient_name=" + patient_name
												+ "&physician_name="
												+ physician_name
												+ "&patient_id=" + patient_id
												+ "&physician_id="
												+ physician_id,
										success : function(data) {
											// TODO
											if (data == "s") {
												$('#addingResult')
														.html(
																"Add primary relation, Success!");
												$('#addingResult').show();
												// loadAllDrug();
												setTimeout(
														"location.reload(true);",
														1000);

											} else if (data == "patientNameWrong") {
												$('#addingResult')
														.html(
																"Failure! No such patient");
												$('#addingResult').show();
											} else if (data == "physicianNameWrong") {
												$('#addingResult')
														.html(
																"Failure! No such physician");
												$('#addingResult').show();
											} else if (data == "hasPhysician") {
												$('#addingResult')
														.html(
																"Failure! this patient is registrated to some physician");
												$('#addingResult').show();
											} else{
												$('#addingResult')
												.html(
														"Failure");
										$('#addingResult').show();
											}

										},
										dataType : "text",
									});
						} else {
							$('#addingResult')
									.html(
											"Warining, input patient name along with patient ID and physician name along with physician name (_separate) please.");
							$('#addingResult').show();
						}

					});
}

function loadPatientAutocomplete() {
	var input = $("#patient_input").val();
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/patient/autocomplete",
		data : "input=" + input,
		success : function(data) {
			var suggestion = [];
			for ( var i in data) {
				suggestion
						.push(data[i].patient_id + "_" + data[i].patient_name);
			}

			$("#patient_input").autocomplete({
				source : suggestion
			});
		}
	});
}
function loadPhysicianAutocomplete() {
	var input = $("#physician_input").val();
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/physician/autocomplete",
		data : "input=" + input,
		success : function(data) {
			var suggestion = [];
			for ( var i in data) {

				suggestion.push(data[i].physicianId + "_"
						+ data[i].physicianName);
			}

			$("#physician_input").autocomplete({
				source : suggestion
			});
		}
	});
}