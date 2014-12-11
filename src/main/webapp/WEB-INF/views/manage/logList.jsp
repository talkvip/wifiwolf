<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>管理日志</title>
<script>
	$(document).ready(function() {
		$("#systemTab").addClass("active");
		$("#sysLogTab").addClass("active");
		
	});
	function conditionChange(isConditionChanged) {
		$("#isConditionChanged").val(isConditionChanged);
	}
</script>
<style type="text/css">
td {word-wrap:break-word; word-break:break-all;}
</style>
</head>

<body>

	<div class="container-fluid">

		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/manage/listLog">管理日志</a></li>
		</ul>
		<br />
		<tags:message content="${message}" />
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<form:form id="searchForm" modelAttribute="log"
					action="${ctx}/manage/listLog" method="post" class="form-inline"
					role="form">
					<input type="hidden" id="targetPage" name="page" value=""/>
					<input type="hidden" id="isConditionChanged" name="isConditionChanged" value="false" />
					<div class="form-group">
						<label for="requestUri">Request Uri：</label>
						<form:input path="requestUri" 
							class="form-control" onchange="conditionChange(true)" />
					</div>
					<div class="form-group">
						<label for="nodeName">日志类型：</label> <form:select path="logType" class="form-control" onchange="conditionChange(true)">
						<form:option value="" label="" />
						<form:option value="1" label="访问" />
						<form:option value="2" label="异常" />
					</form:select>
					</div>&nbsp;&nbsp;
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="查询" />
				</form:form>
			</div>
		</div>
		<br>

		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>日志类型</th>
					<th>时间</th>
					<th>操作者</th>
					<th>远程地址</th>
					<th width="10%">User Agent</th>
					<th>Request Uri</th>
					<th>Mothod</th>
					<th width="40%">Params</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${logs}" var="log">
					<tr>
						<td>${ww:getLabel('log_type',log.logType,'') }</td>
						<td>${log.createTime}</td>
						<td>${log.createBy.username}</td>
						<td>${log.remoteAddr }</td>
						<td>${log.userAgent }</td>
						<td>${log.requestUri}</td>
						<td>${log.method}</td>
						<td>${log.params}</td>
						
					</tr>
					<c:if test="${not empty log.exception}">
					<tr>
					<td>异常</td><td colspan="7">${log.exception}</td>
					</tr></c:if>
				</c:forEach>
			</tbody>
		</table>
		

	</div>
	<%@ include file="/WEB-INF/views/layouts/page.jsp"%>
</body>
</html>
