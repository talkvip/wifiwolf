<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>认证方式设置</title>
<script>
	$(document).ready(function() {
		$("#account-tab").addClass("active");
	});
</script>
</head>

<body>

	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/manage/authTypeList">认证方式设置列表</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align:center">序号</th>
					<th style="text-align:center">注册要求</th>
					<th style="text-align:center">登录要求</th>
					<th style="text-align:center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${authTypes}" var="authType" varStatus="order">
					<tr>
						<td style="text-align:center">${order.count}&nbsp;</td>
						<td style="text-align:center">${ww:getLabel('register_type',authType.registerType,'') }&nbsp;</td>
						<td style="text-align:center">${ww:getLabel('auth_type',authType.authType,'') }&nbsp;</td>
						<td style="text-align:center"><a href="${ctx}/manage/authTypeStatus?id=${authType.id}"
							id="editLink-${order.count}">${ww:getLabel('auth_type_status', authType.status,'') }</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
