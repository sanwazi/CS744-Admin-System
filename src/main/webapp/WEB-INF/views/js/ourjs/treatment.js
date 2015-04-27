
$(document).ready(function() {
	loadAllTreatments();
	showAddingTreatmentTable();
	addTreatment();
});

function loadAllTreatments() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/treatment/treatmentList",
		success : function(data) {
			loadTreatment(data);
		},
		dataType : "json",
	});
}
function loadTreatment(treatmentList) {
	var dataSet = [];
	for ( var i in treatmentList) {
		var treatmentItems = [];
		treatmentItems.push(treatmentList[i].treatment_name);
		treatmentItems.push('<i class="fa fa-usd"></i>'+treatmentList[i].treatment_price);
		treatmentItems.push(treatmentList[i].can_medical_staff);
		var editButton = generateEditButton(treatmentList[i].treatment_id);
		var deleteButton = generateDeleteButton(treatmentList[i].treatment_id);
		treatmentItems.push(editButton);
		treatmentItems.push(deleteButton);
		dataSet.push(treatmentItems);
	}
	// console.log(dataSet);
	$('#dataTables-treatment').DataTable({
		responsive : true,
		data : dataSet,
		columns : [{
			"title" : "Name",
			"class" : "center"
		}, {
			"title" : "Cost (US Dollars)",
			"class" : "center"
		}, {
			"title" : "Can Medical Staff do",
			"class" : "center"
		}, {
			"title" : "Change",
			"class" : "center"
		},{
			"title" : "Delete",
			"class" : "center"
		}]
	});
}

//Add new treatment

function showAddingTreatmentTable(){
	$("#btn-showAddingTreatmentTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}


function addTreatment() {
	$('#addTreatment').on(
			'click',
			function() {
				var treatmentName = $('#treatmentName').val().trim();
				var cost = $('#treatmentCost').val();
				if (treatmentName == "") {
					$('#nameMessage').html(
							"Please provide treatment name.");
					return;
				}
				if (!/^[0-9A-Za-z| |)|-|(|,|.]+$/.test(treatmentName)) {
					$('#nameMessage')
							.text(
									"Please provide treatment name(only includes character and number).")
							.show();
					return;
				}
				if (cost == "") {
					$('#costMessage').html(
							"Please provide treatment cost.");
					return;
				}
				if (!(/^\d*(?:\.\d{0,2})?$/.test(cost))) {
					$('#costMessage')
							.html(
									"Please provide treatment cost like 123.45 or 123");
					return;
				}

				if (cost > 999999.99) {
					$('#costMessage')
							.html(
									"Please provide treatment cost which less or equal 999999.99");
					return;
				}
				var can_do = $('#can_ms_do input:radio:checked').val();
				$.ajax({
					type : "GET",
					url : "/EMR_Admin/treatment/addTreatment",
					data : 'treatment_name=' + treatmentName + "&cost="+cost +"&can_ms_do=" + can_do,
					success : function(data) {
						//TODO
						if(data=="s"){
							$('#addingResult').html("Success!");
							$('#addingResult').show();
							//loadAllDrug();
							setTimeout("location.reload(true);",1000);
							
						}
						else if(data=="d"){
							$('#addingResult').html("Failure! Duplicated Name!");
							$('#addingResult').show();
						}

					},
					dataType : "text",
				});
			});
}


function generateEditButton(treatment_id) {
	var button = "<a name=\"edit_treatment\" id=\""
			+ treatment_id
			+ "\" class=\"btn btn-warning btn-xs\" href=\"edit_treatment.html?treatment_id="
			+treatment_id+"\"><i class=\"fa fa-edit\"></i>Edit</a>";
	return button;
}
function generateDeleteButton(treatment_id) {
	var button = "<a name=\"delete_treatment\" id=\""
			+ treatment_id
			+ "\" class=\"btn btn-danger btn-xs\" href=\"delete_treatment.html?treatment_id="
			+treatment_id+"\"><i class=\"fa fa-edit\"></i>Delete</a>";
	return button;
}
