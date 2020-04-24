function gameOver(){
	alert("Game over.");
	parent.location.href='www.google.com.au';
}

function validateBooking(){
	var userID = document.getElementById("userID");
	var emailaddress = document.getElementById("emailaddress").value;
	var regx = /@/;
	var securitycode = document.getElementById("securitycode").value;
	var securitymatch = document.getElementById("codematch").value;

	var resultStatus = true;
	var messageStr = "The following errors were detected: \n";

	if (userID == null || userID.value == "") {
		resultStatus = false;
		messageStr += "- Please enter User ID \n";
	}

	if (regx.test(emailaddress)){
		// do nothing
	}else{
		resultStatus = false;
		messageStr += "- Please enter valid e-mail. \n";
	}

	if(securitymatch != securitycode){
		resultStatus = false;
		messageStr += "- Please enter matching security code.";
	}

	if (!resultStatus) {
		alert(messageStr);
	}

	return resultStatus;
}

