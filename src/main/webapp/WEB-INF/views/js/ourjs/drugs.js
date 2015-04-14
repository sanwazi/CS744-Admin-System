//[{"drug_id":1,"drug_name_commercial":"druggg"},{"drug_id":2,"drug_name_commercial":"druggggs"},{"drug_id":3,"drug_name_commercial":"drugs"},{"drug_id":4,"drug_name_commercial":"Abacavir Sulfate (Ziagen)"},{"drug_id":5,"drug_name_commercial":"Abilify (Aripiprazole)"},{"drug_id":6,"drug_name_commercial":"Abiraterone Acetate Tablets (Zytiga)"},{"drug_id":7,"drug_name_commercial":"Actigall (Ursodiol, USP Capsules)"},{"drug_id":8,"drug_name_commercial":"Aflibercept (Eylea)"},{"drug_id":9,"drug_name_commercial":"Baclofen (Kemstro)"},{"drug_id":10,"drug_name_commercial":"Bacteriostatic Water (Bacteriostatic Water for Injection)"},{"drug_id":11,"drug_name_commercial":"Basiliximab (Simulect)"}]
//[{"drug_id":1,"drug_unique_id":"097a9215-ee07-451f-8ef1-dd66704b1c01","drug_lv":"1","drug_name_medical":"Hydromorphone","drug_name_commercial":"Exalgo","drug_unit":"MG/ML","drug_dose":"50","drug_reaction":null,"drug_usage":null,"drug_status":"Enable"},{"drug_id":2,"drug_unique_id":"f433da5e-e312-40fb-b891-a403e46f53b9","drug_lv":"1","drug_name_medical":"Hydromorphone","drug_name_commercial":"Dilaudid-hp","drug_unit":"MG/ML","drug_dose":"50","drug_reaction":null,"drug_usage":null,"drug_status":"Enable"}]
//Pharmacy json: [
//   {
//      "id":1,
//      "drugId":"097a9215-ee07-451f-8ef1-dd66704b1c01",
//      "drugType":"Prescription drug",
//      "drugNameMedical":"Hydromorphone",
//      "drugNameCommercial":"Exalgo",
//      "drugPrice":10.0,
//      "drugLv":"1",
//      "drugUnit":"MG/ML",
//      "drugDose":"50",
//      "drugReaction":null,
//      "drugUsage":null,
//      "drugStatus":"Enable"
//   }]
var testjson = "[{\"id\":1,\"drugId\":\"097a9215-ee07-451f-8ef1-dd66704b1c01\"," +
		"\"drugType\":\"Prescription drug\",\"drugNameMedical\":\"Hydromorphone\"," +
		"\"drugNameCommercial\":\"Exalgo\",\"drugPrice\":10.0,\"drugLv\":\"1\"," +
		"\"drugUnit\":\"MG/ML\",\"drugDose\":\"50\",\"drugReaction\":null," +
		"\"drugUsage\":null,\"drugStatus\":\"Enable\"}," +
		"{\"id\":2,\"drugId\":\"097a9215-ee07-451f-8ef1-dd66704b1c02\"," +
			"\"drugType\":\"Prescription drug\",\"drugNameMedical\":\"Hydromorphone2\"," +
			"\"drugNameCommercial\":\"Exalgo2\",\"drugPrice\":10.0,\"drugLv\":\"1\"," +
			"\"drugUnit\":\"MG/ML\",\"drugDose\":\"50\",\"drugReaction\":null," +
			"\"drugUsage\":null,\"drugStatus\":\"Enable\"}]";
$(document).ready(function() {
	console.log(testjson);
	loadAllDrug();
	addPharmacyDrugToDb(testjson);

//	loadAllDrug();
//	showAddingDrugTable();
//	
//	addDrug();
	//listenToDelete();
});
//Get drugs list
function loadAllDrug() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/drug/getDrug",

		success : function(data) {
				loadDrug(data);
				addPharmacyDrugToDb(testjson);
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
		drugItems.push(drugList[i].drug_name_commercial);
		var editButton = generateEditButton(drugList[i].drug_id);
		var deleteButton = generateDeleteButton(drugList[i].drug_id);
		drugItems.push(editButton);
		drugItems.push(deleteButton);
		//drugItems.push(drugList[i].drug_id);
		//drugItems.push(drugList[i].drug_unique_id);
		drugItems.push(drugList[i].drug_name.split('_')[0]);
		drugItems.push(drugList[i].drug_name.split('_')[1]);
		//drugItems.push(drugList[i].drug_unit);
		//drugItems.push(drugList[i].drug_dose);
		//drugItems.push(drugList[i].drug_reaction);
		//drugItems.push(drugList[i].drug_usage);
		//drugItems.push(drugList[i].drug_status);
		//var editButton = generateEditButton(drugList[i].drug_id);
		//var deleteButton = generateDeleteButton(drugList[i].drug_id);
		//drugItems.push(editButton);
		//drugItems.push(deleteButton);
		console.log(drugItems.length);
		dataSet.push(drugItems);
		
	}
	console.log(dataSet.length);

	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [{
			"title" : "Drug Commercial Name",
			"class" : "center"
		},{
			"title" : "Drug Medical Name",
			"class" : "center"
		}]
	});
	
}

