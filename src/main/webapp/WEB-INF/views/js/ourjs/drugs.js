
$(document).ready(function() {
	// console.log(testjson);
	loadAllDrug();
	
});

function loadAllDrug() {
	$.ajax({
		type : "GET",
		url : "/EMR_Admin/drug/getDrug",

		success : function(data) {
				loadDrug(data);
				getDrugListFromPharmacyFromBackEndAndDisplay();
			// giveButtonLink();
		},
		dataType : "json",
	});
}
// Display drugs
function loadDrug(drugList) {
	console.log(drugList.length);
//	var drugNameCommercial;
//	var drugNameMedical;
	var dataSet = [];
	for ( var i in drugList) {
		var drugItems = [];
		drugItems.push(drugList[i].drug_name_medical);
		drugItems.push(drugList[i].drug_name_commercial);

		 drugItems.push(drugList[i].drug_unit);
		 drugItems.push(drugList[i].drug_dose);
		 drugItems.push(drugList[i].drug_side_effects);
		 drugItems.push(drugList[i].drug_usage);
		 var markedStatus = drawMarkForStatus(drugList[i].drug_status);
		 drugItems.push(markedStatus);

		console.log(drugItems.length);
		dataSet.push(drugItems);
		for(var i in dataSet){
			for(var j in dataSet[i]){
				if(!$.isEmptyObject(dataSet[i][j]) && dataSet[i][j].indexOf('<')==-1 && dataSet[i][j].length >=30){
					console.log(dataSet[i][j].substring(0,8));
					//dataSet[i][j]= dataSet[i][j].replace(dataSet[i][j].substring(30, dataSet[i][j].length-1),"...");
					dataSet[i][j] = "<div class=\"iffyTip wd100\">"+dataSet[i][j]+"</div>";
				}
			}
		}
		
	}
	console.log(dataSet.length);
	

	$('#dataTables-example').DataTable({
		responsive : true,
		data : dataSet,
		columns : [{
			"title" : "Drug Medical Name",
			"class" : "center"
		},{
			"title" : "Drug Commercial Name",
			"class" : "center"
		},{
			"title" : "Drug Unit",
			"class" : "center"
		},{
			"title" : "Drug Dose",
			"class" : "center"
		},{
			"title" : "Drug Side Effect",
			"class" : "center wd100"
		},{
			"title" : "Drug Usage",
			"class" : "center"
		},{
			"title" : "Drug Status",
			"class" : "center"
		}]
	});
	
	 $('#dataTables-example').on('mouseenter', ".iffyTip", function () {
	     var $this = $(this);
	     if (this.offsetWidth < this.scrollWidth && !$this.attr('title')) {
	         $this.tooltip({
	             title: $this.text(),
	             placement: "bottom"
	         });
	         $this.tooltip('show');
	     }
	 });
	
}
function drawMarkForStatus(drug_status){
	if(drug_status == "Enable"){
		var enableMark = "<a id=\""
			+ drug_status
			+ "\" class=\"btn btn-success btn-xs\" >"+drug_status+"</a>";
		return enableMark
	}else{
		var disableMark = "<a id=\""
			+ drug_status
			+ "\" class=\"btn btn-danger btn-xs\" >"+drug_status+"</a>";
		return disableMark
	}
	
}
// get drug from pharmacy
// function getDrugFromPharmacy(){
// $('#get_drug_from_pharmacy').click(function(){
// $.ajax({
// url:"http://138.49.101.83/Pharmacy/interface/drugList/all",
// dataType: 'jsonp', // Notice! JSONP <-- P (lowercase)
// success:function(json){
//		    	 
// // do stuff with json (in this case an array)
// alert("Success");
// },
// error:function(){
// alert("Error");
// }
// });
// location.reload();
// });
// }




// Add new drugs

function showAddingDrugTable(){
	$("#btn-showAddingDrugsTable").on('click', function() {
		$("#panelcontent").show("slow");
	});
}


