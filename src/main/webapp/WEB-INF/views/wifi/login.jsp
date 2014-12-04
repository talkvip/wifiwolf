<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Wifi Wolf 网络登录</title>
	<script src="${ctx}/resources/js/jquery-min.js"></script>
</head>

<body>
	<h1>Wifi Wolf 网络登录</h1>
	<form id="loginForm" action="${ctx}/wifi/login" method="post">
		<div class="control-group">
			<label for="username" class="control-label">用户名:</label>
			<div class="controls">
				<input type="text" id="username" name="userName" placeholder="用户名/手机号登录" value="${username}" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">手机号:</label>
			<div class="controls">
				<input type="text" id="phoneNum" name="phoneNum" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">手机验证码:</label>
			<div class="controls">
				<input type="text" id="phoneCode" name="phoneCode" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="userPassword" name="userPassword" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<label class="checkbox inline" for="rememberMe"> <input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我</label>
				<input id="submit_btn" class="btn" type="submit" value="登录"/>
			</div>
		</div>
	</form>
	
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
