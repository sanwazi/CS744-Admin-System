$(document).ready(function() {
	loadAdmin();
});

function loadAdmin() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/adminList",
		success : function(data) {
			loadAdminData(data);
		},
		dataType : "json",
	});
}

function loadAdminData(admin) {
	var dataSet = [];
	for (var i = 0; i < admin.length; i++) {
		var admins = [];
		admins.push(admin[i].adminAccount);
		dataSet.push(admins);
	}

	$('#dataTables-admin').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Admin account",
			"class" : "center"
		} ]
	});
}