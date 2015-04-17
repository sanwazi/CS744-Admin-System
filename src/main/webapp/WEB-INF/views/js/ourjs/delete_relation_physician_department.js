//[{"physicianId":1,"physicianName":"milk","physicianGender":"male","physicianBirthday":675838800000,"account":"milk","password":"milk"}]
//{"relation_id":1,"physician_id":1,"physician_name":"milk","department_id":1,"department_name":"Burn_Center"}
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
var department_name;
$(document).ready(function() {
	//loadPatient();
	var physician_id = getUrlParameter("physician_id");
	//drug_name = getUrlParameter("drug_name");
	console.log(physician_id);
	getRelationBasicContent(physician_id);
	listeningDeleteButton();
	listeningCancelButton();
});


function getRelationBasicContent(physician_id) {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/department_physician/getRelationByPhysicianId",
		data : "physician_id=" + physician_id,
		success : function(data) {
			//drug_id = data.drug_id;
			//var relation_id = data.relation_id;
			var physician_id = data.physician_id;
			var physician_name = data.physician_name;
			var department_id = data.department_id;
			department_name = data.department_name;
			console.log(physician_name+" "+department_name);
			fillTextboxes(physician_id, physician_name, department_id, department_name );
		},
		dataType : "json",
	});
}
function listeningDeleteButton(){
	console.log("delete_relation");
	$("#delete_relation").on(
			'click',
			function(){
				var physician_id = $("#physician_id").val();
				//var drug_name = $("#drug_name").val();
				console.log("delete_relation button has been clicked");
				$.ajax({
					type: "GET",
					url : "/EMR_Admin/department_physician/deleteRelationByPhysicianId",
					data: "physician_id=" + physician_id,
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

//cancel listener
function listeningCancelButton(){
	console.log("btn-cancel");
	$("#btn-cancel").on(
			'click',
			function(){
				jump();
			});
}

function jump(){
	location.href ="departmentDetail.html?department_name="+department_name;
}

function fillTextboxes(physician_id, physician_name, department_id, department_name ){
	$("#physician_id").val(physician_id);
	$("#physician_name").val(physician_name);
	$("#department_id").val(department_id);
	$("#department_name").val(department_name);
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