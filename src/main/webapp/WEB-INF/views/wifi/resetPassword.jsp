<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>Wifi Wolf 重置密码</title>
<link href="${ctx}/resources/css/signin.css" type="text/css"
	rel="stylesheet" />

<script>
	$(document).ready(function() {
		$("#submitForm").click(function() {
			$("#resetForm").submit();
		});
		$("#resetForm").validate();
	});
</script>
</head>

<body>
	<div class="container">
		<form class="form-signin" id="resetForm"
			action="${ctx}/wifi/resetPassword" method="post">
			<h2 class="form-signin-heading">Wifi Wolf 重置密码</h2>
			<c:if test="${result eq 'true'}">
				<div class="alert alert-success input-medium controls">
					<button class="close" data-dismiss="alert">×</button>
					您的密码已经被重置并发送到手机，请使用新密码登录！
				</div>
			</c:if>
			<c:if test="${result eq 'false'}">
				<div class="alert alert-error input-medium controls">
					<button class="close" data-dismiss="alert">×</button>
					用户名或手机号不存在!
				</div>
			</c:if>
			<div class="control-group">
				<input class="form-control" type="text" id="username"
					name="username" value="${username}" placeHolder="用户名/手机号" required
					autofocus /> <br>
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="${ctx}/wifi/login/?wifidogHost=${wifidogHost}&wifidogPort=${wifidogPort}&registerType=${registerType}&authType=${authType}&gw_id=${gw_id}">返回登录</a>
			<input type="hidden" name="wifidogHost" value="${wifidogHost}" /> <input
				type="hidden" name="wifidogPort" value="${wifidogPort}" /> <input
				type="hidden" name="gw_id" value="${gw_id}" /><input type="hidden"
				name="authType" value="${authType}" />
			<button class="btn btn-lg btn-primary btn-block" id="submitForm"
				type="button">重置密码</button>
		</form>
	</div>
</body>
</html>
