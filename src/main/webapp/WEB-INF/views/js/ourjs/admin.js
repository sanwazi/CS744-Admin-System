$(document).ready(function() {
	loadAdmin();
	addAdmin();
	showAddingAdminTable();
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
		var deleteButton = "<a name=\"admin_delete"
				+ "id=\""
				+ admin[i].adminId
				+ "\" class=\"btn btn-danger btn-xs\" href=\"delete_admin.html?admin_id="
				+ admin[i].adminId + "\""
				+ " ><i class=\"fa fa-trash-o fa-lg\"></i> Delete</a>";
		admins.push(deleteButton);
		dataSet.push(admins);
	}

	$('#dataTables-admin').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Admin account",
			"class" : "center"
		}, {
			"title" : "Delete",
			"class" : "center"
		} ]
	});
}

function addAdmin() {
	$('#addAdmin').on(
			'click',
			function() {
				var account = $('#adminAccount').val();
				var password1 = $('#password1').val();
				var password2 = $('#password2').val();

				if (account == "") {
					$('#addingResult').html("Input admin account please!");
					$('#addingResult').show();
					return;
				}
				if (password1 == "") {
					$('#addingResult').html("Input password please!");
					$('#addingResult').show();
					return;
				}

				if (password1 != password2) {
					$('#addingResult').html("You input different passwords.");
					$('#addingResult').show();
					return;
				}
				
				$.ajax({
					type : "GET",
					url : "/EMR_Admin/admin/addAdmin",
					data : 'account=' + account
							+ '&password=' + password1,
					success : function(data) {
						// TODO
						if (data == "s") {
							$('#addingResult').html("Success!");
							$('#addingResult').show();
							location.reload(true);
						} else if (data == "d") {
							$('#addingResult')
									.html("Failure! Duplicated Name!");
							$('#addingResult').show();
						}
					},
					dataType : "text",
				});
			});
}
function showAddingAdminTable() {
	$("#btn-showAddingAdminTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}