// function addDrug() {
// $('#addDrug').on(
// 'click',
// function() {
// var drug_name_commercial = $('#drug_name_commercial').val();
// //var amount = $('#amount').val();
// $.ajax({
// type : "GET",
// url : "/EMR_Admin/drug/addDrug",
// data : 'drug_name_commercial=' + drug_name_commercial,
// success : function(data) {
// //TODO
// if(data=="s"){
// $('#addingResult').html("Success!");
// $('#addingResult').show();
// //loadAllDrug();
// setTimeout("location.reload(true);",1000);
//							
// }
// else if(data=="d"){
// $('#addingResult').html("Failure! Duplicated Name!");
// $('#addingResult').show();
// }
// // var row = '<tr>';
// // row += '<td>' + id_drug + '</td>';
// // row += '<td>' + amount + '</td>';
// // row += '</tr>';
// // var $rowDom = $.parseHTML(row);
// // $('#drugTableContent').append($rowDom);
// // $rowDom.show('slow');
// },
// dataType : "text",
// });
// });
// }
function addDrug(){
	var url1= "http://138.49.101.83/Pharmacy/interface/drugList/all";
	var url2 = "";
	$('#get_drug_from_pharmacy').click(function(){

		console.log("here3");
		var invocation = new XMLHttpRequest();
		var url = 'http://138.49.101.83/Pharmacy/interface/drugList/all';
		console.log("here3");
		
			console.log("here3");
		  if(invocation) {
			  console.log("here1");
		    invocation.open('GET', url, false);
		    //invocation.withCredentials = true;
		    invocation.onreadystatechange = 'Access-Control-Allow-Origin';
		    invocation.send(); 
		    console.log("here2");
		    addPharmacyDrugToDb(invocation.responseText);
		  }
		
		
//		var xhr = new XMLHttpRequest();
//		xhr.open('GET', 'http://138.49.101.83/Pharmacy/interface/drugList/all');
//		xhr.onreadystatechange = function () {
//		  if (this.status == 200 && this.readyState == 4) {
//		    console.log('response: ' + this.responseText);
//		  }
//		  console.log("here");
//		};
//		xhr.send();
		//location.reload();
	});
}

function getDrugListFromPharmacyFromBackEndAndDisplay(){
	$('#get_drug_from_pharmacy').click(function(){
		$.ajax({
			type:"GET",
			url:"/EMR_Admin/drug/getDrugListFromPharmacy",
			dataType: "text",
			success:function(data){
				if(data=="s"){
					location.reload();
				}else if (data == "addDrugsToDbF"){
					alert("Adding pharmacy drug list to database failed!");
				}
				else if(data == "drugsFromPharmacyEmpty"){
					alert("Drugs from pharmacy is empty!");
				}
			}
		});
	});
}



function addPharmacyDrugToDb(testjson){
	
		var drugsFromPharmacy = $.parseJSON(testjson);
		var dataSet = [];
		for(var i in drugsFromPharmacy){
			var drug = {
							drug_id:drugsFromPharmacy[i].id,
							drug_unique_id:drugsFromPharmacy[i].drugId,
							drug_lv:drugsFromPharmacy[i].drugLv,
							drug_name_medical:drugsFromPharmacy[i].drugNameMedical,
							drug_name_commercial:drugsFromPharmacy[i].drugNameCommercial,
							drug_unit:drugsFromPharmacy[i].drugUnit,
							drug_dose:drugsFromPharmacy[i].drugDose,
							drug_reaction:drugsFromPharmacy[i].drugReaction,
							drug_usage:drugsFromPharmacy[i].drugUsage,
							drug_status:drugsFromPharmacy[i].drugStatus,
						};
			dataSet.push(drug);
		}
		var dataSetJsonResult = JSON.stringify(dataSet);
		console.log("dataSetJsonResult"+dataSetJsonResult);
		$.ajax({
			type : "POST",
			url : "/EMR_Admin/drug/addAllDrugFromPharmacyToDb",
			contentType: "application/json; charset=utf-8",
			mimeType: 'application/json',
			data : dataSetJsonResult,
			success : function(data) {
				console.log(data);
				location.reload();
// //TODO
// if(data=="s"){e
// // $('#addingResult').html("Success!");
// // $('#addingResult').show();
// alert("Success!");
// //loadAllDrug();
// //setTimeout("location.reload(true);",1000);
//					
// }
// else if(data=="d"){
// alert("Failure!");
// // $('#addingResult').html("Failure! Duplicated Name!");
// // $('#addingResult').show();
// }
			},
			dataType : "text",
		});
	
}

