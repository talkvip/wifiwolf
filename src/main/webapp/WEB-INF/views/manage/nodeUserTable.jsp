<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<title>路由器管理</title>
<script>
	$(document).ready(function() {
		
	});
</script>
</head>

<body>

	<div class="container-fluid">
		<p>路由器名称：${node.nodeDescription }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网关ID：${node.gatewayId }</p>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>手机</th>
					<th>邮箱</th>
					<th>WIFI-状态</th>
					<th>用户类型</th>
					<th>注册时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${nodeUsers}" var="nodeUser">
					<tr>
						<c:choose>
							<c:when test="${nodeUser.phone != null && nodeUser.phone ne ''}">
								<td>${nodeUser.phone}(${ww:getLabel('is_verified',nodeUser.isPhoneVerified,'') })&nbsp;</td>
							</c:when>
							<c:otherwise>
								<td>暂无&nbsp;</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${nodeUser.email != null && nodeUser.email ne ''}">
								<td>${nodeUser.email}(${ww:getLabel('is_verified',nodeUser.isEmailVerified,'') })&nbsp;</td>
							</c:when>
							<c:otherwise>
								<td>暂无&nbsp;</td>
							</c:otherwise>
						</c:choose>
						<td>${ww:getLabel('wifi_status',nodeUser.wifiStatus,'') }&nbsp;</td>
						<td>${ww:getLabel('user_type',nodeUser.userType,'') }&nbsp;</td>
						<td>${user.createTime}&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
</body>
</html>
