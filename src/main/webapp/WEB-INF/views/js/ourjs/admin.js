$(document).ready(function() {
	loadAdmin();
});

function loadAdmin() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/adminList",
		success : function(data) {
			console.log(data)
			loadAdminData(data);
		},
		dataType : "json",
	});
}

function loadAdminData(admin) {
	var dataSet = [];
	for (var i=0;i<admin.length;i++){
		var admins = [];
		admins.push(admin[i].adminId);
		admins.push(admin[i].adminAccount);
		admins.push(admin[i].adminPassword);

		dataSet.push(admins);
	}

	$('#dataTables-admin').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Admin id",
			"class" : "center"
		}, {
			"title" : "Admin account",
			"class" : "center"
		}, {
			"title" : "Admin password",
			"class" : "center"
		} ]
	});
}