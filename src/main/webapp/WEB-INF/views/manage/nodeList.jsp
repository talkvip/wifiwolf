<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="pagePath" value="${ctx}/manage/nodeList" />
<html>
<head>
<title>路由器管理</title>
<script>
	$(document).ready(function() {
		$("#account-tab").addClass("active");
		$(".confirm").confirm({
			text : "确定要删除这个路由器？",
			title : "请确认",

			confirmButton : "确认",
			cancelButton : "取消"
		});
	});
</script>
</head>

<body>

	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/manage/nodeList">路由器列表</a></li>
			<li><a href="${ctx}/manage/nodeForm?id=${node.id}">路由器${not empty node.id?'修改':'添加'}</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<form:form id="searchForm" modelAttribute="node"
					action="${ctx}/manage/nodeList" method="post" class="form-inline"
					role="form">
					<div class="form-group">
						<label for="nodeDescription">路由器名：</label>
						<form:input type="text" name="search_nodeDescription"
							class="form-control" path="nodeDescription"></form:input>
					</div>
					<div class="form-group">
						<label for="gatewayId">网关ID：</label>
						<form:input type="text" name="search_gatewayId"
							class="form-control" path="gatewayId"></form:input>
					</div>&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="查询" />
				</form:form>
			</div>
		</div>
		<br>

		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>路由器名</th>
					<th>网关ID</th>
					<th>认证类型</th>
					<th>系统使用时间</th>
					<th>wifidog 使用时间</th>
					<th>系统使用内存</th>
					<th>上一次心跳IP</th>
					<th>上一次心跳时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${nodes}" var="node">
					<tr>
						<td>${node.nodeDescription}&nbsp;</td>
						<td>${node.gatewayId}&nbsp;</td>
						<td>${node.lastHeartbeatSysUptime}&nbsp;</td>
						<td>${node.lastHeartbeatWifidogUptime}&nbsp;</td>
						<td>${node.lastHeartbeatSysMemfree}&nbsp;</td>
						<td>${node.lastHeartbeatSysLoad}&nbsp;</td>
						<td>${node.lastHeartbeatIp}&nbsp;</td>
						<td>${node.lastHeartbeatTimestamp}&nbsp;</td>

						<td><a href="${ctx}/manage/nodeForm?id=${node.id}"

							id="editLink-${node.nodeDescription}">修改</a> <a
							href="${ctx}/manage/deleteNode?id=${node.id}"
							id="editLink-${node.nodeDescription}"
							onclick="return confirm('确定删除该路由器？')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
	<%@ include file="/WEB-INF/views/layouts/page.jsp"%>
</body>
</html>
