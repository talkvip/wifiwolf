
function getQueryParameter(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return r[2];
	return null;
}

function getValue(id) {
	var o = document.getElementById(id);
	if (o == null || o == undefined)
		return "";


	if (o.type == "checkbox") {
		return o.checked == true ? o.value : "";
	} else {
		return o.value;
	}

	return "";
}

function SmsVerifyMsg(code) {
	if (code == "-1") {
		alert("Mobile Number is empty!");
	} else if (code == "0") {
		alert("An SMS text with a Verification Code has been sent onto your mobile phone. Then enter the code once you received the SMS text.");
	} else if (code == "1") {
		alert("Mobile phone verification is not permitted. Please contact the administrator of this hotspot if you have any question.");
	} else if (code == "2") {
		alert("Mobile phone verification is not permitted. Please contact the administrator of this hotspot if you have any question.");
	} else if (code == "3") {
		alert("This phone number is in black list. Please contact the administrator of this hotspot if you have any question.");
	} else if (code == "4") {
		alert("Verification is being done too frequently. Please try it later.");
	} else if (code == "5") {
		alert("The times of verification has exceeded the daily limit.");
	} else if (code == "6") {
		alert("The hotspot is out of service.");
	} else if (code == "7") {
		alert("The hotspot has exceeded the quota of sending SMS. Please contact the administrator of this hotspot.");
	} else if (code == "8") {
		alert("Request is expired. Please reload this page.");
	} else if (code == "9") {
		alert("Please use the last Verification Code in your SMS texts.");
	} else if (code == "99") {
		alert("Sending text failed. Please make sure phone number is valid, or, contact the administrator of this hotspot.");
	} else if (code == "999") {
		alert("System error. Please try it again later or contact the administrator of this hotspot.");
	} else {
		alert("System error. Please try it again later or contact the administrator of this hotspot.");
	}
}





function startAuth() {

	var serverUrl = getQueryParameter("server_url");

	if (serverUrl == null) {
		return;
	}
	document.getElementById("myform").action=serverUrl;

	var gw_id = getQueryParameter("gw_id");
	var gw_address = getQueryParameter("gw_address");
	gw_address = gw_address == null ? "" : gw_address;
	var gw_port = getQueryParameter("gw_port");
	var url = getQueryParameter("url");

	document.getElementById("gw_id").value=gw_id;
	document.getElementById("wifidogHost").value=gw_address;
	document.getElementById("wifidogPort").value=gw_port;
	document.getElementById("originUrl").value=url;
	
	var _agree = getValue("agree");
	var _username = getValue("username");
	var _password = getValue("userPassword");
	var _phoneNum = getValue("phoneNum");
	var _phoneCode = getValue("phoneCode");

	document.getElementById("myform").submit();

}

var SmsVerifyLock = false;
function sendSmsVerifyCode(phoneNum,gw_id){
	

	if(SmsVerifyLock == true) {
		return;
	}

	var serverUrl = getQueryParameter("server_url");

	if (serverUrl == null) {
		return;
	}	else{
		serverUrl =serverUrl+"/phoneVerify";
	}
	
	if(phoneNum == "") {
		alert("请输入手机号");
		return;
	}
 
	var url = serverUrl + "?phoneNum="+phoneNum;
 
	var script = document.createElement('script');  
	script.setAttribute('src', url);  
	document.getElementsByTagName('head')[0].appendChild(script);  

	SmsVerifyLock = true;
	setTimeout("SmsVerifyLock=false", 60000);	
}


