<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>WifiWolf</title>
<meta name="decorator" content="default" />
<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 7px 0 10px;}
		#header {margin:0 0 10px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} 
	</style>

</head>
<body>
	<div id="main">

		<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${ctx}/"><b>WifiWolf</b></a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
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
		<div class="container-fluid">
			<div id="content" class="row">
				<div id="left" class="col-xs-3 col-md-3">
					<div class="panel-group" id="menu" role="tablist"
						aria-multiselectable="true">
						<shiro:hasRole name="user">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="heading0">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#menu"
											href="#collapsemenu0" aria-expanded="true"
											aria-controls="collapsemenu0" title="个人信息"> &nbsp;个人信息 </a>
									</h4>
								</div>
								<div id="collapsemenu0" class="panel-collapse collapse in"
									role="tabpanel" aria-labelledby="heading0">
									<div class="panel-body">
										<ul class="nav nav-list">
											<li><a href="${ctx}/user/myinfo" target="mainFrame">&nbsp;个人信息</a></li>
<!-- 											<li><a href="" target="mainFrame">&nbsp;修改密码</a></li> -->

										</ul>
									</div>
								</div>
							</div>
						</shiro:hasRole>
						
					</div>

				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right" class="col-xs-9 col-md-9">
					<iframe id="mainFrame" name="mainFrame" src=""
						style="overflow: visible;" scrolling="yes" frameborder="no"
						width="100%" height="650"></iframe>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="/WEB-INF/views/layouts/footer.jsp"%>
</body>
</html>
