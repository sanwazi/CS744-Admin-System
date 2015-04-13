//session user detail: {"password":null,"username":"admin","authorities":[{"authority":"ROLE_ADMIN"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
//sq json: [{"sq_id":1,"sq_name":"Which year was your father born?","sq_step":1},{"sq_id":2,"sq_name":"What's your first pet's name?","sq_step":1},{"sq_id":3,"sq_name":"What's your mother's middle name?","sq_step":1},{"sq_id":4,"sq_name":"Who's your favorite movie star?","sq_step":1},{"sq_id":5,"sq_name":"What's your favorite game?","sq_step":2},{"sq_id":6,"sq_name":"What's your first girlfriend/boyfriend's last name?","sq_step":2},{"sq_id":7,"sq_name":"What's your father's favorite food?","sq_step":2},{"sq_id":8,"sq_name":"What's your mother's favorite drink?","sq_step":3},{"sq_id":9,"sq_name":"Where did you get your driver license?","sq_step":3},{"sq_id":10,"sq_name":"Who's your favorite singer?","sq_step":3}]

var currentUserName;
var step1_selects = document.getElementById("step1_selects");
var step2_selects = document.getElementById("step2_selects");
var step3_selects = document.getElementById("step3_selects");
var q_a_pairs = [];
//var questions = [];
//var inputs=[];
$(document).ready(function() {
	actions();
	getUserDetail();
});
function getUserDetail() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/security_question/getUserDetail",
		success : function(data) {
			currentUserName = data.username;
			console.log(currentUserName);
			isFistTimeLogin(currentUserName);
		},
		dataType : "json",
	});
}

function isFistTimeLogin(currentUserName){
	$.ajax({
		type : "GET",
		url: "/EMR_Admin/sq_a_a/isFistTimeLogin",
		data: "admin_account=" + currentUserName,
		success : function(data){
			if(data=="yes"){
				console.log("fist time login");
				getAllQuestions();
				submitCheck();
			}else{
				jumpToCorrespondingSQpage();
			}
		},
		dataType:"text",
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
			step1_selects.appendChild(option);
		}else if(sqs[i].sq_step == "2"){
			var option = document.createElement("option");
			option.setAttribute("value",sqs[i].sq_id);
			option.innerHTML = sqs[i].sq_id+". "+sqs[i].sq_name;
			step2_selects.appendChild(option);
		}else if(sqs[i].sq_step == "3"){
			var option = document.createElement("option");
			option.setAttribute("value",sqs[i].sq_id);
			option.innerHTML = sqs[i].sq_id+". "+sqs[i].sq_name;
			step3_selects.appendChild(option);
		}
	}

}
function submitCheck(){
	$('#submit').click(function(){
		var currentId = $(this).parents('.tab-pane').attr("id");
		var input = $('[id=input_'+currentId+']').val();
		var question = $('[id='+currentId+'_selects]').children("option").filter(":selected").val();
		q_a_pairs.push(question+"_"+input);
//		inputs.push(input);
//		questions.push(question);
//		console.log(inputs.length);
//		console.log(questions.length);
		if(q_a_pairs.length<3){
			alert("please fill all questions to submit");
			return;
		}else{
			submitNew();
		}
	});
}

function submitNew(){
	$.ajax({
		type : "GET",
		url: "/EMR_Admin/sq_a_a/addNew",
		data: "admin_account=" + currentUserName
			+"&sq_id1="+q_a_pairs[0].split('_')[0]+"&sq_id2="+q_a_pairs[1].split('_')[0]+"&sq_id3="+q_a_pairs[2].split('_')[0]
			+"&answer_1="+q_a_pairs[0].split('_')[1]+"&answer_2="+q_a_pairs[1].split('_')[1]+"&answer_3="+q_a_pairs[2].split('_')[1],
		success : function(data){
			console.log(data);
			if(data=="addingToDbSuccess"){
				alert("S");
				setTimeout(jumpToIndex,1000);
			}else if (data == "addingToDbfalse"){
				alert("F");
			}
		},
		dataType:"text",
	});
}

function jumpToIndex(){
	location.href ="index.html";
}

function jumpToCorrespondingSQpage(){
	location.href ="corresponding_sq.html";
}
//
function actions(){
	$('.next').click(function() {
		
		var nextId = $(this).parents('.tab-pane').next().attr("id");
		var currentId = $(this).parents('.tab-pane').attr("id");
		
		var input = $('[id=input_'+currentId+']').val();
		var question = $('[id='+currentId+'_selects]').children("option").filter(":selected").val();
		if(input==""||input==null){
			alert("please input answer to continue.");
			return;
		}
		else{
			q_a_pairs.push(question+"_"+input);
//			inputs.push(input);
//			questions.push(question);
			$('[href=#' + nextId + ']').tab('show');
			return false;
		}
		

	});
	$('.back').click(function(){
		
		var prevId = $(this).parents('.tab-pane').prev().attr("id");
		$('[href=#' + prevId + ']').tab('show');
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
