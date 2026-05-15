$(document).ready(function(){
	$("#errorPreu").hide();
	$("#errorIdProducte").hide();
	
	
	$(".form-preu").submit(function(){

		var hasErrors = false
		
		var preu = $("#preu").val();
		if(preu == null || preu.trim() == "" || !isANumber(preu)) {
			$("#errorPreu").show();
			hasErrors = true;
		} else {
			$("#errorPreu").hide();
		}
		
		var idProducte = $("#idProducte").val()
		if(idProducte == null || idProducte.trim() == "" || !isAnIntegerNumber(idProducte)) {
			$("#errorIdProducte").show();
			hasErrors = true;
		} else {
			$("#errorIdProducte").hide();
		}

	if (hasErrors) {
			return false;
		}
    });

})

function isANumber(input){
	var stringRetornat = "";
	for (var i = 0; i < input.length; i++) {
		if (input[i] == ",") {
			stringRetornat += ".";
		} else {
			stringRetornat += input[i];
		}
	}
	if (isNaN(stringRetornat)) {
		return false;
	}
	return true;
}

function isAnIntegerNumber(input) {
	for (var i = 0; i < input.length; i++){
		if((input[i]) == "."){
			return false;
		}
	}
	if (isNaN(input)) {
		return false;
	}
	return true;
}
