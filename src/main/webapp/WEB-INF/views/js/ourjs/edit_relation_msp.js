
$(document).ready(function() {
	var relation_msp_id = getUrlParameter("relation_msp_id");
	getRelationBasicContent(relation_msp_id);
	listeningUpdateButton();
});


function getRelationBasicContent(relation_msp_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/relation/getRelation_mspById",
		data : "relation_id=" + relation_msp_id,
		success : function(data) {
			//drug_id = data.drug_id;
			var physician_name = data.physician_name;
			var ms_name = data.ms_name;
			fillTextboxes(physician_name,ms_name);
		},
		dataType : "json",
	});
}
function listeningUpdateButton(){
	$("#update_relation_msp").on(
			'click',
			function(){
				var relation_msp_id = getUrlParameter("relation_msp_id");
				var physician_name = $("#physician_name").val();
				var ms_name = $("#medical_staff_name").val();
				
				if(physician_name == "" ){
					$('#addingResult').html("Input physician name please.");
					$('#addingResult').show();
					return;
				}
				
				if( ms_name == "" ){
					$('#addingResult').html("Input medical staff name please.");
					$('#addingResult').show();
					return;
				}
				
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/relation/updateMSPRelation",
					data: "relation_id=" + relation_msp_id + "&physician_name="+physician_name+ "&medical_staff_name="+ms_name,
					success : function(data){
						
						if(data=="s"){
							$('#addingResult').html("Success!");
							$('#addingResult').show();
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
	location.href ="Physician_Medical_Staff.html";
}

function fillTextboxes( physician, ms ){
	$("#physician_name").val(physician);
	$("#medical_staff_name").val(ms);
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