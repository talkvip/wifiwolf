<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>WifiWolf</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 7px 0 10px;}
		#header {margin:0 0 10px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} 
	</style>
	<script type="text/javascript"> 
		$(document).ready(function() {
			$("#menu a.menu").click(function(){
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
			});
		});
	</script>
</head>
<body>
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top">
	      <div class="navbar-inner">
	      	 <div class="brand">WifiWolf</div>
	         <div class="nav-collapse">
	           <ul id="menu" class="nav">
				 <c:set var="firstMenu" value="true"/>
				 
				 
	           </ul>
	           <ul class="nav pull-right">
			  	 
			  	 <li class="dropdown">
				    <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, <shiro:principal property="name"/></a>
				    <ul class="dropdown-menu">
				      <li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
				      <li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
				    </ul>
			  	 </li>
			  	 <li><a href="${ctx}/logout" title="退出登录">退出</a></li>
			  	 <li>&nbsp;</li>
	           </ul>
	         </div><!--/.nav-collapse -->
	      </div>
	    </div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left">
<%-- 					<iframe id="menuFrame" name="menuFrame" src="${ctx}/menu/" style="overflow:visible;" --%>
<!-- 						scrolling="yes" frameborder="no" width="100%" height="650"></iframe> -->
<div class="accordion" id="menu">
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapsemenu1" title="用户管理"><i class="icon-chevron-down"></i>&nbsp;用户管理</a>
		    </div>
		    <div id="collapsemenu1" class="accordion-body collapse in">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a href="" target="mainFrame" >&nbsp;用户列表</a></li>
						
					</ul>
				</div>
		    </div>
		</div>
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapsemenu1" title="认证设置"><i class="icon-chevron-right"></i>&nbsp;认证设置</a>
		    </div>
		    <div id="collapsemenu1" class="accordion-body collapse">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a href="" target="mainFrame" >&nbsp;认证页面设置</a></li>
						<li><a href="" target="mainFrame" >&nbsp;认证后页面设置</a></li>
					</ul>
				</div>
		    </div>
		</div>
				<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapsemenu1" title="路由器管理"><i class="icon-chevron-right"></i>&nbsp;路由器管理</a>
		    </div>
		    <div id="collapsemenu1" class="accordion-body collapse">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a href="" target="mainFrame" >&nbsp;路由器列表</a></li>
						
					</ul>
				</div>
		    </div>
		</div>
				<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapsemenu1" title="系统设置"><i class="icon-chevron-right"></i>&nbsp;系统设置</a>
		    </div>
		    <div id="collapsemenu1" class="accordion-body collapse">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a href="" target="mainFrame" >&nbsp;活跃连接</a></li>
						<li><a href="" target="mainFrame" >&nbsp;手机统计</a></li>
						<li><a href="" target="mainFrame" >&nbsp;认证类型浏览</a></li>
						<li><a href="" target="mainFrame" >&nbsp;日志</a></li>
						<li><a href="" target="mainFrame" >&nbsp;字典</a></li>
					</ul>
				</div>
		    </div>
		</div>
	</div>
				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;"
						scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
		    
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = "160"; // 左侧窗口大小
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs=getWindowSize().toString().split(",");
			$("#menuFrame, #mainFrame, #openClose").height((strs[0]<minHeight?minHeight:strs[0])-$("#header").height()-$("#footer").height()-32);
			$("#openClose").height($("#openClose").height()-5);
			if(strs[1]<minWidth){
				$("#main").css("width",minWidth-10);
				$("html,body").css({"overflow":"auto","overflow-x":"auto","overflow-y":"auto"});
			}else{
				$("#main").css("width","auto");
				$("html,body").css({"overflow":"hidden","overflow-x":"hidden","overflow-y":"hidden"});
			}
			$("#right").width($("#content").width()-$("#left").width()-$("#openClose").width()-5);
		}
	</script>
</body>
</html>