//var departmentName;
//[{"surgery_id":1,"surgery_name":"Hand Surgery","cost":0},{"surgery_id":2,"surgery_name":"head and Neck Surgery","cost":0},{"surgery_id":3,"surgery_name":"Hernia Surgery","cost":0},{"surgery_id":4,"surgery_name":"Neurosurgery","cost":0},{"surgery_id":5,"surgery_name":"Orthopedic Surgery","cost":0},{"surgery_id":6,"surgery_name":"Ophthalmological Surgery","cost":0},{"surgery_id":7,"surgery_name":"Amputation","cost":121},{"surgery_id":8,"surgery_name":"Appendectomy","cost":0},{"surgery_id":9,"surgery_name":"Blepharoplasty","cost":0},{"surgery_id":10,"surgery_name":"Hysterectomy","cost":0}]
$(document).ready(function() {
	//loadPatient();
	//departmentName = getUrlParameter("department_name");
	//console.log(departmentName);
	loadAllSurgerys();
});

function loadAllSurgerys() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/surgery/getSurgery",
		//data: "department_name="+departmentName,
		success : function(data) {
			loadSurgery(data);
			//giveButtonLink();
			//loadTemporaryPatient(data.temporary);
			//updateButton();
		},
		dataType : "json",
	});
}
function loadSurgery(surgeryList) {
	var dataSet = [];
	for ( var i in surgeryList) {
		var surgeryItems = [];
		surgeryItems.push(surgeryList[i].surgery_id);
		surgeryItems.push(surgeryList[i].surgery_name);
		surgeryItems.push(surgeryList[i].cost);
//		surgeryItems.push(convertMillisecondsToDate(physicianList[i].physicianBirthday));
//		surgeryItems.push(physicianList[i].account);
//		surgeryItems.push(physicianList[i].password);
//		if (primary[i].access_right == '11') {
//			patientItem.push('read/write');
//		} else if (primary[i].access_right == '10') {
//			patientItem.push('read');
//		}
		// patientItem.push(primary[i].access_right);
//		var editButton = generateEditButton(physicianList[i].physicianId);
//		var deleteButton = generateDeleteButton(physicianList[i].physicianId);
//		physicianItems.push(editButton+deleteButton);
		//physicianItems.push(deleteButton);
		dataSet.push(surgeryItems);
	}
	// console.log(dataSet);
	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [ {
			"title" : "Surgery Id",
			"class" : "center"
		},{
			"title" : "Surgery Name",
			"class" : "center"
		}, {
			"title" : "Cost (US Dollars)",
			"class" : "center"
		}]
	});
}