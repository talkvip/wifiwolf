<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$().ready(function() {
		$("#inputForm").validate({
			rules : {
				gateway_id : {
					required : true,
					maxlength : 45,
					minlength : 5
				},
				node_name : {
					maxlength : 255,
					minlength : 5
				}
			}
		});
	});
</script>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li><a href="${ctx}/manage/nodeList">路由器列表</a></li>
			<li class="active"><a href="${ctx}/manage/nodeForm?id=${node.id}">用户${not empty node.id?'修改':'添加'}</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<form:form id="inputForm" modelAttribute="node"
			action="${ctx}/manage/nodeSave" method="post" class="form-horizontal">
			<form:hidden path="id" />

			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="gateway_id">网关ID:</label>
				<div class="col-xs-6 col-sm-6">
						<form:input path="gateway_id" class="form-control"
							htmlEscape="false" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="node_name">路由器名称:</label>
				<div class="col-xs-6 col-sm-6">
					<form:input path="node_name" class="form-control"
							htmlEscape="false" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="age">认证类型:</label>
				<div class="col-xs-6 col-sm-6">
					<form:checkboxes path="authTypes" items="${allAuthTypes}"
					itemLabel="auth_type" itemValue="id" htmlEscape="false" class="required" />
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

	</div>
</body>
</html>