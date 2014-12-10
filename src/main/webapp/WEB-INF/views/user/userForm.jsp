<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#userInfoTab").addClass("active");
		$("#inputForm").validate({
			rules : {
				age : {
					max : 150,
					min : 1
				},
				email : {
					email : true
				},
				confirmNewPassword : {
					equalTo : "#plainPassword"
				},
				phone : {
					maxlength : 20
				}
			}
		});
	});</script>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/user/myinfo">用户信息修改</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<form:form id="inputForm" modelAttribute="user"
			action="${ctx}/user/save" method="post" class="form-horizontal">
			<form:hidden path="id" />

			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="username">登录名:</label>
				<div class="col-xs-6 col-sm-6">
					<shiro:hasRole name="admin">
						<form:input path="username" class="form-control"
							htmlEscape="false" />
					</shiro:hasRole>
					<shiro:lacksRole name="admin">
						<form:input path="username" class="form-control"
							htmlEscape="false"  disabled="true" />
					</shiro:lacksRole>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="sex">性别:</label>
				<div class="col-xs-6 col-sm-6">
					<form:select path="sex" class="form-control">
						<form:option value="" label="请选择" />
						<form:options items="${ww:getDictList('gender')}" itemLabel="name" itemValue="code" htmlEscape="false"/>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="age">年龄:</label>
				<div class="col-xs-6 col-sm-6">
					<form:input path="age" class="form-control" htmlEscape="false" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="plainPassword">密码:</label>
				<div class="col-xs-6 col-sm-6">
					<form:password path="plainPassword" class="form-control"
						htmlEscape="false" />
					<c:if test="${not empty user.id}">
						<span class="help-inline">若不修改密码，请留空。</span>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label"
					for="confirmNewPassword">确认密码:</label>
				<div class="col-xs-6 col-sm-6">
					<input id="confirmNewPassword" class="form-control"
						name="confirmNewPassword" type="password" value="" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="phone">电话号码:</label>
				<div class="col-xs-6 col-sm-6">
					<form:input path="phone" class="form-control" htmlEscape="false"
						 />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col-sm-2 control-label" for="email">邮箱:</label>
				<div class="col-xs-6 col-sm-6">
					<form:input path="email" class="form-control" htmlEscape="false" />
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