<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>用户管理</title>
<script>
	$(document).ready(function() {
		$("#account-tab").addClass("active");
	});
</script>
</head>

<body>

	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/user/list">用户列表</a></li>
			<li><a href="${ctx}/user/form?id=${user.id}">用户${not empty user.id?'修改':'添加'}</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<form:form id="searchForm" modelAttribute="user" action="${ctx}/user/list" method="post" class="breadcrumb form-search">
					<label>登录名：</label> <form:input type="text" name="search_LIKE_username"
						class="input-small" path="username"></form:input>
					<label>邮件名：</label> <form:input type="text" name="search_EQ_email"
						class="input-small" path="email"></form:input>
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				</form:form>
			</div>
		</div>

		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>姓名</th>
					<th>手机</th>
					<th>邮箱</th>
					<th>WIFI-状态</th>
					<th>角色</th>
					<th>注册时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.username}&nbsp;</td>
						<td>${user.phone}(${user.isPhoneVerified == 1 ?'已验证':'未验证'})&nbsp;</td>
						<td>${user.email}(${user.isEmailVerified == 1 ?'已验证':'未验证'})&nbsp;</td>
						<td>${user.wifiStatus == 1 ?'可接入互联网':'不可使用网络'}&nbsp;</td>
						<td>${ww:getLabel('user_type',user.userType,'') }&nbsp;</td>
						<td>${user.createTime}&nbsp;</td>
						<td><a href="${ctx}/user/form?id=${user.id}"
							id="editLink-${user.username}">修改</a> <a
							href="${ctx}/user/delete/${user.id}"
							id="editLink-${user.username}">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="/WEB-INF/views/layouts/page.jsp"%>

	</div>
</body>
</html>