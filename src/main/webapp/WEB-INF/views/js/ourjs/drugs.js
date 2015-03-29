//[{"drug_id":1,"drug_name":"druggg"},{"drug_id":2,"drug_name":"druggggs"},{"drug_id":3,"drug_name":"drugs"},{"drug_id":4,"drug_name":"Abacavir Sulfate (Ziagen)"},{"drug_id":5,"drug_name":"Abilify (Aripiprazole)"},{"drug_id":6,"drug_name":"Abiraterone Acetate Tablets (Zytiga)"},{"drug_id":7,"drug_name":"Actigall (Ursodiol, USP Capsules)"},{"drug_id":8,"drug_name":"Aflibercept (Eylea)"},{"drug_id":9,"drug_name":"Baclofen (Kemstro)"},{"drug_id":10,"drug_name":"Bacteriostatic Water (Bacteriostatic Water for Injection)"},{"drug_id":11,"drug_name":"Basiliximab (Simulect)"}]
$(document).ready(function() {

	loadAllDrug();
});

function loadAllDrug() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/drug/getDrug",

		success : function(data) {
			loadDrug(data);

		},
		dataType : "json",
	});
}
function loadDrug(drugList) {
	var dataSet = [];
	for ( var i in drugList) {
		var drugItems = [];
		drugItems.push(drugList[i].drug_id);
		drugItems.push(drugList[i].drug_name);
		dataSet.push(drugItems);
	}

	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [ {
			"title" : "Drug ID",
			"class" : "center"
		}, {
			"title" : "Drug Name",
			"class" : "center"
		}]
	});
}