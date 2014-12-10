<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>WifiWolf</title>
<meta name="decorator" content="default" />

</head>
<body>
	<div id="main" class="fill">

		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${ctx}/"><b>WifiWolf</b></a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li><p class="navbar-text">
								您好,
								<shiro:principal />
							</p></li>
						<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
						<li>&nbsp;</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->

			</div>
			<!-- /.container-fluid -->
		</nav>
		<div class="container-fluid fill" >
			<div id="content" class="row fill">
				<div id="left" class="col-xs-3 col-md-3">
					<div class="panel-group" id="menu" role="tablist" aria-multiselectable="true">

						<shiro:hasRole name="admin">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading1">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#menu" href="#collapsemenu1" aria-expanded="true"
											aria-controls="collapsemenu1" title="用户管理"> &nbsp;用户管理 </a>
									</h4>
								</div>
								<div id="collapsemenu1" class="panel-collapse collapse in" role="tabpanel"
									aria-labelledby="heading1">
									<div class="panel-body">
										<ul class="nav nav-list">
											<li><a href="${ctx}/manage/userList" target="mainFrame">&nbsp;用户列表</a></li>

										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading2">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#menu" href="#collapsemenu2" aria-expanded="false"
											aria-controls="collapsemenu2" title="认证设置"> &nbsp;认证设置管理</a>
									</h4>
								</div>
								<div id="collapsemenu2" class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading2">
									<div class="panel-body">
										<ul class="nav nav-list">
											<li><a href="${ctx}/manage/authType" target="mainFrame">&nbsp;认证规则设置</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading3">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#menu" href="#collapsemenu3" aria-expanded="false"
											aria-controls="collapsemenu3" title="路由器管理"> &nbsp;路由器管理 </a>
									</h4>
								</div>
								<div id="collapsemenu3" class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading3">
									<div class="panel-body">
										<ul class="nav nav-list">
											<li><a href="${ctx}/manage/nodeList" target="mainFrame">&nbsp;路由器列表</a></li>

										</ul>
									</div>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading4">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#menu" href="#collapsemenu4" aria-expanded="false"
											aria-controls="collapsemenu4" title="系统设置"> &nbsp;系统设置 </a>
									</h4>
								</div>
								<div id="collapsemenu4" class="panel-collapse collapse" role="tabpanel"
									aria-labelledby="heading4">
									<div class="panel-body">
										<ul class="nav nav-list">
											<li><a href="" target="mainFrame">&nbsp;活跃连接</a></li>
											<li><a href="" target="mainFrame">&nbsp;手机统计</a></li>
											<li><a href="" target="mainFrame">&nbsp;日志</a></li>
											<li><a href="" target="mainFrame">&nbsp;字典</a></li>
											<li><a href="manage/verifyCode" target="mainFrame">&nbsp;验证码</a></li>
										</ul>
									</div>
								</div>
							</div>
						</shiro:hasRole>
					</div>

				</div>
				<div id="right" class="col-xs-9 col-md-9  fill">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow: visible;" scrolling="yes"
						frameborder="no" width="100%" height="100%"></iframe>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="/WEB-INF/views/layouts/footer.jsp"%>
</body>
</html>
