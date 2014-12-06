<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>Wifi Wolf 网络登录</title>
<link href="${ctx}/resources/css/signin.css" type="text/css" rel="stylesheet" />

<script>
	$(document).ready(function(){
		$("#getSmsCode").click(function(){
			var phoneNum = $.trim($("#phoneNum").val());
			var patten = new RegExp(/^1\d{10}$/);
			if(patten.test(phoneNum)==false){
				alert("请输入 正确的手机号");
				return;
			}
			$.get("${ctx}/wifi/phoneVerify/?phoneNum=" + phoneNum,function(data,status){
				//TODO
			});
			$("#getSmsCode").attr("disabled",true);
			var step = 59;
            $('#getSmsCode').text('59秒后重试');
            var _res = setInterval(function()
            {   
            	step-=1;
                $("#getSmsCode").attr("disabled", true);//设置disabled属性
                $('#getSmsCode').text(step+"秒后重试");
                
                if(step <= 0){
	                $("#getSmsCode").removeAttr("disabled"); //移除disabled属性
	                $('#getSmsCode').text('获取验证码');
	                clearInterval(_res);//清除setInterval
                }
            },1000);
		});
		$("#agreeLicense").change(function(){
			if($(this).prop("checked")==true){
				$("#submitForm").attr("disabled",false);
			}else{
				$("#submitForm").attr("disabled",true);
			}
		});
		
		$("#submitForm").click(function(){
			$("#loginForm").submit();
		});
		$("#loginForm").validate();
	});
</script>
</head>

<body>
	<div class="container">
		<form class="form-signin" id="loginForm" action="${ctx}/wifi/login/" method="post">
			<h2 class="form-signin-heading">Wifi Wolf 网络登录</h2>

			<c:if test="${authType eq 'PHONE_PASSWORD'}">
				<div class="control-group">
					<label for="username" class="control-label">用户名/手机号:</label> <input class="form-control"
						type="text" id="username" name="username" value="${username}" required autofocus /> <br>
				</div>
			</c:if>
			<c:if test="${authType eq 'PHONE'}">
				<div class="control-group">
					<label for="password" class="control-label">手机号:</label> <input class="form-control"
						type="text" id="phoneNum" name="phoneNum" required autofocus /> <br>
				</div>
			</c:if>
			<c:if test="${authType eq 'PHONE_SMS' || authType eq 'PHONE_PASSWORD_SMS'}">
				<div class="control-group">
					<label for="password" class="control-label">手机号:</label> <input class="form-control"
						type="text" id="phoneNum" name="phoneNum" required autofocus /> <br>
						<button id="getSmsCode" class="btn btn-default" type="button" value="" >获取验证码</button>
					<br><br>
				</div>
				<div class="control-group">
					<label for="password" class="control-label">手机验证码:</label> <input class="form-control"
						type="text" name="phoneCode" id="phoneCode" required autofocus /> <br>
				</div>
			</c:if>

			<c:if test="${authType eq 'PHONE_PASSWORD' || authType eq 'PHONE_PASSWORD_SMS'}">
				<div class="control-group">
					<label for="password" class="control-label">密码:</label> <input class="form-control"
						type="password" name="userPassword" id="userPassword" required autofocus />
				</div>
			</c:if>

			<div class="checkbox">
				<label for="agreeLicense"> <input type="checkbox" id="agreeLicense" name="agreeLicense" checked="checked">
					同意<a href="#" data-toggle="modal" data-target="#myModal">用户协议</a>
				</label>
			</div>
			<input type="hidden" name="wifidogHost" value="${wifidogHost}" /> <input type="hidden"
				name="wifidogPort" value="${wifidogPort}" /> <input type="hidden" name="gw_id" value="${gw_id}" />
			<button class="btn btn-lg btn-primary btn-block" id="submitForm" type="button" >登录</button>
			<br><a href="#" target="_blank">注册</a>&nbsp;&nbsp;&nbsp;<a href="#" target="_blank">忘记密码</a>
		</form>

	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">用户协议</h4>
				</div>
				<div class="modal-body">点击下面的按钮即可通过 JavaScript 启动一个模态框。此模态框将从上到下、逐渐浮现到页面前。</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
