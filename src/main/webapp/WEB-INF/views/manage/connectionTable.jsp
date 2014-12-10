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
					<th>IP</th>
					<th>MAC地址</th>
					<th>TOKEN令牌</th>
					<th>输入</th>
					<th>输出</th>
					<th>初始URL</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${connections}" var="connection">
					<tr>
						<td>${connection.ip}</td>
						<td>${connection.mac}</td>
						<td>${connection.token.token}</td>
						<td>${connection.incoming}</td>
						<td>${connection.outgoing}</td>
						<c:set var="url">${connection.token.originUrl}</c:set>
						<td title="${url}">${ww:shrimUrl(url)}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
</body>
</html>
