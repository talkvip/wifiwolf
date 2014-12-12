<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="pagePath" value="${ctx}/manage/nodeList" />
<html>
<head>
<title>路由器管理</title>
<script>
	$(document).ready(function() {
		$("#nodeTab").addClass("active");
		$("#account-tab").addClass("active");
		$('#myModal').on('show.bs.modal', function (e) {
			
			$.get("${ctx}/manage/"+modelType+"/?nodeid="
					+ nodeid, function(data, status) {
				if(status=="success"){
					$("#tableDiv").empty();
					$("#tableDiv").append(data);
				}
			});
		});
	});
	var nodeid,modelType;
	function showModel(nodeId,type,title){
		modelType=type;
		nodeid=nodeId;
		$('#myModal').modal();
		$("#myModalLabel").html(title);
	}
	function conditionChange(isConditionChanged) {
		$("#isConditionChanged").val(isConditionChanged);
	}
	
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
				<form:form id="searchForm" modelAttribute="node" action="${ctx}/manage/nodeList" method="post"
					class="form-inline" role="form">
					<input type="hidden" id="targetPage" name="page" value=""/>
					<input type="hidden" id="isConditionChanged" name="isConditionChanged" value="false" />
					<div class="form-group">
						<label for="nodeDescription">路由器名：</label>
						<form:input type="text" name="search_nodeDescription" class="form-control" onchange="conditionChange(true)"
							path="nodeDescription"></form:input>
					</div>
					<div class="form-group">
						<label for="gatewayId">网关ID：</label>
						<form:input type="text" name="search_gatewayId" class="form-control" onchange="conditionChange(true)" path="gatewayId"></form:input>
					</div>&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
				</form:form>
			</div>
		</div>
		<br>

		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>路由器名</th>
					<th>网关ID</th>
					<th>系统运行时间</th>
					<th>wifidog运行时间</th>
					<th>系统空闲内存</th>
					<th>系统负载</th>
					<th>上一次心跳IP</th>
					<th>上一次心跳时间</th>
					<th>在线用户</th>
					<th>注册用户</th>
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
						<c:set var="key">${node.id}</c:set>
						<td><a href='javascript:void(0)' onclick='showModel(${key},"liveConnectionFragment", "在线用户")'>&nbsp;${onlineUserMap[key]}</a></td>
						<td><a href='javascript:void(0)' onclick='showModel(${key},"liveNodeUserFragment", "注册用户")'>&nbsp;${registeredUserMap[key]}</a></td>
						<td><a href="${ctx}/manage/nodeForm?id=${node.id}" id="editLink-${node.nodeDescription}">修改</a>
							<a href="${ctx}/manage/deleteNode?id=${node.id}" id="editLink-${node.nodeDescription}"
							onclick="return confirm('确定删除该路由器？')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
	<%@ include file="/WEB-INF/views/layouts/page.jsp"%>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body" id="tableDiv"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
