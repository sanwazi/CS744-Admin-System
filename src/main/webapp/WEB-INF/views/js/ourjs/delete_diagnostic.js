

$(document).ready(function() {

	var diagnostic_id = getUrlParameter("diagnostic_id");
	getdiagnosticBasicContent(diagnostic_id);
	listeningDeleteButton();
});


function getdiagnosticBasicContent(diagnostic_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/diagnostic/getdiagnosticById",
		data : "diagnostic_id=" + diagnostic_id,
		success : function(data) {
			var diagnostic_name = data.diagnostic_test_name;
			var cost = data.cost;
			fillTextboxes( diagnostic_name,cost);
		},
		dataType : "json",
	});
}
function listeningDeleteButton(){
	$("#delete_diagnostic").on(
			'click',
			function(){
				var diagnostic_id = getUrlParameter("diagnostic_id");
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/diagnostic/deleteDiagnostic",
					data: "diagnostic_id=" + diagnostic_id,
					success : function(data){
						var req = {
								table:"diagnostic",
								action:"delete",
								object:{
									id:getUrlParameter("diagnostic_id"),
									name:$("#diagnostic_name").val(),
									cost:$("#diagnostic_cost").val()
								}
						}
						console.log(req);
						if(data=="s"){
							
							$('#deletingResult').html("Success!");
							$('#deletingResult').show();
							//loadAllDrug();
//							setTimeout(jump,1000);
							
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
	location.href ="diagnostic.html";
}

function fillTextboxes( diagnostic_name,cost){
	$("#diagnostic_name").val(diagnostic_name);
	$("#diagnostic_cost").val(cost);
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