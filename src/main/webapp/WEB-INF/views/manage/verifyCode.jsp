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

		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/manage/verifyCode">验证码列表</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<br>

		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>手机号</th>
					<th>验证码</th>
					<th>状态</th>
					<th>创建时间</th>
					<th>验证时间</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${codes}" var="code">
					<tr>
						<td>${code.phoneNum}&nbsp;</td>
						<td>${code.verifyCode}&nbsp;</td>
						<td>${code.status}&nbsp;</td>
						<td>${code.createTime}&nbsp;</td>
						<td>${code.verifyTime}&nbsp;</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
</body>
</html>
