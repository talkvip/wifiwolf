<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="pagePath" value="${ctx}/manage/userList" />
<html>
<head>
<title>用户管理</title>
<script>
	$(document).ready(function() {
		$("#cmsTab").addClass("active");
		$("#account-tab").addClass("active");
		$(".confirm").confirm({
		    text: "确定要删除这个用户？",
		    title: "请确认",
		    
		    confirmButton: "确认",
		    cancelButton: "取消"
		});
	});
	function conditionChange(isConditionChanged) {
		$("#isConditionChanged").val(isConditionChanged);
	}
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
				<form:form id="searchForm" modelAttribute="user"
					action="${ctx}/manage/userList" method="post" class="form-inline"
					role="form">
					<input type="hidden" id="targetPage" name="page" value="" />
					<input type="hidden" id="isConditionChanged"
						name="isConditionChanged" value="false" />
					<div class="form-group">
						<label for="phoneNum">手机号：</label>
						<form:input type="text" name="search_phoneNum"
							class="form-control" onchange="conditionChange(true)"
							path="phone"></form:input>
					</div>
					<div class="form-group">
						<label for="nodeName">路由器名：</label>
						<form:select path="registerNode.id" class="form-control"
							onchange="conditionChange(true)">
							<form:option value="" label="请选择" />
							<form:options items="${nodes}" itemLabel="nodeDescription"
								itemValue="id" htmlEscape="false" />
						</form:select>
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
					<th>登录名</th>
					<th>手机</th>
					<th>邮箱</th>
					<th>WIFI-状态</th>
					<th>用户类型</th>
					<th>路由器名</th>
					<th>注册时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.username}&nbsp;</td>
						<c:choose>
							<c:when test="${user.phone != null && user.phone ne ''}">
								<td>${user.phone}(${ww:getLabel('is_verified',user.isPhoneVerified,'') })&nbsp;</td>
							</c:when>
							<c:otherwise>
								<td>暂无&nbsp;</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${user.email != null && user.email ne ''}">
								<td>${user.email}(${ww:getLabel('is_verified',user.isEmailVerified,'') })&nbsp;</td>
							</c:when>
							<c:otherwise>
								<td>暂无&nbsp;</td>
							</c:otherwise>
						</c:choose>
						<td>${ww:getLabel('wifi_status',user.wifiStatus,'') }&nbsp;</td>
						<td>${ww:getLabel('user_type',user.userType,'') }&nbsp;</td>
						<c:set var="key">${user.id}</c:set>
						<td>&nbsp;${userRegisterNodeMap[key]}</td>
						<td>${user.createTime}&nbsp;</td>
						<td><a href="${ctx}/manage/userForm?id=${user.id}"
							id="editLink-${user.username}">修改</a> <a
							href="${ctx}/manage/deleteUser?id=${user.id}"
							id="editLink-${user.username}"
							onclick="return confirm('确定删除该用户？')">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
	<%@ include file="/WEB-INF/views/layouts/page.jsp"%>
</body>
</html>
