<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
	<h1>用户列表</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success">
			<button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>

	<div class="row">
		<div class="offset4">
			<a href="${ctx}//user/add"> <input type="submit" name="add_btn"
				value="添加新用户">
			</a>
		</div>
	</div>

	<div class="row">
		<div class="offset4">
			<form class="form-search" action="#">
				<label>登录名：</label> <input type="text" name="search_LIKE_loginName"
					class="input-small" value="${param.search_LIKE_loginName}">
				<label>邮件名：</label> <input type="text" name="search_EQ_email"
					class="input-small" value="${param.search_EQ_email}">
				<button type="submit" class="btn" id="search_btn">Search</button>
			</form>
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
					<td>${user.userType == 1 ?'管理员':'用户'}&nbsp;</td>
					<td>${user.createTime}&nbsp;</td>
					<td><a href="${ctx}/user/update/${user.id}"
						id="editLink-${user.username}">修改</a> <a
						href="${ctx}/user/delete/${user.id}"
						id="editLink-${user.username}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="/WEB-INF/views/layouts/page.jsp"%>
</body>
</html>