$(document).ready(function() {
	loadRelation();
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
		
		if(relations[i].relation_type == "PRIMARY_CARE" ){
			var type = "<button type = button"
				+ "\" class=\"btn btn-primary btn-xs\">Primary</button>";
			relation.push(type);
		}else{
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
