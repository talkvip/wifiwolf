<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn" style="overflow-x: hidden; overflow-y: auto;">
<head>
<title><sitemesh:title /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${ctx}/resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${ctx}/resources/js/jquery-ui.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jquery.i18n.properties.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/additional-methods.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/localization/messages_zh.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jBox.min.js" type="text/javascript"></script>
<link href="${ctx}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/css/custom.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/css/jBox.css" type="text/css" rel="stylesheet" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<sitemesh:head />
</head>
<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${ctx}/">WifiWolf</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
			
				<ul class="nav navbar-nav">
				<shiro:hasRole name="admin">
					<li id="nodeTab"><a href="${ctx}/manage/nodeList">路由器管理</a></li>
					<li id="authTab"><a href="${ctx}/manage/authType">认证管理</a></li>
					<li id="cmsTab"><a href="${ctx}/manage/userList">客户关系管理</a></li>
					<li id="systemTab" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">系统 <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li id="phoneStatTab"><a href="#">手机统计</a></li>
							<li id="sysLogTab"><a href="#">日志</a></li>
							<li id="verifyCodeTab"><a href="${ctx}/manage/verifyCode">验证码</a></li>
						</ul></li>
						</shiro:hasRole>
						<shiro:hasRole name="user">
						<li id="userInfoTab"><a href="${ctx}/user/myinfo">个人信息</a></li>
						</shiro:hasRole>
				</ul>
				

				<ul class="nav navbar-nav navbar-right">
						<li><p class="navbar-text">
								您好,
								<shiro:principal />
							</p></li>
						<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
						<li>&nbsp;</li>
					</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<sitemesh:body />


	<!-- <div class="footer">
		<div class="container">
			<p class="text-muted">Place sticky footer content here.</p>
		</div>
	</div> -->
	<div id="footer" class="navbar-fixed-bottom">
	Copyright &copy; 2014-2014 <a href="http://dasherz.net/">DasHerz Studio</a>
</div>
	


</body>
</html>
