<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function jumpTo(maxPage, currentPage) {
		var page = $("#jumpTo").val();
		if (isNaN(page)) {
			alert("请输入数字");
		} 
		if (page > maxPage || page < 1) {
			alert("对不起，无法到达该页")
		} else if(page == currentPage){
		}else {
			$('body').load('${pagePath}?page=' + page);
		}
	}
</script>
<div class="container-fluid">
	<div class="row">
		<div class="page-area">

			<label class="page-desc">共-${totalPages}-页</label>
			<ul class="pagination page-no-margin">
				<!-- 上一页 按钮 -->
				<c:choose>
					<c:when test="${page != 1}">
						<li><a href="${pagePath}?page=${page-1}">&laquo;</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#">&laquo;</a></li>
					</c:otherwise>
				</c:choose>
				<!-- 页数列表 -->
				<c:forEach items="${pageList}" var="item">
					<c:choose>
						<c:when test="${item == page}">
							<li class="disabled"><a href="#" class="currentPage">${item}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pagePath}?page=${item}">${item}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 下一页 按钮 -->
				<c:choose>
					<c:when test="${page != totalPages}">
						<li><a href="${pagePath}?page=${page+1}">&raquo;</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#">&raquo;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<c:choose>
				<c:when test="${item == page}">
					<a href="${pagePath}?page=${item}">${item}</a>
				</c:when>
			</c:choose>
			<input type="text" class="form-control page-editbox" id="jumpTo">

			<button class="btn btn-default page-button"
				onclick="jumpTo(${totalPages},${page})">跳转</button>


		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</div>


