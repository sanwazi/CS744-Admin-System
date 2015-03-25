var id;
var age;
var birthday;
var gender;
var name;
$(document).ready(function() {
	getPatientID();
	getPatientBasicInfo();
	bindSubmitAction();
})

function getPatientID() {
	id = getUrlParameter("id");
}

function getPatientBasicInfo(){
	$.ajax({
		type : "GET",
		url : "/emr/getPatient",
		data :"patientId=" + id,
		success : function(data) {
			age = data["patient_age"];
			gender = data["patient_gender"];
			name = data["patient_name"];
			birthday = data["patient_birthday"];
			$('#name').val(name);
			$('#birth').val( birthday);
			$('#age').val(age);
			$('#gender').val(gender);
		},
		dataType : "json",
	});
	
	
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

function bindSubmitAction() {
	$('#createEMRSubmit').click(
			function() {

				var race = $('#race').val();
				var occupation = $('#occupation').val();
				var address = $('#address').val();
				var height = $('#height').val();
				var weight = $('#weight').val();
				var blood_group = $('#blood_group').val();
				var vaccinations = $('#vaccinations').val();
				var diabetes = $('#diabetes').val();
				var allergies = $('#allergies').val();

				$.ajax({
					type : "GET",
					url : "/emr/create",
					data : "race=" + race + "&occupation=" + occupation
							+ "&address=" + address + "&height=" + height
							+ "&weight=" + weight + "&blood_group="
							+ blood_group + "&vaccinations=" + vaccinations
							+ "&diabetes=" + diabetes + "&allergies="
							+ allergies + "&patientId=" + id,
					success : function(data) {
						if( data == "success" ){
							alert(data);
							window.location.href="/pages/patients.html";
						}else if( data == "failure"){
							alert(data)
						}
					},
					dataType : "text",
				});
			});
}