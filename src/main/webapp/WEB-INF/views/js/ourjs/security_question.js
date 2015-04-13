//session user detail: {"password":null,"username":"admin","authorities":[{"authority":"ROLE_ADMIN"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
//sq json: [{"sq_id":1,"sq_name":"Which year was your father born?","sq_step":1},{"sq_id":2,"sq_name":"What's your first pet's name?","sq_step":1},{"sq_id":3,"sq_name":"What's your mother's middle name?","sq_step":1},{"sq_id":4,"sq_name":"Who's your favorite movie star?","sq_step":1},{"sq_id":5,"sq_name":"What's your favorite game?","sq_step":2},{"sq_id":6,"sq_name":"What's your first girlfriend/boyfriend's last name?","sq_step":2},{"sq_id":7,"sq_name":"What's your father's favorite food?","sq_step":2},{"sq_id":8,"sq_name":"What's your mother's favorite drink?","sq_step":3},{"sq_id":9,"sq_name":"Where did you get your driver license?","sq_step":3},{"sq_id":10,"sq_name":"Who's your favorite singer?","sq_step":3}]

var currentUserName;

$(document).ready(function() {
	actions();
	getUserDetail();
	getAllQuestions();
});
function getUserDetail() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/security_question/getUserDetail",
		// data: "department_name="+departmentName,
		success : function(data) {
			currentUserName = data.username;
			console.log(currentUserName);
		},
		dataType : "json",
	});
}

function isFistTimeLogin(currentUserName){
	$.ajax({
		type : "GET",
		url: "/EMR_Admin/security_question/getcorrespondingQuestions",
		data: "admin_account=" + currentUserName,
		success : function(data){
			
		},
		dataType:"json",
	});
}

function getAllQuestions(){
	$.ajax({
		type : "GET",
		url: "/EMR_Admin/security_question/getAll",
		success : function(data){
			addOptions(data);
		},
		dataType:"json",
	});
}

function addOptions(sqs){
	for(var i in sqs){
		if(sqs[i].sq_step == "1"){
			var option = document.createElement("option");
			option.setAttribute("value",sqs[i].sq_id);
			option.innerHTML = sqs[i].sq_id+". "+sqs[i].sq_name;
			document.getElementById("step1_selects").appendChild(option);
		}else if(sqs[i].sq_step == "2"){
			var option = document.createElement("option");
			option.setAttribute("value",sqs[i].sq_id);
			option.innerHTML = sqs[i].sq_id+". "+sqs[i].sq_name;
			document.getElementById("step2_selects").appendChild(option);
		}else if(sqs[i].sq_step == "3"){
			var option = document.createElement("option");
			option.setAttribute("value",sqs[i].sq_id);
			option.innerHTML = sqs[i].sq_id+". "+sqs[i].sq_name;
			document.getElementById("step3_selects").appendChild(option);
		}
	}

}

//
function actions(){
	$('.next').click(function() {

		var nextId = $(this).parents('.tab-pane').next().attr("id");
		$('[href=#' + nextId + ']').tab('show');
		return false;

	});

	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {

		// update progress
		var step = $(e.target).data('step');
		var percent = (parseInt(step) / 3) * 100;

		$('.progress-bar').css({
			width : percent + '%'
		});
		$('.progress-bar').text("Step " + step + " of 3");

		// e.relatedTarget // previous tab

	});

	$('.first').click(function() {

		$('#myWizard a:first').tab('show')

	});
}
