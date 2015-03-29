//[{"physicianId":1,"physicianName":"milk","physicianGender":"male","physicianBirthday":675838800000,"account":"milk","password":"milk"}]

Date.prototype.customFormat = function(formatString){
    var YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
    var dateObject = this;
    YY = ((YYYY=dateObject.getFullYear())+"").slice(-2);
    MM = (M=dateObject.getMonth()+1)<10?('0'+M):M;
    MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
    DD = (D=dateObject.getDate())<10?('0'+D):D;
    DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][dateObject.getDay()]).substring(0,3);
    th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
    formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);

    h=(hhh=dateObject.getHours());
    if (h==0) h=24;
    if (h>12) h-=12;
    hh = h<10?('0'+h):h;
    AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
    mm=(m=dateObject.getMinutes())<10?('0'+m):m;
    ss=(s=dateObject.getSeconds())<10?('0'+s):s;
    return formatString.replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
}

//token:     description:             example:
//	#YYYY#     4-digit year             1999
//	#YY#       2-digit year             99
//	#MMMM#     full month name          February
//	#MMM#      3-letter month name      Feb
//	#MM#       2-digit month number     02
//	#M#        month number             2
//	#DDDD#     full weekday name        Wednesday
//	#DDD#      3-letter weekday name    Wed
//	#DD#       2-digit day number       09
//	#D#        day number               9
//	#th#       day ordinal suffix       nd
//	#hhh#      military/24-based hour   17
//	#hh#       2-digit hour             05
//	#h#        hour                     5
//	#mm#       2-digit minute           07
//	#m#        minute                   7
//	#ss#       2-digit second           09
//	#s#        second                   9
//	#ampm#     "am" or "pm"             pm
//	#AMPM#     "AM" or "PM"             PM

var departmentName;
$(document).ready(function() {
	//loadPatient();
	departmentName = getUrlParameter("department_name");
	console.log(departmentName);
	loadPhysician(departmentName);
});

function loadPhysician(departmentName) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/department/viewPhysicians",
		data: "department_name="+departmentName,
		success : function(data) {
			loadDepartmentPhysicians(data);
			giveButtonLink();
			//loadTemporaryPatient(data.temporary);
			//updateButton();
		},
		dataType : "json",
	});
}
function loadDepartmentPhysicians(physicianList) {
	var dataSet = [];
	for ( var i in physicianList) {
		var physicianItems = [];
		physicianItems.push(physicianList[i].physicianName);
		physicianItems.push(physicianList[i].physicianGender);
		physicianItems.push(convertMillisecondsToDate(physicianList[i].physicianBirthday));
		physicianItems.push(physicianList[i].account);
		physicianItems.push(physicianList[i].password);
//		if (primary[i].access_right == '11') {
//			patientItem.push('read/write');
//		} else if (primary[i].access_right == '10') {
//			patientItem.push('read');
//		}
		// patientItem.push(primary[i].access_right);
		var editButton = generateEditButton(physicianList[i].physicianId);
		var deleteButton = generateDeleteButton(physicianList[i].physicianId);
		physicianItems.push(editButton+deleteButton);
		//physicianItems.push(deleteButton);
		dataSet.push(physicianItems);
	}
	// console.log(dataSet);
	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [ {
			"title" : "Physician Name"
		}, {
			"title" : "Physician Gender"
		}, {
			"title" : "Physician Birthday"
		}, {
			"title" : "Physician Account"
		},{
			"title" : "Physician Password"
		},{
			"title" : "Action",
			"class" : "center"
		} ]
	});
}
//function loadTemporaryPatient(temporary) {
//	// console.log(temporary)
//	var dataSet = [];
//	for ( var i in temporary) {
//		var patientItem = [];
//		patientItem.push(temporary[i].patient_name);
//		patientItem.push(temporary[i].patient_gender);
//		patientItem.push(convertTimestampToDate(temporary[i].patient_birthday));
//		patientItem.push(temporary[i].relation_type);
//		patientItem.push(temporary[i].start_date);
//		patientItem.push(temporary[i].end_date);
//		if (temporary[i].access_right == '11') {
//			patientItem.push('read/write');
//		} else if (temporary[i].access_right == '10') {
//			patientItem.push('read');
//		}
//		// patientItem.push(temporary[i].access_right);
//		patientItem.push(temporary[i].primary_physician_name)
//		var button = getEmrButton(temporary[i].patient_id);
//		console.log(button);
//		patientItem.push(button);
//		dataSet.push(patientItem);
//	}
//	// console.log(dataSet);
//	$('#dataTables-temporary').DataTable({
//		"responsive" : true,
//		"data" : dataSet,
//		"columns" : [ {
//			"title" : "Patient Name"
//		}, {
//			"title" : "Patient Gender"
//		}, {
//			"title" : "Patient Birthday"
//		}, {
//			"title" : "Relation Type",
//			"class" : "center"
//		}, {
//			"title" : "Start Date",
//			"class" : "center"
//		}, {
//			"title" : "End Date",
//			"class" : "center"
//		}, {
//			"title" : "Access Level",
//			"class" : "center"
//		}, {
//			"title" : "Primary Physician",
//			"class" : "center"
//		}, {
//			"title" : "Action",
//			"class" : "center"
//		} ]
//	});
//}
function generateEditButton(physicianId) {
	var button = "<a name=\"edit\" id=\""
			+ physicianId
			+ "\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i>Edit</a>";
	return button;
}
function generateDeleteButton(physicianId) {
	var button = "<a name=\"delete\" id=\""
			+ physicianId
			+ "\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i>Delete</a>";
	return button;
}

function giveButtonLink(){
	$('a[name=edit]').each(function(){
		var physician_id = $(this).attr("id");
		var $button = $(this);
		$button.attr("href", "edit_physician.html?physician_id="+physician_id);	
	})
	$('a[name=delete]').each(function(){
		var physician_id = $(this).attr("id");
		var $button = $(this);
		$button.attr("href", "delete_physician.html?physician_id="+physician_id);	
	})
}

//function updateButton() {
//	$('a[name=emr]').each(function() {
//		var patient_id = $(this).attr("id");
//		var $button = $(this);
//		$.ajax({
//			type : "GET",
//			url : "/emr/check",
//			data : "patientId=" + patient_id,
//			success : function(data) {
//				if (data['hasEmr'] == 'true') {
//					$button.attr("href", "emr.html?patient_id=" + patient_id);
//				} else if (data['hasEmr'] == "false") {
//					$button.attr('class', 'btn btn-danger btn-xs');
//					$button.attr('href', 'create_emr.html?id=' + patient_id);
//
//				}
//			},
//			dataType : "json",
//		});
//	})
//}
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
function convertMillisecondsToDate(input){
	var date = new Date(input);
	return date.customFormat("#YYYY#-#MM#-#DD#-");
} 

