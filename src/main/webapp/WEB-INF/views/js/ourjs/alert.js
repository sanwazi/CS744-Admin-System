
$(document).ready(function() {

	$.ajax({
		type : "GET",
		url : "/EMR_Admin/alertPatient",
		success : function(data) {
			$('#patient_without_emr').text(data);
		},
		dataType : "text",
	});
	
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/drug/getDrug",
		success : function(data) {
			var res = 0;
			for( var i = 0; i < data.length; i++  ){
				if(data[i].drug_status != "Enable" )
					res++;
			}
			$('#disable_drug').text(res);
		},
		dataType : "json",
	});
	
	
});
