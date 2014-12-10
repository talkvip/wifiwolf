<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>认证方式设置</title>
<script>
$(document).ready(function() {
	$("#authTab").addClass("active");
						$("#form").validate({
							rules : {
								authPageCustomizeUrl : {
									url : true
								},
								portalPageCustomizeUrl : {
									url : true
								}
							},
							message : {}
						});

						var initModal = function(template) {
							$("a.thumbnail").each(function() {
								$(this).css("background-color", "white");
							});
							var selectedItem = $("#" + template).val();
							$("#" + selectedItem + "Img").css(
									"background-color", "blue");
						};
						var itemClicked = function(template, item) {
							$("a.thumbnail").each(function() {
								$(this).css("background-color", "white");
							});
							$(item).css("background-color", "blue");
							var str = $(item).attr("id");
							str = str.substr(0, str.length - 3);
							$("#" + template).val(str);
						};

						$("#authPageTemplates").on("shown.bs.modal",
								function() {
									initModal('authTemplate')
								});
						$("#portalPageTemplates").on("shown.bs.modal",
								function() {
									initModal('portalTemplate')
								});
						$("div#authPageTemplates a.thumbnail").click(
								function() {
									itemClicked("authTemplate", this)
								});
						$("div#portalPageTemplates a.thumbnail").click(
								function() {
									itemClicked("portalTemplate", this)
								});
						$("#inputHtmlAuthPage").click(
								function() {
									$("#customizeHtml").val(
											$("#authPageCustomizeHtml").val());
									$("#customizeHtmlType").val("authPage");
									$("#customizeHtmlModel").modal();
								});
						$("#intputHtmlPortalPage")
								.click(
										function() {
											$("#customizeHtml")
													.val(
															$(
																	"#portalPageCustomizeHtml")
																	.val());
											$("#customizeHtmlType").val(
													"portalPage");
											$("#customizeHtmlModel").modal();
										});
						$("#saveCustomizeHtml")
								.click(
										function() {
											var customizeHtmlType = $(
													"#customizeHtmlType").val();
											$(
													"#" + customizeHtmlType
															+ "CustomizeHtml")
													.val(
															$("#customizeHtml")
																	.val());
											$("#customizeHtmlModel").modal(
													'toggle');
										});

						$("#submitForm")
								.click(
										function() {
											if ($("#authPageTypeRadios2").prop(
													"checked") == true
													&& $(
															"#authPageCustomizeUrl")
															.val() == "") {
												alert("自定义认证页面URL不能为空！");
												$("#authPageCustomizeUrl")
														.focus();
												return;
											}
											if ($("#authPageTypeRadios3").prop(
													"checked") == true
													&& $(
															"#authPageCustomizeHtml")
															.val() == "") {
												alert("自定义认证页面HTML内容不能为空！");
												$("#authPageCustomizeHtml")
														.focus();
												return;
											}

											if ($("#portalPageTypeRadios3")
													.prop("checked") == true
													&& $(
															"#portalPageCustomizeUrl")
															.val() == "") {
												alert("自定义认证后页面URL不能为空！");
												return;
											}
											if ($("#portalPageTypeRadios4")
													.prop("checked") == true
													&& $(
															"#portalPageCustomizeHtml")
															.val() == "") {
												alert("自定义认证后页面HTML内容不能为空！");
												return;
											}
											console.log("submiting form");
											$("#form").submit();
										});
					});
</script>
</head>

