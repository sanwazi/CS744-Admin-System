

$(document).ready(function() {

	var admin_id = getUrlParameter("admin_id");
	getAdminBasicContent(admin_id);
	listeningDeleteButton();
});


function getAdminBasicContent(admin_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/admin/getAdminById",
		data : "admin_id=" + admin_id,
		success : function(data) {
			var admin_account = data.adminAccount;
			$("#admin_account").val(admin_account);
		},
		dataType : "json",
	});
}
function listeningDeleteButton(){
	$("#delete_admin").on(
			'click',
			function(){
				var admin_id = getUrlParameter("admin_id");
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/admin/deleteAdmin",
					data: "admin_id=" + admin_id,
					success : function(data){
						
						if(data=="s"){
							$('#deletingResult').html("Success!");
							$('#deletingResult').show();
							//loadAllDrug();
							setTimeout(jump,1000);
							
						}
						else if(data=="d"){
							$('#deletingResult').html("Failure! Something is Wrong!");
							$('#deletingResult').show();
						}
					},
						dataType : "text",
				});
			}
	);
}



function jump(){
	location.href ="admin.html";
}

function getUrlParameter(sParam) {

	var sPageURL = window.location.search.substring(1);
	var sURLVariables = sPageURL.split('&');

	for (var i = 0; i < sURLVariables.length; i++) {

		var sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] == sParam) {

			return sParameterName[1];

		}
	}
}