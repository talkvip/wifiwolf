<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
	<script type="text/javascript">

	$(document).ready(function() {
		$('#inputForm').validate({
			rules : {
				gatewayId : {
					required : true,
					maxlength : 45,
					minlength : 5
				},
				nodeName : {
					required : true,
					maxlength : 255,
					minlength : 5
				}
			},message:{}
		});
	});
</script>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li><a href="${ctx}/manage/nodeList">路由器列表</a></li>
			<li class="active"><a href="${ctx}/manage/nodeForm?id=${node.id}">路由器${not empty node.id?'修改':'添加'}</a></li>
		</ul>
		<br>
		<tags:message content="${message}" />
		<form:form id="inputForm" modelAttribute="node"
			action="${ctx}/manage/nodeSave" method="post" class="form-horizontal">
			<form:hidden path="id" />

			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="gatewayId">网关ID:</label>
				<div class="col-xs-6 col-sm-6">
						<form:input path="gatewayId" class="form-control"
							htmlEscape="false" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="nodeName">路由器名称:</label>
				<div class="col-xs-6 col-sm-6">
					<form:input path="nodeName" class="form-control"
							htmlEscape="false" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="authTypes">认证类型:</label>
				<div class="col-xs-6 col-sm-6">
					<form:checkboxes path="authTypes" items="${allAuthTypes}"
					itemLabel="authType" itemValue="id" element="div" htmlEscape="false"  />
				</div>
			</div>

			<div class="form-group">
				<div class="col-xs-offset-2 col-sm-offset-2 col-xs-6 col-sm-6">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp; <input id="btnCancel" class="btn"
						type="button" value="返 回" onclick="history.go(-1)" />
				</div>
			</div>
		</form:form>
<form id="inputForm2" class="form-horizontal" action="/wifiwolf/manage/nodeSave" method="post">
			<input id="id" name="id" type="hidden" value=""/>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="gatewayId">网关ID:</label>
				<div class="col-xs-6 col-sm-6">
						<input id="gatewayId" name="gatewayId" class="form-control" type="text" value=""/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="nodeName">路由器名称:</label>
				<div class="col-xs-6 col-sm-6">
					<input id="nodeName1" name="nodeName1" class="form-control" type="text" value=""/>
				</div>
			</div>
		</form>

	</div>
	

</body>
</html>