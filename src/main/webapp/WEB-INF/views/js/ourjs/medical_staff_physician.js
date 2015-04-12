$(document).ready(function() {
	loadRelation();
	showAddingRelationTable();
	addMSPRelation();
	// $("#patient_input").on('keyup', function() {
	// loadPatientAutocomplete();
	// });
	// $("#physician_input").on('keyup', function() {
	// loadPhysicianAutocomplete();
	// });
});

function loadRelation() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/relation/physician_medical_staff",
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
		relation.push(relations[i].ms_name);

		var modifyButton = "<a name=\"relation_msp_edit\" id=\""
				+ relations[i].ms_id
				+ "\" class=\"btn btn-warning btn-xs\" href=\"edit_relation_msp.html?relation_msp_id="
				+ relations[i].ms_id
				+ "\"><i class=\"fa fa-pencil-square-o fa-lg\"></i> Edit</a>";
		relation.push(modifyButton);

		var deleteButton = "<a name=\"delete_relation\" id=\""
				+ relations[i].ms_id
				+ "\" class=\"btn btn-danger btn-xs\" href=\"delete_relation_msp.html?relation_msp_id="
				+ relations[i].ms_id + "\""
				+ " ><i class=\"fa fa-trash-o fa-lg\"></i> Delete</a>";
		relation.push(deleteButton);
		dataSet.push(relation);
	}

	$('#dataTables-physician-medical-staff').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Physician",
			"class" : "center"
		}, {
			"title" : "Medical Staff",
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
function addMSPRelation() {
	$('#addRelationMSP')
			.on(
					'click',
					function() {

						var patientName = $('#medical_staff_input').val();
						var physicianName = $('#physician_input').val();
						console.log(patientName);
						console.log(physicianName);
//						if (patientName.indexOf('_') != -1) {
//							var patient_name = patientName.split('_')[1];
//							var patient_id = patientName.split('_')[0];
//							var physician_name = physicianName.split('_')[1];
//							var physician_id = physicianName.split('_')[0];
//							$
//									.ajax({
//										type : "GET",
//										url : "/EMR_Admin/relation/addMSPRelation",
//										data : "patient_name=" + patient_name
//												+ "&physician_name="
//												+ physician_name
//												+ "&patient_id=" + patient_id
//												+ "&physician_id="
//												+ physician_id,
//										success : function(data) {
//											// TODO
//											if (data == "s") {
//												$('#addingResult')
//														.html(
//																"Add MSP relation, Success!");
//												$('#addingResult').show();
//												// loadAllDrug();
//												setTimeout(
//														"location.reload(true);",
//														1000);
//
//											} else if (data == "patientNameWrong") {
//												$('#addingResult')
//														.html(
//																"Failure! No such patient");
//												$('#addingResult').show();
//											} else if (data == "physicianNameWrong") {
//												$('#addingResult')
//														.html(
//																"Failure! No such physician");
//												$('#addingResult').show();
//											} else if (data == "hasPhysician") {
//												$('#addingResult')
//														.html(
//																"Failure! this patient is registrated to some physician");
//												$('#addingResult').show();
//											} else {
//												$('#addingResult').html(
//														"Failure");
//												$('#addingResult').show();
//											}
//
//										},
//										dataType : "text",
//									});
//						} else {
//							$('#addingResult')
//									.html(
//											"Warining, input patient name along with patient ID and physician name along with physician name (_separate) please.");
//							$('#addingResult').show();
//						}

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