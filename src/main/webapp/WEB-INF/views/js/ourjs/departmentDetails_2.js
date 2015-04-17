var department_name;
$(document).ready(function() {
	//departmentNameInput();
	//loadPatient();
	department_name = getUrlParameter("department_name");
	console.log(department_name);
	checkDepartmentName(department_name);
	//console.log(departmentName);
//	
//	loadPhysician(departmentName);
//	showAddingDrugTable();
//	registerSurgeryInput();
});

function getUrlParameter(sParam) {

	var sPageURL = window.location.search.substring(1);
	console.log(sPageURL);
	var sURLVariables = sPageURL.split('&');

	for (var i = 0; i < sURLVariables.length; i++) {

		var sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] == sParam) {

			return sParameterName[1];

		}
	}
}

function checkDepartmentName(department_name) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/getDepartmentDetails",
		data: "department_name="+department_name,
		success : function(data) {
			var result = $.isEmptyObject(data);
			if(result){
				drawNotice(department_name);
				departmentNameInput();
			}
			else{
				console.log(data[0].department_name);
				loadDepartmentPhysicians(data[0].department_name);
				departmentNameInput();
			}
			
			//giveButtonLink();
			//loadTemporaryPatient(data.temporary);
			//updateButton();
		},
		dataType : "json",
	});
}
function drawNotice(department_name){
	$('#table_title').html("Department: "+department_name+" Dose not exist!");
}

function loadDepartmentPhysicians(department_name){
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/department/viewPhysicians",
		data: "department_name="+department_name,
		success : function(data) {
			drawTableTitle(department_name);
			drawTable(data);
			//giveButtonLink();
			//loadTemporaryPatient(data.temporary);
			//updateButton();
		},
		dataType : "json",
	});
}

function drawTableTitle(department_name){
//	var dname;
//	if(departmentName.indexOf('_')!=-1){
//		dname = departmentName.replace('_',' ');
//	}else{
//		dname = departmentName;
//	}
	$('#table_title').html(department_name+" Physicians List");
	$('#dataTable-panel').show('slow');
}

function drawTable(physicianList) {
	var dataSet = [];
	var physicianFirstName;
	var physicianLastName;
	for ( var i in physicianList) {
		var physicianItems = [];
		if(physicianList[i].physicianName.indexOf(' ')>-1){
			physicianFirstName = physicianList[i].physicianName.split(' ')[0];
			physicianLastName = physicianList[i].physicianName.split(' ')[1];
		}
		else{
			physicianFirstName = physicianList[i].physicianName;
			physicianLastName = "Bach";
		}
		console.log(physicianFirstName,physicianLastName);
		physicianItems.push(physicianFirstName);
		physicianItems.push(physicianLastName);
		physicianItems.push(physicianList[i].physicianGender);
		physicianItems.push(physicianList[i].specialty);
//		physicianItems.push(convertMillisecondsToDate(physicianList[i].physicianBirthday));
//		physicianItems.push(physicianList[i].account);
//		physicianItems.push(physicianList[i].password);
//		if (primary[i].access_right == '11') {
//			patientItem.push('read/write');
//		} else if (primary[i].access_right == '10') {
//			patientItem.push('read');
//		}
		// patientItem.push(primary[i].access_right);
		//var editButton = generateEditButton(physicianList[i].physicianId);
		//var deleteButton = generateDeleteButton(physicianList[i].physicianId);
		//physicianItems.push(editButton);
		//physicianItems.push(deleteButton);
		//physicianItems.push(deleteButton);
		dataSet.push(physicianItems);
	}
	// console.log(dataSet);
	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [ {
			"title" : "Physician First Name",
				"class" : "center"
		}, {
			"title" : "Physician Last Name",
			"class" : "center"
		}, {
			"title" : "Physician Gender",
			"class" : "center"
		}, {
			"title" : "Physician Specialty",
			"class" : "center"
		}]
	});
}

function departmentNameInput() {
	$("#input_search_department_name").on(
			"keyup",
			function() {
				console.log("here user input department_name");
				var input = $('#input_search_department_name').val();
				$.ajax({
					type : "GET",
					url : "/EMR_Admin/department/autocomplete",
					data : 'input=' + input,
					success : function(data) {
						var department_names = [];
						for(var i in data){
							department_names.push(data[i].department_name);
						}
//						var suggestion = [];
//						var sourceName = [];
//						for ( var i in data) {
//							suggestion.push(data[i].physicianId + "_"
//									+ data[i].physicianName);
//						}
						$("#input_search_department_name").autocomplete({
							source : department_names,
							select : function(event, ui) {
								var item = ui.item;
								$(this).val(item.value);
								console.log('item.value'+item.value);
								jumpToDepartmentDetail_2(item.value);
								//loadPhysician(item.value);
								return false;
							}
						});
					},
					dataType : "json",
				});
			});
	searchButtonListener();
}

function searchButtonListener(){
	$('#btn_search_department_name').on(
			'click',
			function(){
				var input = $('#input_search_department_name').val();
				console.log('input here'+input);
				jumpToDepartmentDetail_2(input);
			});
}
function jumpToDepartmentDetail_2(department_name){
	location.href="departmentDetail_2.html?department_name="+department_name;
}