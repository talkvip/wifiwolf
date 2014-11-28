<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
function jumpTo(maxPage){
    var page = $("#jumpTo").val();
    if(page > maxPage || page < 1){
        alert("对不起，无法到达该页")
    }else{
        $('body').load('${ctx}/manage/list?page=' + page);
    }
}
</script>
<div>
	<label class="col-xs-2 col-sm-2 control-label">共-${totalPages}-页</label>
	<div class="col-xs-2">
		<ul class="pagination">
			<!-- 上一页 按钮 -->
			<c:choose>
				<c:when test="${page != 1}">
					<li><a href="${ctx}/manage/list?page=${page-1}">&laquo;</a></li>
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
						<li><a href="${ctx}/manage/list?page=${item}">${item}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 下一页 按钮 -->
			<c:choose>
				<c:when test="${page != totalPages}">
					<li><a href="${ctx}/manage/list?page=${page+1}">&raquo;</a></li>
				</c:when>
				<c:otherwise>
					<li class="disabled"><a href="#">&raquo;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- 直接跳转 -->
	<c:choose>
		<c:when test="${item == page}">
			<a href="${ctx}/manage/list?page=${item}" class="currentPage">${item}</a>
		</c:when>
	</c:choose>
	<div class="col-xs-2">
		<div class="input-group">
			<input type="text" class="form-control" id="jumpTo"> <span
				class="input-group-btn">
				<button class="btn btn-default" type="button"
					onclick="jumpTo(${totalPages})">跳转</button>
			</span>
		</div>
	</div>
</div>
