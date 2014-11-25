<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="en">
  <head>
    <title>WifiWolf登录页面</title>
	<link href="${ctx}/resources/css/signin.css" type="text/css" rel="stylesheet" />
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

	<div class="container">

      <form class="form-signin" role="form" action="${ctx}/user/login" method="post">
        <h2 class="form-signin-heading">WifiWolf</h2>
        <%
		String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if(error != null){
		%>
			<div class="alert alert-error input-medium controls">
				<button class="close" data-dismiss="alert">×</button>登录失败，请重试.
			</div>
		<%
		}
		%>
        <input class="form-control" type="text" id="username" name="username" placeholder="User ID" value="${username}" required autofocus/>
        <br>
        <input class="form-control" type="password" id="password" name="password" required/>
        <div class="checkbox">
          <label for="rememberMe">
            <input type="checkbox" id="rememberMe" name="rememberMe"> 记住我
          </label>
        </div>
        <c:if test="${isValidateCodeLogin}">
        <input type="text" id="validateCode" name="validateCode" maxlength="5" class="txt required" style="font-weight:bold;width:45px;"/>
<img src="${ctx}/resources/validationCode" onclick="$('.validateCodeRefresh').click();" class="mid validateCode"/>
<a href="javascript:" onclick="$('.validateCode').attr('src','${ctx}/resources/validationCode?'+new Date().getTime());" class="mid validateCodeRefresh">看不清</a>
</c:if>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
    </div>
  </body>
</html>
