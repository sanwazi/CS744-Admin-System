$(document).ready(function() {
	loadMedicalStaff();
});

function loadMedicalStaff() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/medicalstaff/list",
		success : function(data) {
			loadMedicalStaffData(data);
			
		},
		dataType : "json",
	});
}

function loadMedicalStaffData(MedicalStaff) {
	var dataSet = [];
	for (var i = 0; i < MedicalStaff.length; i++) {
		var MedicalStaffs = [];
		MedicalStaffs.push(MedicalStaff[i].ms_name);
		MedicalStaffs.push(MedicalStaff[i].ms_account);
		MedicalStaffs.push(MedicalStaff[i].physician_name);
		dataSet.push(MedicalStaffs);
	}

	$('#dataTables-medical-staff').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Name",
			"class" : "center"
		},  {
			"title" : "Account",
			"class" : "center"
		}, {
			"title" : "Under Physician",
			"class" : "center"
		}]
	});
}