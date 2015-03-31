
//{"surgery_id":1,"surgery_name":"Hand Surgery","cost":0}

$(document).ready(function() {
	var surgery_id = getUrlParameter("surgery_id");
	console.log(surgery_id);
	getSurgeryBasicContent(surgery_id);
	listeningUpdateButton();
});


function getSurgeryBasicContent(surgery_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/surgery/getSurgeryById",
		data : "surgery_id=" + surgery_id,
		success : function(data) {
			//drug_id = data.drug_id;
			var surgery_name = data.surgery_name;
			var cost = data.cost;
			console.log(surgery_id+" "+surgery_name+cost);
			fillTextboxes(surgery_id, surgery_name,cost);
		},
		dataType : "json",
	});
}
function listeningUpdateButton(){
	console.log("update_surgery");
	$("#update_surgery").on(
			'click',
			function(){
				var surgery_id = $("#surgery_id").val();
				var surgery_name = $("#surgery_name").val();
				var cost = $("#surgery_cost").val();
				console.log("surgery_update button has been clicked");
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/surgery/updateSurgery",
					// /surgery/updateSurgery
					data: "surgery_id=" + surgery_id + "&surgery_name="+surgery_name+ "&cost="+cost,
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
	location.href ="surgery.html";
}

function fillTextboxes(surgery_id, surgery_name, cost){
	$("#surgery_id").val(surgery_id);
	$("#surgery_name").val(surgery_name);
	$("#surgery_cost").val(cost);
	console.log("filled the form");
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