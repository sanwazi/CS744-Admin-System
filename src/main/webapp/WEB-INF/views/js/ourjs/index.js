//var urllink = "/EMR_Admin/views/pages/departmentDetails.html";
var urllink = "/EMR_Admin/views/pages/departmentDetail.html?department_name=";
var ul = document.getElementById("department_ul");
var nodes = [];
var res = [];
$(document).ready(function() {
	loadDepartment();
	loadStatistics();
	//loadTimeLine();
});

function getLog(){
	
	$.ajax({
		type:"GET",
		url: "/EMR_Admin/log",
		success : function(data){
			for( var i = 0; i < data.length; i++ ){
				var node = [];
				node.push("nodeId", i);
				node.push("title", data[i].admin_name);
				node.push("date", data[i].date);
				node.push("description", data[i].action + data[i].table);
				node.push("percent", i );
				nodes.push(node);
				console.log(nodes);
			}
		},
		dataType: "json",
	});
	console.log("nodes:" +nodes);

	res.push("nodes",nodes);
	console.log(res);
	return res;
}

function loadTimeLine(){
	$.UDNZTimeline({
	    "data_url": "/EMR_Admin/log",
	    "container": {
	        "id": "admin_activity_timeline"
	    }
	}).Draw();
}

function loadStatistics(){
	$.ajax({
		type:"GET",
		url: "/EMR_Admin/loadStatistics",
		success : function(data){
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
		dataType: "json",
	});
}

function loadDepartment(){
	$.ajax({
		type:"GET",
		url: "/EMR_Admin/getDepartment",
		success : function(data){
			var len = Object.keys(data).length;
			
			for(var i in data){
				addli(data[i].department_name)
			}
		},
		dataType: "json",
	});
}


function addli(text/*要加入的文字*/){

    var li= document.createElement("li");
   //var t=document.createTextNode(text);
//    console.log(text);
    var departmentName = text.replace('_',' ');
  //li.innerHTML="<a href=\"#\">"+text+"</a>";
    li.innerHTML="<a href=\""+urllink+text+"\">"+departmentName+"</a>";
   // li.attr('href','/EMR_Admin/views/pages/departmentDetails.html?department_name='+departmentName);
    //li.innerHTML="<a href=\""+urllink+departmentName+"\">"+text+"</a>";
  //li.appendChild(t);
    ul.appendChild(li);
    
}