<body>

	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/manage/authType">认证方式设置列表</a></li>
		</ul>
		<br />
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<tags:message content="${message}" />
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">认证方式设置</h3>
						</div>
						<div class="panel-body">


							<table id="contentTable" class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th style="text-align: center">序号</th>
										<th style="text-align: center">注册要求</th>
										<th style="text-align: center">登录要求</th>
										<th style="text-align: center">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${authTypes}" var="authType" varStatus="order">
										<tr <c:if test="${authType.status eq 1}">class="success"</c:if> >
											<td style="text-align: center">${order.count}&nbsp;</td>
											<td style="text-align: center">${ww:getLabel('register_type',authType.registerType,'') }&nbsp;</td>
											<td style="text-align: center">${ww:getLabel('auth_type',authType.authType,'') }&nbsp;</td>
											<td style="text-align: center"><a
												href="${ctx}/manage/saveAuthType?id=${authType.id}" id="editLink-${order.count}">${ww:getLabel('auth_type_status', authType.status,'') }</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<form id="form" class="form-horizontal" method="post" action="saveAuthSetting" role="form">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">认证页面设置</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-3">
								<div class="radio">
									<label> <input type="radio" name="authPageType" id="authPageTypeRadios1"
										<c:if test="${authPage.pageTemplate!=null}">checked="checked"</c:if> value="useTemplate">
										启用<b>页面模版</b>
									</label>
								</div>
							</div>
							<div class="col-sm-3">
								<select id="authTemplate" name="authTemplate" class="form-control">
									<c:forEach items="${authPageTemplates }" var="authPageTemplate" varStatus="i">
										<option value="authTemplate${authPageTemplate.id}"
											<c:if test="${authPage.pageTemplate!=null && authPageTemplate.id==authPage.pageTemplate.id}">selected="selected"</c:if>>${authPageTemplate.templateName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-1">
								<button type="button" class="btn btn-default" data-toggle="modal" id="authPageTemplateBtn"
									data-target="#authPageTemplates">效果</button>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<div class="radio">
									<label> <input type="radio" name="authPageType" id="authPageTypeRadios2"
										<c:if test="${authPage.customizeUrl!=null}">checked="checked"</c:if>
										value="useCustomizeUrl"> 启用<b>自定义页面URL</b>
									</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" id="authPageCustomizeUrl" name="authPageCustomizeUrl"
									class="form-control" placeholder="URL" value="${authPage.customizeUrl }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<div class="radio">
									<label> <input type="radio" name="authPageType" id="authPageTypeRadios3"
										<c:if test="${authPage.customizeHtml!=null}">checked="checked"</c:if>
										value="useCustomizeHtml"> 启用<b>自定义页面HTML</b> <input type="hidden"
										id="authPageCustomizeHtml" name="authPageCustomizeHtml" value="${authPage.customizeHtml }">
									</label>
								</div>
							</div>
							<div class="col-sm-1">
								<button type="button" id="inputHtmlAuthPage" class="btn btn-default ">编辑内容</button>
							</div>
						</div>
					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">认证后页面设置</h3>
					</div>
					<div class="panel-body">


						<div class="form-group">
							<div class="col-sm-3">
								<div class="radio">
									<label> <input type="radio" name="portalPageType" id="portalPageTypeRadios1"
										<c:if test="${portalPage.useOriginUrl!=null}">checked="checked"</c:if>
										value="useOriginUrl"> 启用<b>初始页面URL</b>
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<div class="radio">
									<label> <input type="radio" name="portalPageType" id="portalPageTypeRadios2"
										<c:if test="${portalPage.pageTemplate!=null}">checked="checked"</c:if> value="useTemplate">
										启用<b>页面模版</b>
									</label>
								</div>
							</div>
							<div class="col-sm-3">
								<select id="portalTemplate" name="portalTemplate" class="form-control">
									<c:forEach items="${portalPageTemplates }" var="portalPageTemplate" varStatus="i">
										<option value="portalTemplate${portalPageTemplate.id}"
											<c:if test="${portalPage.pageTemplate!=null && portalPageTemplate.id==portalPage.pageTemplate.id}">selected="selected"</c:if>>${portalPageTemplate.templateName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-1">
								<button type="button" id="portalPageTemplateBtn" class="btn btn-default" data-toggle="modal"
									data-target="#portalPageTemplates">效果</button>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<div class="radio">
									<label> <input type="radio" name="portalPageType" id="portalPageTypeRadios3"
										<c:if test="${portalPage.customizeUrl!=null}">checked="checked"</c:if>
										value="useCustomizeUrl"> 启用<b>自定义页面URL</b>
									</label>
								</div>
							</div>
							<div class="col-sm-9">
								<input type="text" id="portalPageCustomizeUrl" name="portalPageCustomizeUrl"
									class="form-control" placeholder="URL" value="${portalPage.customizeUrl }">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<div class="radio">
									<label> <input type="radio" name="portalPageType" id="portalPageTypeRadios4"
										<c:if test="${portalPage.customizeHtml!=null}">checked="checked"</c:if>
										value="useCustomizeHtml"> 启用<b>自定义页面HTML</b><input type="hidden"
										id="portalPageCustomizeHtml" name="portalPageCustomizeHtml"
										value="${portalPage.customizeHtml }">
									</label>
								</div>
							</div>
							<div class="col-sm-1">
								<button type="button" id="intputHtmlPortalPage" class="btn btn-default ">编辑内容</button>
							</div>
						</div>
					</div>
				</div>
				<input type="button" id="submitForm" class="btn btn-primary form-control" value="保存">
			</form>

			<!-- auth page selector Modal -->
			<div class="modal fade" id="authPageTemplates" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">请选择认证页面模版</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<c:forEach items="${authPageTemplates }" var="authPageTemplate" varStatus="i">

									<div class="col-xs-6 col-md-3">
										<a href="#" class="thumbnail" id="authTemplate${authPageTemplate.id}Img"> <img
											src="${ctx}/resources/img/template_thumbnail/${authPageTemplate.templateThumbnail}"
											alt="template_thumbnail">
										</a>
									</div>
								</c:forEach>

							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>

			<!-- portal page selector Modal -->
			<div class="modal fade" id="portalPageTemplates" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">请选择认证后页面模版</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<c:forEach items="${portalPageTemplates }" var="portalPageTemplate" varStatus="i">

									<div class="col-xs-6 col-md-3">
										<a href="#" class="thumbnail" id="portalTemplate${portalPageTemplate.id}Img"> <img
											src="${ctx}/resources/img/template_thumbnail/${portalPageTemplate.templateThumbnail}"
											alt="template_thumbnail">
										</a>
									</div>
								</c:forEach>


							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>

			<!-- custom HTML Modal -->
			<div class="modal fade" id="customizeHtmlModel" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">自定义HTML</h4>
						</div>
						<div class="modal-body">
							<textarea id="customizeHtml" class="form-control" rows="10"></textarea>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" id="saveCustomizeHtml" class="btn btn-primary">保存</button>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="customizeHtmlType">
		</div>
	</div>
</body>
</html>
