$(document).ready(function() {

	var relation_id = getUrlParameter("relation_id");
	getRelationBasicContent(relation_id);
	listeningDeleteButton();
});

function getRelationBasicContent(relation_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/relation/getRelationById",
		data : "relation_id=" + relation_id,
		success : function(data) {
			fillTextboxes(data);
		},
		dataType : "json",
	});
}
function listeningDeleteButton() {
	$("#delete_relation").on('click', function() {
		var relation_id = getUrlParameter("relation_id");
		$.ajax({
			type : "GET",
			url : "/EMR_Admin/relation/deleteRelation",
			data : "relation_id=" + relation_id,
			success : function(data) {

				if (data == "s") {
					$('#deletingResult').html("Success!");
					$('#deletingResult').show();
					setTimeout(jump, 1000);

				} else if (data == "d") {
					$('#deletingResult').html("Failure! Something is Wrong!");
					$('#deletingResult').show();
				}
			},
			dataType : "text",
		});
	});
}

function jump() {
	location.href = "Physician_Patient.html";
}

function fillTextboxes(relation) {
	$("#physician_name").val(relation.physician_name);
	$("#patient_name").val(relation.patient_name);
	$("#relation_type").val(relation.relation_type);
	$("#create_date").val(convertMillisecondsToDate(relation.start_date));
	if (relation.relation_type == "PRIMARY_CARE")
		$("#end_date").val("No ending date for PRIMARY CARE");
	else
		$("#end_date").val(convertMillisecondsToDate(relation.end_date));
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

function convertMillisecondsToDate(input) {
	var date = new Date(input);
	return date.customFormat("#YYYY#-#MM#-#DD#");
}
Date.prototype.customFormat = function(formatString) {
	var YYYY, YY, MMMM, MMM, MM, M, DDDD, DDD, DD, D, hhh, hh, h, mm, m, ss, s, ampm, AMPM, dMod, th;
	var dateObject = this;
	YY = ((YYYY = dateObject.getFullYear()) + "").slice(-2);
	MM = (M = dateObject.getMonth() + 1) < 10 ? ('0' + M) : M;
	MMM = (MMMM = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ][M - 1])
			.substring(0, 3);
	DD = (D = dateObject.getDate()) < 10 ? ('0' + D) : D;
	DDD = (DDDD = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday", "Saturday" ][dateObject.getDay()]).substring(0, 3);
	th = (D >= 10 && D <= 20) ? 'th' : ((dMod = D % 10) == 1) ? 'st'
			: (dMod == 2) ? 'nd' : (dMod == 3) ? 'rd' : 'th';
	formatString = formatString.replace("#YYYY#", YYYY).replace("#YY#", YY)
			.replace("#MMMM#", MMMM).replace("#MMM#", MMM).replace("#MM#", MM)
			.replace("#M#", M).replace("#DDDD#", DDDD).replace("#DDD#", DDD)
			.replace("#DD#", DD).replace("#D#", D).replace("#th#", th);

	h = (hhh = dateObject.getHours());
	if (h == 0)
		h = 24;
	if (h > 12)
		h -= 12;
	hh = h < 10 ? ('0' + h) : h;
	AMPM = (ampm = hhh < 12 ? 'am' : 'pm').toUpperCase();
	mm = (m = dateObject.getMinutes()) < 10 ? ('0' + m) : m;
	ss = (s = dateObject.getSeconds()) < 10 ? ('0' + s) : s;
	return formatString.replace("#hhh#", hhh).replace("#hh#", hh).replace(
			"#h#", h).replace("#mm#", mm).replace("#m#", m).replace("#ss#", ss)
			.replace("#s#", s).replace("#ampm#", ampm).replace("#AMPM#", AMPM);
}