
//{"diagnostic_id":1,"diagnostic_name":"Hand diagnostic","cost":0}

$(document).ready(function() {
	var diagnostic_id = getUrlParameter("diagnostic_id");
	getdiagnosticBasicContent(diagnostic_id);
	listeningUpdateButton();
});


function getdiagnosticBasicContent(diagnostic_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/diagnostic/getdiagnosticById",
		data : "diagnostic_id=" + diagnostic_id,
		success : function(data) {
			//drug_id = data.drug_id;
			var diagnostic_name = data.diagnostic_test_name;
			var cost = data.cost;
			fillTextboxes(diagnostic_id, diagnostic_name,cost);
		},
		dataType : "json",
	});
}
function listeningUpdateButton(){
	$("#update_diagnostic").on(
			'click',
			function(){
				var diagnostic_id = $("#diagnostic_id").val();
				var diagnostic_name = $("#diagnostic_name").val();
				var cost = $("#diagnostic_cost").val();
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/diagnostic/updateDiagnostic",
					data: "diagnostic_id=" + diagnostic_id + "&diagnostic_name="+diagnostic_name+ "&cost="+cost,
					success : function(data){
						
						if(data=="s"){
							$('#addingResult').html("Success!");
							$('#addingResult').show();
							//loadAllDrug();
							setTimeout(jump,1000);
							
						}
						else if(data=="d"){
							$('#addingResult').html("Failure! Duplicated Name!");
							$('#addingResult').show();
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

function fillTextboxes(diagnostic_id, diagnostic_name, cost){
	$("#diagnostic_id").val(diagnostic_id);
	$("#diagnostic_name").val(diagnostic_name);
	$("#diagnostic_cost").val(cost);
}


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