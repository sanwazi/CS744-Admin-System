$(document).ready(function() {
	loadStatistics();
	loadAdminActivity();
});

function loadAdminActivity() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/log",
		success : function(data) {
			loadLogData(data);
		},
		dataType : "json",
	});
}

function loadLogData(logs) {
	var dataSet = [];
	for (var i = 0; i < logs.length; i++) {
		var log = [];
		log.push(logs[i].admin_name);
		var button = actionButton(logs[i].action);
		log.push(button);
		log.push(logs[i].table);
		log.push(convertMillisecondsToDate(logs[i].date));
		dataSet.push(log);
	}

	$('#dataTables-log').DataTable({
		"responsive" : true,
		"data" : dataSet,
		"columns" : [ {
			"title" : "Administrator",
			"class" : "center"
		}, {
			"title" : "Action",
			"class" : "center"
		}, {
			"title" : "Table",
			"class" : "center"
		}, {
			"title" : "Date",
			"class" : "center"
		} ]
	});
}

function actionButton(type) {
	if (type == "ADD") {
		return "<a   class=\"btn btn-primary btn-xs\" ><i class=\"fa fa-plus fa-lg\"></i> Add</a>";
	} else if (type == "DELETE") {
		return "<a   class=\"btn btn-warning btn-xs\" ><i class=\"fa fa-trash-o fa-lg\"></i> Delete</a>";
	} else if (type == "UPDATE") {
		return "<a class=\"btn btn-danger btn-xs\" ><i class=\"fa fa-pencil fa-lg\"></i> Update</a>";
	} else {
		return "<a class=\"btn btn-success btn-xs\" ><i class=\"fa fa-cloud-download fa-lg\"></i> Get data from pharmacy</a>";
	}
}
function loadStatistics() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/loadStatistics",
		success : function(data) {
			$('#adminNum').text(data[0]);
			$('#physicianNum').text(data[1]);
			$('#patientNum').text(data[2]);
			$('#medicalStaffNum').text(data[3]);
			$('#departmentNum').text(data[4]);
			$('#drugNum').text(data[5]);
			$('#surgeryNum').text(data[6]);
			$('#treatmentNum').text(data[7]);
			$('#diagnosticNum').text(data[8]);
		},
		dataType : "json",
	});
}
function convertMillisecondsToDate(input) {
	var date = new Date(input);
	return date.customFormat("#MM#-#DD#-#YYYY#   #hhh#:#mm#:#ss#");
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
