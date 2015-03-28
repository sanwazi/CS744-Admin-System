var urllink = "/EMR_Admin/department/viewPhysicians?department_name=";

$(document).ready(function() {
	loadDepartment();
});

function loadDepartment(){
	$.ajax({
		type:"GET",
		url: "/EMR_Admin/getDepartment",
		success : function(data){
			var len = Object.keys(data).length;
			for(var i in data){
				addli(len, data[i].department_name)
			}
		},
		dataType: "json",
	});
}


function addli(n/*需要加入的li的个数*/,text/*要加入的文字*/){
	
    var ul = document.getElementById("department_ul");
    for(var i=0;i<n;i++){
        var li= document.createElement("li");
//        var t=document.createTextNode(text);
        console.log(text);
        var departmentName = text;
      //li.innerHTML="<a href=\"#\">"+text+"</a>";
        li.innerHTML="<a href=\""+urllink+departmentName+"\">"+text+"</a>";
      //li.appendChild(t);
        ul.appendChild(li);
    }
}

