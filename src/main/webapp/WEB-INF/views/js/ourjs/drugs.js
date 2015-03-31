//[{"drug_id":1,"drug_name":"druggg"},{"drug_id":2,"drug_name":"druggggs"},{"drug_id":3,"drug_name":"drugs"},{"drug_id":4,"drug_name":"Abacavir Sulfate (Ziagen)"},{"drug_id":5,"drug_name":"Abilify (Aripiprazole)"},{"drug_id":6,"drug_name":"Abiraterone Acetate Tablets (Zytiga)"},{"drug_id":7,"drug_name":"Actigall (Ursodiol, USP Capsules)"},{"drug_id":8,"drug_name":"Aflibercept (Eylea)"},{"drug_id":9,"drug_name":"Baclofen (Kemstro)"},{"drug_id":10,"drug_name":"Bacteriostatic Water (Bacteriostatic Water for Injection)"},{"drug_id":11,"drug_name":"Basiliximab (Simulect)"}]
$(document).ready(function() {

	loadAllDrug();
	showAddingDrugTable();
	
	addDrug();
	//listenToDelete();
});
//Get drugs list
function loadAllDrug() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/drug/getDrug",

		success : function(data) {
			loadDrug(data);
			//giveButtonLink();
			

		},
		dataType : "json",
	});
}
//Display drugs
function loadDrug(drugList) {
	console.log(drugList.length);
	var dataSet = [];
	for ( var i in drugList) {
		var drugItems = [];
		drugItems.push(drugList[i].drug_id);
		drugItems.push(drugList[i].drug_name);
		var editButton = generateEditButton(drugList[i].drug_id);
		var deleteButton = generateDeleteButton(drugList[i].drug_id);
		drugItems.push(editButton);
		drugItems.push(deleteButton);
		console.log(drugItems.length);
		dataSet.push(drugItems);
		
	}
	console.log(dataSet.length);

	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [ {
			"title" : "Drug ID",
			"class" : "center"
		}, {
			"title" : "Drug Name",
			"class" : "center"
		},{
			"title" : "Change",
			"class" : "center"
		},{
			"title" : "Delete",
			"class" : "center"
		}]
	});
	
}

//Add new drugs

function showAddingDrugTable(){
	$("#btn-showAddingDrugsTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}


function addDrug() {
	$('#addDrug').on(
			'click',
			function() {
				var drugName = $('#drugName').val();
				//var amount = $('#amount').val();
				$.ajax({
					type : "GET",
					url : "/EMR_Admin/drug/addDrug",
					data : 'drugName=' + drugName,
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
//						var row = '<tr>';
//						row += '<td>' + id_drug + '</td>';
//						row += '<td>' + amount + '</td>';
//						row += '</tr>';
//						var $rowDom = $.parseHTML(row);
//						$('#drugTableContent').append($rowDom);
//						$rowDom.show('slow');
					},
					dataType : "text",
				});
			});
}
//delete button listener
function listenToDelete(){
	//var buttonId = "#"+drugId+"_delete";
	$('#dataTable-example :button').click(
		function(){
			console.log(""+"button"+" has been clicked");
			alert(""+"button"+" has been clicked");
//			$.ajax({
//				type: "GET",
//				url: "/EMR_Admin/drug/deleteDrug",
//				data: 'drug_id='+drugId,
//				success:function(data){
//					if(data=="s"){
//						$('#deleteResult').html("Success!");
//						$('#deleteResult').show();
//						//loadAllDrug();
//						setTimeout("location.reload(true);",1000);
//						
//					}
//					else if(data=="d"){
//						$('#deleteResult').html("Failure! Duplicated Name!");
//						$('#deleteResult').show();
//					}
//				},dataType :"text",}
//			);
		
		}
	);
}
//generate button
function generateEditButton(drugId) {
	var button = "<a name=\"edit\" id=\""
			+ drugId
			+ "\" class=\"btn btn-warning btn-xs\" href=\"edit_drug.html?drug_id="
			+drugId+"\"><i class=\"fa fa-edit\"></i>Edit</a>";
	return button;
}
function generateDeleteButton(drugId) {
	var button = "<a name=\"delete\" id=\""
			+ drugId
			+ "_delete\" class=\"btn btn-danger btn-xs\" href=\"delete_drug.html?drug_id="
			+drugId+"\"><i class=\"fa fa-edit\"></i>Delete</a>";
	return button;
}

//var modifyButton = "<button name=\"relation_modify\" id=\""
//	+ relations[i].relation_id
//	+ "\" class=\"btn btn-warning btn-xs\"><i class=\"fa fa-pencil-square-o fa-lg\"></i> Change</button>";
//relation.push(modifyButton);
//var deleteButton = "<button name=\"relation_delete\" id=\""
//	+ relations[i].relation_id
//	+ "\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o fa-lg\"></i> Delete</button>";
//
//function giveButtonLink(){
//	$('a[name=edit]').each(function(){
//		var drug_id = $(this).attr("id");
//		var drug_name = $(this).attr("drug_name");
//		var $button = $(this);
//		$button.attr("href", "edit_drug.html?drug_id="+drug_id);	
//	})
//	$('a[name=delete]').each(function(){
//		var drug_id = $(this).attr("id");
//		var $button = $(this);
//		$button.attr("href", "delete_drug.html?drug_id="+drug_id);	
//	})
//}