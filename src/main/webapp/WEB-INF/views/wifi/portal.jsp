<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>欢迎页面</title>
</head>
<body>
	<div class="container">

		<div>
			<h1>欢迎使用WifiWolf无线网络</h1>
			<p>
				如果需要手动登出，请不要关闭页面。<a
					href="./logout?token=${token}&wifidogHost=${wifidogHost}&wifidogPort=${wifidogPort}">登出</a>
			</p>
		</div>

	</div>

</body>
</html>