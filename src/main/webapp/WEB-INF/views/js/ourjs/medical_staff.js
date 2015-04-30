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
		var names = MedicalStaff[i].ms_name.split(" ");
		MedicalStaffs.push(names[0]);
		if (names.length == 1)
			MedicalStaffs.push("None");
		else
			MedicalStaffs.push(names[1]);
		MedicalStaffs.push(MedicalStaff[i].ms_account);
		if (MedicalStaff[i].ssn == "")
			MedicalStaffs.push("None");
		else
			MedicalStaffs.push(MedicalStaff[i].ssn);
		MedicalStaffs.push(MedicalStaff[i].password);
		dataSet.push(MedicalStaffs);
	}

	$('#dataTables-medical-staff').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "First Name",
			"class" : "center"
		}, {
			"title" : "Last Name",
			"class" : "center"
		}, {
			"title" : "Account",
			"class" : "center"
		}, {
			"title" : "SSN",
			"class" : "center"
		}, {
			"title" : "Password",
			"class" : "center"
		} ]
	});
}