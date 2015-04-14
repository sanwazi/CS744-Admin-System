//var urllink = "/EMR_Admin/views/pages/departmentDetails.html";
var urllink = "/EMR_Admin/views/pages/departmentDetail.html?department_name=";
var ul = document.getElementById("department_ul");

$(document).ready(function() {
	loadDepartment();
});

function loadDepartment(){
	$.ajax({
		type:"GET",
		url: "/EMR_Admin/getDepartment",
		success : function(data){
			var len = Object.keys(data).length;
			console.log(len);
			
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

