<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<form id="loginForm" action='<s:url value="/user/login"></s:url>' method="post" class="form-horizontal">
	<input type="hidden" id="wifidogHost" name="wifidogHost"  value="${wifidogHost}" />
	<input type="hidden" id="wifidogPort" name="wifidogPort"  value="${wifidogPort}" />
		<div class="control-group">
			<label for="username" class="control-label">名称:</label>
			<div class="controls">
				<input type="text" id="username" name="username"  value="" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium required"/>
			</div>
		</div>
				
		<div class="control-group">
			<div class="controls">
				<label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我</label>
				<input id="submit_btn" class="btn btn-primary" type="submit" value="登录"/> 
			 	
			</div>
		</div>
	</form>
</body>
</html>