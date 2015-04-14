var patientId;
var physicianId;
$(document).ready(function() {
	loadRelation();
//	showAddingRelationTable();
//	addPrimaryRelation();
//
//	$("#patient_input").on('keyup', function() {
//		loadPatientAutocomplete();
//	});
//	$("#physician_input").on('keyup', function() {
//		loadPhysicianAutocomplete();
//	});
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
		
		physicianNames = relations[i].physician_name.split(" ");
		relation.push(physicianNames[0]);
		if( physicianNames.length == 1 )
			relation.push("None");
		else
			relation.push(physicianNames[1]);
		
		patientNames = relations[i].patient_name.split(" ");
		relation.push(patientNames[0]);
		if( patientNames.length == 1 )
			relation.push("None");
		else
			relation.push(patientNames[1]);

		if (relations[i].relation_type == "PRIMARY_CARE") {
			var type = "<button type = button"
					+ "\" class=\"btn btn-primary btn-xs\">Primary</button>";
			relation.push(type);
		} else {
			var type = "<button type = button"
					+ "\" class=\"btn btn-success btn-xs\">Temporary</button>";
			relation.push(type);
		}

		
//		var deleteButton = "<a name=\"delete_relation\" id=\""
//				+ relations[i].relation_id
//				+ "\" class=\"btn btn-danger btn-xs\" href=\"delete_relation_PP.html?relation_id="
//				+ relations[i].relation_id + "\""
//				+ " ><i class=\"fa fa-trash-o fa-lg\"></i> Delete</a>";
//		relation.push(deleteButton);
		dataSet.push(relation);
	}

	$('#dataTables-physician-patient').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Physician First Name",
			"class" : "center"
		},  {
			"title" : "Physician Last Name",
			"class" : "center"
		}, {
			"title" : "Patient First Name",
			"class" : "center"
		}, {
			"title" : "Patient Last Name",
			"class" : "center"
		},{
			"title" : "Care Privilege",
			"class" : "center"
		} ]
//		}, {
//			"title" : "Delete",
//			"class" : "center"
//		} ]
	});
}
//function showAddingRelationTable() {
//	$("#btn-showAddingRelationTable").on('click', function() {
//		$("#panelcontent-relation").show("slow");
//	});
//}
//function addPrimaryRelation() {
//	$('#addPrimaryRelation')
//			.on(
//					'click',
//					function() {
//
//						var patientName = $('#patient_input').val();
//						var physicianName = $('#physician_input').val();
//						if (patientName.indexOf('_') != -1) {
//							var patient_name = patientName.split('_')[1];
//							var patient_id = patientName.split('_')[0];
//							var physician_name = physicianName.split('_')[1];
//							var physician_id = physicianName.split('_')[0];
//							$
//									.ajax({
//										type : "GET",
//										url : "/EMR_Admin/relation/addPrimaryRelation",
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
//																"Add primary relation, Success!");
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
//
//					});
//}
//
//function loadPatientAutocomplete() {
//
//	var input = $("#patient_input").val();
//	$.ajax({
//		type : "GET",
//		url : "/EMR_Admin/patient/autocompleteWithFullName",
//		data : "input=" + input,
//		success : function(data) {
//
//			$("#patient_input").autocomplete({
//				source : data,
//				select : function(event, ui) {
//					var item = ui.item;
//					showPatientWithFullName(item.lable);
//				}
//			});
//		}
//	});
//}
//function loadPhysicianAutocomplete() {
//	var input = $("#physician_input").val();
//	$.ajax({
//		type : "GET",
//		url : "/EMR_Admin/physician/autocomplete",
//		data : "input=" + input,
//		success : function(data) {
//			var suggestion = [];
//			for ( var i in data) {
//
//				suggestion.push(data[i].physicianId + "_"
//						+ data[i].physicianName);
//			}
//
//			$("#physician_input").autocomplete({
//				source : suggestion
//			});
//		}
//	});
//}
//
//function showPatientWithFullName(patient_id) {
//	$
//			.ajax({
//				type : "GET",
//				url : "/EMR_Admin/patient/getPatientById",
//				data : "patient_id=" + patient_id,
//				success : function(data) {
//					bootbox
//							.dialog({
//								title : "Get the patient you want?",
//								message : '<div class="row">  '
//										+ '<div class="col-md-12"> '
//										+ '<form class="form-horizontal"> '
//										+
//
//										'<div class="form-group"> <div class="row"> ' 
//										+ '<label class="col-md-4 control-label" for="name">Name</label> '
//										+
//
//										'<div class="col-md-4"> '
//										+ '<input id="name" name="name" type="text" placeholder="'
//										+ data.patient_name
//										+ '"class="form-control input-md" disabled> '
//										+ '</div> </div>'
//
//										+ '<div class="row"> <label class="col-md-4 control-label" for="gender">Gender</label> '
//										+ '<div class="col-md-4"> '
//										+ '<input id="gender" name="gender" type="text" placeholder="'
//										+ data.patient_gender
//										+ '"class="form-control input-md" disabled> '
//										+ '</div> </div>' 
//
//										+ '<div class="row"> <label class="col-md-4 control-label" for="age">Age</label> '
//										+ '<div class="col-md-4"> '
//										+ '<input id="age" name="age" type="text" placeholder="'
//										+ data.patient_age
//										+ '"class="form-control input-md" disabled> '
//										+ '</div> </div>' 
//										
//										+ '<div class="row"> <label class="col-md-4 control-label" for="birthday">Birthday</label> '
//										+ '<div class="col-md-4"> '
//										+ '<input id="birthday" name="birthday" type="text" placeholder="'
//										+ data.patient_birthday
//										+ '"class="form-control input-md" disabled> '
//										+ '</div> </div>' 
//										
//										+ '<div class="row"> <label class="col-md-4 control-label" for="SSN">SSN</label> '
//										+ '<div class="col-md-4"> '
//										+ '<input id="SSN" name="SSN" type="text" placeholder="'
//										+ data.patient_ssn
//										+ '"class="form-control input-md" disabled> '
//										+ '</div> </div>' +
//										
//										'</div>' +
//
//										'</form> </div>  </div>',
//								buttons : {
//									success : {
//										label : "Save",
//										className : "btn-success",
//										callback : function() {
//											patientId = data.patient_id;
//										}
//									}
//								}
//							});
//
//				}
//			});
// }