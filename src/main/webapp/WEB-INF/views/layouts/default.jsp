<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

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
	<sitemesh:body />

</body>
</html>
