<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>Wifi Wolf 网络登录</title>
<script src="${ctx}/resources/js/jquery-min.js"></script>
<link href="${ctx}/resources/css/signin.css" type="text/css"
	rel="stylesheet" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
	function sentSMS() {
		var phoneNum = $("#phoneNum").val();
		$('body').load('${ctx}/wifi/phoneVerify/?phoneNum=' + phoneNum);
	}
</script>
</head>

<body>
	<div class="container">
		<form class="form-signin" id="loginForm"
			action="${ctx}/wifi/login/?wifidogHost=${wifidogHost}&wifidogPort=${wifidogPort}&authType=${authType}&gw_id=${gw_id}"
			method="post">
			<h2 class="form-signin-heading">Wifi Wolf 网络登录</h2>

			<c:if test="${authType eq 'PHONE_PASSWORD'}">
				<div class="control-group">
					<label for="username" class="control-label">用户名:</label> <input
						class="form-control" type="text" id="username" name="username"
						placeholder="用户名/手机号登录" value="${username}" required autofocus />
					<br>
				</div>
			</c:if>
			<c:if test="${authType eq 'PHONE'}">
				<div class="control-group">
					<label for="password" class="control-label">手机号:</label> <input
						class="form-control" type="text" id="phoneNum" name="phoneNum"
						required autofocus /> <br>
				</div>
			</c:if>
			<c:if
				test="${authType eq 'PHONE_SMS' || authType eq 'PHONE_PASSWORD_SMS'}">
				<div class="control-group">
					<label for="password" class="control-label">手机号:</label>
					<div class="input-group">
						<input class="form-control" type="text" id="phoneNum"
							name="phoneNum" required autofocus /> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button" onclick="sentSMS()">点此获取手机验证码</button>
						</span>
					</div>
					<br>
				</div>
				<div class="control-group">
					<label for="password" class="control-label">手机验证码:</label> <input
						class="form-control" type="text" name="phoneCode" id="phoneCode"
						required autofocus /> <br>
				</div>
			</c:if>
			<c:if
				test="${authType eq 'PHONE_PASSWORD' || authType eq 'PHONE_PASSWORD_SMS'}">
				<div class="control-group">
					<label for="password" class="control-label">密码:</label> <input
						class="form-control" type="password" name="userPassword"
						id="userPassword" required autofocus /> <br>
				</div>
			</c:if>
			<div class="checkbox">
				<label for="rememberMe"> <input type="checkbox"
					id="rememberMe" name="rememberMe"> 记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>
	</div>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
