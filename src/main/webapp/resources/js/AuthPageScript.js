function getQueryParameter(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return r[2];
	return null;
}

function startAuth() {

	var serverUrl = getQueryParameter("server_url");
	var authType = getQueryParameter("authType");

	if (serverUrl == null) {
		return;
	}
	if (authType == "PHONE") {
		if ($("#phoneNum").val() == "") {
			alert("手机号不能为空");
			return;
		}
	}
	if (authType == "PHONE_SMS") {
		if ($("#phoneNum").val() == "" || $("#phoneCode").val() == "") {
			alert("手机号或验证码不能为空");
			return;
		}
	}

	if (authType == "PHONE_PASSWORD") {
		if ($("#phoneNum").val() == "") {
			alert("手机号不能为空");
			return;
		}

		if ($("#userPassword").val() == "") {
			alert("密码不能为空");
			return;
		}
	}
	if (authType == "PHONE_PASSWORD_SMS") {
		if ($("#phoneNum").val() == "" || $("#phoneCode").val() == ""
				|| $("#userPassword").val() == "") {
			alert("手机号或验证码或密码不能为空");
			return;
		}
	}
	if ($("#agree").attr("checked") == false) {
		alert("请先同意用户协议");
		return;
	}

	$("#myform").attr("action", serverUrl);

	var gw_id = getQueryParameter("gw_id");
	var gw_address = getQueryParameter("gw_address");
	gw_address = gw_address == null ? "" : gw_address;
	var gw_port = getQueryParameter("gw_port");
	var url = getQueryParameter("url");
	$("#gw_id").val(gw_id);
	$("#wifidogHost").val(gw_address);
	$("#wifidogPort").val(gw_port);
	$("#originUrl").val(url);
	$("#myform").submit();

}

var SmsVerifyLock = false;
function sendSmsVerifyCode() {

	if (SmsVerifyLock == true) {
		alert("获取验证码太过频繁");
		return;
	}

	var serverUrl = getQueryParameter("server_url");

	if (serverUrl == null) {
		return;
	} else {
		serverUrl = serverUrl + "/sendVerifyCode";
	}

	var phoneNum = $.trim($("#phoneNum").val());
	var patten = new RegExp(/^1\d{10}$/);
	if (patten.test(phoneNum) == false) {
		alert("请输入 正确的手机号");
		return;
	}
	var url = serverUrl + "?phoneNum=" + phoneNum;
	 
	var script = document.createElement('script');  
	script.setAttribute('src', url);  
	document.getElementsByTagName('head')[0].appendChild(script);
}

$(document).ready(function() {
	$("#agree").attr("checked", true);
	var authType = getQueryParameter("authType");
	if (authType == "PHONE") {
		$("#userPassword").attr("disabled", true);
		$("#phoneCode").attr("disabled", true);
		$("#getVerifyCode").attr("disabled", true);
	}
	if (authType == "PHONE_SMS") {
		$("#userPassword").attr("disabled", true);
	}
	if (authType == "PHONE_PASSWORD") {
		$("#phoneCode").attr("disabled", true);
		$("#getVerifyCode").attr("disabled", true);
	}

});
