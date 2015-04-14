
//{"surgery_id":1,"surgery_name":"Hand Surgery","cost":0}

$(document).ready(function() {
	var surgery_id = getUrlParameter("surgery_id");
	getSurgeryBasicContent(surgery_id);
	listeningDeleteButton();
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
			fillTextboxes( surgery_name,cost);
		},
		dataType : "json",
	});
}
function listeningDeleteButton(){
	console.log("delete_surgery");
	$("#delete_surgery").on(
			'click',
			function(){
				var surgery_id = getUrlParameter("surgery_id");
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/surgery/deleteSurgery",
					data: "surgery_id=" + surgery_id,
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
	location.href ="surgery.html";
}

function fillTextboxes( surgery_name,cost){
	$("#surgery_name").val(surgery_name);
	$("#surgery_cost").val(cost);
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