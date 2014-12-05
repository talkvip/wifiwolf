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
</head>

<body>
	<div class="container">
		<form class="form-signin" id="loginForm" action="${ctx}/wifi/login"
			method="post">
			<h2 class="form-signin-heading">Wifi Wolf 网络登录</h2>
			
			<!--c:if test="${authType.getAuthType() == PHONE_PASSWORD}">  -->
			<div class="control-group" id="userName">
				<label for="username" class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" name="userName" placeholder="用户名/手机号登录"
						value="${username}" class="input-medium required" />
				</div>
			</div>
			<!--  /c:if>-->
			<div class="control-group" id="phoneNum">
				<label for="password" class="control-label">手机号:</label>
				<div class="controls">
					<input type="text" name="phoneNum" class="input-medium required" />
				</div>
			</div>
			<div class="control-group" id="phoneCode">
				<label for="password" class="control-label">手机验证码:</label>
				<div class="controls">
					<input type="text" name="phoneCode" class="input-medium required" />
				</div>
			</div>
			<div class="control-group" id="userPassword">
				<label for="password" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" name="userPassword"
						class="input-medium required" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<label class="checkbox inline" for="rememberMe"> <input
						type="checkbox" name="rememberMe" /> 记住我
					</label> <input id="submit_btn" class="btn" type="submit" value="登录" />
				</div>
			</div>
		</form>
	</div>
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
