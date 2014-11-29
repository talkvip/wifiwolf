<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<script>
	$(document).ready(function() {
		$("#account-tab").addClass("active");
		$(".confirm").confirm({
		    text: "确定要删除这个用户？",
		    title: "请确认",
		    
		    confirmButton: "确认",
		    cancelButton: "取消"
		});
	});
</script>
</head>

<body>

	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/manage/userList">用户列表</a></li>
			<li><a href="${ctx}/manage/userForm?id=${user.id}">用户${not empty user.id?'修改':'添加'}</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<form:form id="searchForm" modelAttribute="user" action="${ctx}/manage/userList" method="post"
					class="form-inline" role="form">
					<div class="form-group">
						<label for="username">登录名：</label>
						<form:input type="text" name="search_LIKE_username" class="form-control" path="username"></form:input>
					</div>
					<div class="form-group">
						<label for="email">邮件名：</label>
						<form:input type="text" name="search_EQ_email" class="form-control" path="email"></form:input>
					</div>&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
				</form:form>
			</div>
		</div>
		<br>

		<table id="contentTable" class="table table-striped table-bordered table-condensed">
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
						<td>${user.phone}(${ww:getLabel('is_verified',user.isPhoneVerified,'') })&nbsp;</td>
						<td>${user.email}(${ww:getLabel('is_verified',user.isEmailVerified,'') })&nbsp;</td>
						<td>${ww:getLabel('wifi_status',user.wifiStatus,'') }&nbsp;</td>
						<td>${ww:getLabel('user_type',user.userType,'') }&nbsp;</td>
						<td>${user.createTime}&nbsp;</td>
						<td><a href="${ctx}/manage/userForm?id=${user.id}"
							id="editLink-${user.username}">修改</a> <a
							href="${ctx}/manage/deleteUser?id=${user.id}"
							id="editLink-${user.username}" onclick="return confirm('确定删除该用户？')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		

	</div>
	<%@ include file="/WEB-INF/views/layouts/page.jsp"%>
</body>
</html>