// delete button listener
// function listenToDelete(){
// //var buttonId = "#"+drugId+"_delete";
// $('#dataTable-example :button').click(
// function(){
// console.log(""+"button"+" has been clicked");
// alert(""+"button"+" has been clicked");
// // $.ajax({
// // type: "GET",
// // url: "/EMR_Admin/drug/deleteDrug",
// // data: 'drug_id='+drugId,
// // success:function(data){
// // if(data=="s"){
// // $('#deleteResult').html("Success!");
// // $('#deleteResult').show();
// // //loadAllDrug();
// // setTimeout("location.reload(true);",1000);
// //
// // }
// // else if(data=="d"){
// // $('#deleteResult').html("Failure! Duplicated Name!");
// // $('#deleteResult').show();
// // }
// // },dataType :"text",}
// // );
//		
// }
// );
// }
// generate button
function generateEditButton(drugId) {
	var button = "<a name=\"edit\" id=\""
			+ drugId
			+ "\" class=\"btn btn-warning btn-xs\" href=\"edit_drug.html?drug_id="
			+drugId+"\"><i class=\"fa fa-edit\"></i>Edit</a>";
	return button;
}
function generateDeleteButton(drugId) {
	var button = "<a name=\"delete\" id=\""
			+ drugId
			+ "_delete\" class=\"btn btn-danger btn-xs\" href=\"delete_drug.html?drug_id="
			+drugId+"\"><i class=\"fa fa-edit\"></i>Delete</a>";
	return button;
}

// var modifyButton = "<button name=\"relation_modify\" id=\""
// + relations[i].relation_id
// + "\" class=\"btn btn-warning btn-xs\"><i class=\"fa fa-pencil-square-o
// fa-lg\"></i> Change</button>";
// relation.push(modifyButton);
// var deleteButton = "<button name=\"relation_delete\" id=\""
// + relations[i].relation_id
// + "\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash-o fa-lg\"></i>
// Delete</button>";
//
// function giveButtonLink(){
// $('a[name=edit]').each(function(){
// var drug_id = $(this).attr("id");
// var drug_name_commercial = $(this).attr("drug_name_commercial");
// var $button = $(this);
// $button.attr("href", "edit_drug.html?drug_id="+drug_id);
// })
// $('a[name=delete]').each(function(){
// var drug_id = $(this).attr("id");
// var $button = $(this);
// $button.attr("href", "delete_drug.html?drug_id="+drug_id);
// })
// }

// var url =
// "http://138.49.101.83/Pharmacy/interface/drugList/all?callback=myFunc";
//
// var success = function(data){
// var html = [];
// /* parse JSON */
// data = $.parseJSON(data);
//   
// console.log(data);
// /* loop through array */
// // $.each(data, function(index, d){
// // html.push("Manufacturer : ", d.Manufacturer, ", ",
// // "Sold : ", d.Sold, ", ",
// // "Month : ", d.Month, "<br>");
// // });
// //
// // $("#div391").html(html.join('')).css("background-color", "orange");
// };
//
// $.ajax({
// type: 'GET',
// url: url,
// data:{todo:"jsonp"},
// dataType: "jsonp",
// crossDomain: true,
// cache:false,
// success: success,
// error:function(jqXHR, textStatus, errorThrown){
// alert(errorThrown);
// }
// });
// });
// $.getJSON("http://138.49.101.83/Pharmacy/interface/drugList/all",
// function(data){
// alert(data);
// });
//$.ajax({
//type: 'GET',
////async: false,
////jsonpCallback: 'myJSON',
//contentType: "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
//dataType: 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
//crossDomain: true,
//url:url1,// Notice!
//															// JSONP
//															// <-- P
//															// (lowercase)
//success:function(json){
//	console.log("here1");
//	 console.log(json);
//	 //addPharmacyDrugToDb(json);
//     // do stuff with json (in this case an array)
//     alert("Success");
// },
// error:function(){
//     alert("Error");
// }      
//});