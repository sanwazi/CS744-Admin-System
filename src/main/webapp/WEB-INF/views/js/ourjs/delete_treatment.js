

$(document).ready(function() {

	var treatment_id = getUrlParameter("treatment_id");
	getTreatmentBasicContent(treatment_id);
	listeningDeleteButton();
});


function getTreatmentBasicContent(treatment_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/treatment/getTreatment",
		data : "treatment_id=" + treatment_id,
		success : function(data) {
			var treatment_name = data.treatment_name;
			var cost = data.treatment_price;
			var can_do = data.can_medical_staff;
			fillTextboxes( treatment_name,cost,can_do);
		},
		dataType : "json",
	});
}
function listeningDeleteButton(){
	$("#delete_treatment").on(
			'click',
			function(){
				var treatment_id = getUrlParameter("treatment_id");
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/treatment/deleteTreatment",
					data: "treatment_id=" + treatment_id,
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
	location.href ="treatment.html";
}

function fillTextboxes( treatment_name,cost,can_do){
	$("#treatment_name").val(treatment_name);
	$("#treatment_cost").val(cost);
	$("#can_do").val(can_do);
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