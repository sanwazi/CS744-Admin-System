
//[{"surgery_id":1,"surgery_name":"Hand Surgery","cost":0},{"surgery_id":2,"surgery_name":"head and Neck Surgery","cost":0},{"surgery_id":3,"surgery_name":"Hernia Surgery","cost":0},{"surgery_id":4,"surgery_name":"Neurosurgery","cost":0},{"surgery_id":5,"surgery_name":"Orthopedic Surgery","cost":0},{"surgery_id":6,"surgery_name":"Ophthalmological Surgery","cost":0},{"surgery_id":7,"surgery_name":"Amputation","cost":121},{"surgery_id":8,"surgery_name":"Appendectomy","cost":0},{"surgery_id":9,"surgery_name":"Blepharoplasty","cost":0},{"surgery_id":10,"surgery_name":"Hysterectomy","cost":0}]
$(document).ready(function() {
	loadAllSurgerys();
	showAddingDrugTable();
	addSurgery();
});

function loadAllSurgerys() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/surgery/getSurgery",
		success : function(data) {
			loadSurgery(data);
		},
		dataType : "json",
	});
}
function loadSurgery(surgeryList) {
	var dataSet = [];
	for ( var i in surgeryList) {
		var surgeryItems = [];
		//surgeryItems.push(surgeryList[i].surgery_id);
		surgeryItems.push(surgeryList[i].surgery_name);
		surgeryItems.push('<i class="fa fa-usd"></i>'+surgeryList[i].cost);
		var editButton = generateEditButton(surgeryList[i].surgery_id);
		var deleteButton = generateDeleteButton(surgeryList[i].surgery_id);
		surgeryItems.push(editButton);
		surgeryItems.push(deleteButton);
		dataSet.push(surgeryItems);
	}
	// console.log(dataSet);
	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [{
			"title" : "Surgery Name",
			"class" : "center"
		}, {
			"title" : "Cost (US Dollars)",
			"class" : "center"
		}, {
			"title" : "Change",
			"class" : "center"
		}, {
			"title" : "Edit",
			"class" : "center"
		}]
	});
}

//Add new surgery

function showAddingDrugTable(){
	$("#btn-showAddingSurgeryTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}


function addSurgery() {
	$('#addSurgery').on(
			'click',
			function() {
				var surgeryName = $('#surgeryName').val().trim();
				if (surgeryName == "") {
					$('#nameMessage').html(
							"Please provide surgery name.");
					return;
				}
				if (!/^[0-9A-Za-z| |)|-|(|,|.]+$/.test(surgeryName)) {
					$('#nameMessage')
							.text(
									"Please provide surgery name(only includes character and number).")
							.show();
					return;
				}
				var cost = $('#cost').val();
				if (cost == "") {
					$('#costMessage').html(
							"Please provide surgery cost.");
					return;
				}
				if (!(/^\d*(?:\.\d{0,2})?$/.test(cost))) {
					$('#costMessage')
							.html(
									"Please provide surgery cost like 123.45 or 123");
					return;
				}

				if (cost > 999999.99) {
					$('#costMessage')
							.html(
									"Please provide surgery cost which less or equal 999999.99");
					return;
				}
				$.ajax({
					type : "GET",
					url : "/EMR_Admin/surgery/addSurgery",
					data : 'surgery_name=' + surgeryName + "&cost="+cost,
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


function generateEditButton(surgery_id) {
	var button = "<a name=\"edit\" id=\""
			+ surgery_id
			+ "\" class=\"btn btn-warning btn-xs\" href=\"edit_surgery.html?surgery_id="
			+surgery_id+"\"><i class=\"fa fa-edit\"></i>Edit</a>";
	return button;
}
function generateDeleteButton(surgery_id) {
	var button = "<a name=\"delete\" id=\""
			+ surgery_id
			+ "\" class=\"btn btn-danger btn-xs\" href=\"delete_surgery.html?surgery_id="
			+surgery_id+"\"><i class=\"fa fa-edit\"></i>Delete</a>";
	return button;
}
