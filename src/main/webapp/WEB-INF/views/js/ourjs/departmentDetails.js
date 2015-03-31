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
	drawTableTitle();
	loadPhysician(departmentName);
	showAddingDrugTable();
	registerSurgeryInput();
});

function loadPhysician(departmentName) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/department/viewPhysicians",
		data: "department_name="+departmentName,
		success : function(data) {
			loadDepartmentPhysicians(data);
			//giveButtonLink();
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
//		physicianItems.push(physicianList[i].account);
//		physicianItems.push(physicianList[i].password);
//		if (primary[i].access_right == '11') {
//			patientItem.push('read/write');
//		} else if (primary[i].access_right == '10') {
//			patientItem.push('read');
//		}
		// patientItem.push(primary[i].access_right);
		//var editButton = generateEditButton(physicianList[i].physicianId);
		var deleteButton = generateDeleteButton(physicianList[i].physicianId);
		//physicianItems.push(editButton);
		physicianItems.push(deleteButton);
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
		},{
			"title" : "Delete",
			"class" : "center"
		}]
	});
}

function generateDeleteButton(physician_id) {
	var button = "<a name=\"delete\" id=\""
			+ physician_id
			+ "\" class=\"btn btn-danger btn-xs\" href=\"delete_relation_physician_department.html?physician_id="
			+physician_id+"\"><i class=\"fa fa-edit\"></i>Delete</a>";
	return button;
}

//function draw table title
function drawTableTitle(){
	var dname = departmentName.replace('_',' ');
	$('#table_title').html(dname+" Physicians List");
}

//function giveButtonLink(){
//	$('a[name=edit]').each(function(){
//		var physician_id = $(this).attr("id");
//		var $button = $(this);
//		$button.attr("href", "edit_physician.html?physician_id="+physician_id);	
//	})
//	$('a[name=delete]').each(function(){
//		var physician_id = $(this).attr("id");
//		var $button = $(this);
//		$button.attr("href", "delete_physician.html?physician_id="+physician_id);	
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
	return date.customFormat("#YYYY#-#MM#-#DD#");
} 

//Add new drugs

function showAddingDrugTable(){
	$("#btn-showAddingPhysicianTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}


//autocomplate
function registerSurgeryInput() {
	$("#physician_name").on(
			"keyup",
			function() {
				console.log("here user input name");
				var input = $('#physician_name').val();
				$.ajax({
					type : "GET",
					url : "/EMR_Admin/department_physician/autocomplete",
					data : 'input=' + input,
					success : function(data) {
						var suggestion = [];
						var sourceName = [];
						for ( var i in data) {
							suggestion.push(data[i].physicianId + "_"
									+ data[i].physicianName);
						}
						$("#physician_name").autocomplete({
							source : suggestion
						});
					},
					dataType : "json",
				});
			});
	registerPhysicianAddButton();
}

//add physician_department_relation
function registerPhysicianAddButton() {
	$('#addPhysician').on(
			'click',
			function() {
				var input = $('#physician_name').val();
				if(input.indexOf('_')!=-1){
					var physician_id = input.split('_')[0];
					var physician_name = input.split('_')[1];
					$.ajax({
						type : "GET",
						url : "/EMR_Admin/department_physician/add",
						data : 'physician_id=' + physician_id + '&physician_name='
								+ physician_name + '&departmentName=' + departmentName,
						success : function(data) {
							if(data=="addingToDbSuccess"){
								$('#addingResult').html("Success!");
								$('#addingResult').show();
								//loadAllDrug();
								setTimeout("location.reload(true);",1000);
								
							}
							else if(data=="d"){
								$('#addingResult').html("Failure! Something is wrong!");
								$('#addingResult').show();
//								getDepartmentNamesByPhysicianName(physician_name);
							}
						},
						dataType : "text",
					});
				}
				else{
					var physician_name = input;
					$.ajax({
						type : "GET",
						url : "/EMR_Admin/department_physician/addByPhysicianName",
						data : 'physician_name='
								+ physician_name + '&departmentName=' + departmentName,
						success : function(data) {
							if(data=="need_phyid"){
								$('#addingResult').html("please select a result from autocomplate dropdown box because system need physician id to avoid Duplicate Names");
								$('#addingResult').show();
								//loadAllDrug();
								//setTimeout("location.reload(true);",1000);
								
							}
							else if(data == "noSuchPhysician"){
								$('#addingResult').html("No such physician exists, please check the name again.");
								$('#addingResult').show();
							}
							else if(data=="exists"){
	//							$('#addingResult').html("Failure! Something is wrong!");
	//							$('#addingResult').show();
								getDepartmentNamesByPhysicianName(physician_name);
							}
						},
						dataType : "text",
					});
				}
			});
}
//getDepartmentNamesByPhysicianName
function getDepartmentNamesByPhysicianName(physician_name){
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/department_physician/getDepartmentNamesByPhysicianName",
		data : 'physician_name='+physician_name,
		success:function(data){
			var result = jQuery.isEmptyObject(data);
			if(!result){
				for(i in data)
					console.log(data.toString());
					console.log(i.toString());
				$('#addingResult').html("Failure! Found Physician named "
						+physician_name+" at <a href = \"departmentDetail.html?department_name="
						+data[i].department_name+"\">"+data[i].department_name+"</a>, please go to delete this physician from that department first.");
			}
			$('#addingResult').show();
		}
	});
}
