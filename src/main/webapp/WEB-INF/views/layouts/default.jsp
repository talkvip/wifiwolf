<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html style="overflow-x: hidden; overflow-y: auto;">
<head>
<title><sitemesh:title /></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script src="${ctx}/resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jquery-ui.js" type="text/javascript"></script>
<script src="${ctx}/resources/js/jquery.i18n.properties.js" type="text/javascript"></script>
<link href="${ctx}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/resources/css/bootstrap-theme.min.css" type="text/css" rel="stylesheet" />
<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 7px 0 10px;}
		#header {margin:0 0 10px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} 
	</style>
<sitemesh:head/>
</head>
<body>
	<sitemesh:body />
	
</body>
</html>