//get drug from pharmacy
//function getDrugFromPharmacy(){
//	$('#get_drug_from_pharmacy').click(function(){
//		$.ajax({
//		     url:"http://138.49.101.83/Pharmacy/interface/drugList/all",
//		     dataType: 'jsonp', // Notice! JSONP <-- P (lowercase)
//		     success:function(json){
//		    	 
//		         // do stuff with json (in this case an array)
//		         alert("Success");
//		     },
//		     error:function(){
//		         alert("Error");
//		     }      
//		});
//		location.reload();
//	});
//}




//Add new drugs

function showAddingDrugTable(){
	$("#btn-showAddingDrugsTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}


//function addDrug() {
//	$('#addDrug').on(
//			'click',
//			function() {
//				var drug_name_commercial = $('#drug_name_commercial').val();
//				//var amount = $('#amount').val();
//				$.ajax({
//					type : "GET",
//					url : "/EMR_Admin/drug/addDrug",
//					data : 'drug_name_commercial=' + drug_name_commercial,
//					success : function(data) {
//						//TODO
//						if(data=="s"){
//							$('#addingResult').html("Success!");
//							$('#addingResult').show();
//							//loadAllDrug();
//							setTimeout("location.reload(true);",1000);
//							
//						}
//						else if(data=="d"){
//							$('#addingResult').html("Failure! Duplicated Name!");
//							$('#addingResult').show();
//						}
////						var row = '<tr>';
////						row += '<td>' + id_drug + '</td>';
////						row += '<td>' + amount + '</td>';
////						row += '</tr>';
////						var $rowDom = $.parseHTML(row);
////						$('#drugTableContent').append($rowDom);
////						$rowDom.show('slow');
//					},
//					dataType : "text",
//				});
//			});
//}
function addDrug(){
	$('#get_drug_from_pharmacy').click(function(){
		$.ajax({
		     url:"http://138.49.101.83/Pharmacy/interface/drugList/all",
		     dataType: 'jsonp', // Notice! JSONP <-- P (lowercase)
		     success:function(json){
		    	 addPharmacyDrugToDb(json);
		         // do stuff with json (in this case an array)
		         alert("Success");
		     },
		     error:function(){
		         alert("Error");
		     }      
		});
		location.reload();
	});
}

function addPharmacyDrugToDb(testjson){
	$('#get_drug_from_pharmacy').click(function(){
		var drugsFromPharmacy = $.parseJSON(testjson);
		var dataSet = [];
		for(var i in drugsFromPharmacy){
			var drug = {drug_id:drugsFromPharmacy[i].id,drug_name:drugsFromPharmacy[i].drugNameCommercial+"_"+drugsFromPharmacy[i].drugNameMedical};
			dataSet.push(drug);
		}
		var dataSetJsonResult = JSON.stringify(dataSet);
		console.log("dataSetJsonResult"+dataSetJsonResult);
		$.ajax({
			type : "POST",
			url : "/EMR_Admin/drug/addAllDrugFromPharmacyToDb",
			contentType: "application/json; charset=utf-8",
			mimeType: 'application/json',
			data : dataSetJsonResult,
			success : function(data) {
				console.log(data);
				location.reload();
//				//TODO
//				if(data=="s"){
////					$('#addingResult').html("Success!");
////					$('#addingResult').show();
//					alert("Success!");
//					//loadAllDrug();
//					//setTimeout("location.reload(true);",1000);
//					
//				}
//				else if(data=="d"){
//					alert("Failure!");
////					$('#addingResult').html("Failure! Duplicated Name!");
////					$('#addingResult').show();
//				}
			},
			dataType : "text",
		});
	});
	
}

//delete button listener
//function listenToDelete(){
//	//var buttonId = "#"+drugId+"_delete";
//	$('#dataTable-example :button').click(
//		function(){
//			console.log(""+"button"+" has been clicked");
//			alert(""+"button"+" has been clicked");
////			$.ajax({
////				type: "GET",
////				url: "/EMR_Admin/drug/deleteDrug",
////				data: 'drug_id='+drugId,
////				success:function(data){
////					if(data=="s"){
////						$('#deleteResult').html("Success!");
////						$('#deleteResult').show();
////						//loadAllDrug();
////						setTimeout("location.reload(true);",1000);
////						
////					}
////					else if(data=="d"){
////						$('#deleteResult').html("Failure! Duplicated Name!");
////						$('#deleteResult').show();
////					}
////				},dataType :"text",}
////			);
//		
//		}
//	);
//}
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
//		var drug_name_commercial = $(this).attr("drug_name_commercial");
//		var $button = $(this);
//		$button.attr("href", "edit_drug.html?drug_id="+drug_id);	
//	})
//	$('a[name=delete]').each(function(){
//		var drug_id = $(this).attr("id");
//		var $button = $(this);
//		$button.attr("href", "delete_drug.html?drug_id="+drug_id);	
//	})
//}
