//random question json: {"sq_relation_id":2,"sq_id":2,"admin_account":"admin","answer":"Ryan"}
//sq list json: [{"sq_id":1,"sq_name":"Which year was your father born?","sq_step":1}]
var currentUserName;
var answer;
var sq_name;
$(document).ready(function() {
	getUserDetail();

});

function getUserDetail() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/security_question/getUserDetail",
		success : function(data) {
			currentUserName = data.username;
			console.log(currentUserName);
			loadQuestion(currentUserName);
		},
		dataType : "json",
	});
}

function loadQuestion(currentUserName){
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/sq_a_a/getRandomQuestion",
		data: "admin_account=" + currentUserName,
		success : function(data) {
			answer = data.answer;
			getQuestionNameById(data.sq_id);
		},
		dataType : "json",
	});
}

function getQuestionNameById(sq_id){
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/security_question/getById",
		data: "sq_id=" + sq_id,
		success : function(data) {
			sq_name = data[0].sq_name;
			$('#question').val(sq_name);
			submitListener();
		},
		dataType : "json",
	});
}

function submitListener(){
	$('#submit').click(function(){
		var input = $('#input').val();
		if(input == answer){
			jumpToIndex();
		}else{
			alert("wrong answer, please try again");
		}
	});
}

function jumpToIndex(){
	location.href ="index.html";
}