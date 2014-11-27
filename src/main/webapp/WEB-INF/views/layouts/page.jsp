<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
function jumpTo(maxPage){
    var page = $("#jumpTo").val();
    if(page > maxPage || page < 1){
        alert("对不起，无法到达该页")
    }else{
        $('body').load('${ctx}/user/list?page=' + page);
    }
}
</script>

<div id="pageControl">
	<!-- 上一页 按钮 -->
	<c:choose>
		<c:when test="${page != 1}">
			<a href="${ctx}/user/list?page=${page-1}"><input type="button"
				name="lastPage" value="上一页" /></a>
		</c:when>
		<c:otherwise>
			<input type="button" disabled="true" name="lastPage" value="上一页" />
			<!-- 为了要那个灰掉的button -->
		</c:otherwise>
	</c:choose>
	<!-- 页数列表 -->
	<c:forEach items="${pageList}" var="item">
		<c:choose>
			<c:when test="${item == page}">
				<a href="${ctx}/user/list?page=${item}" class="currentPage">${item}</a>
			</c:when>
			<c:otherwise>
				<a href="${ctx}/user/list?page=${item}">${item}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 下一页 按钮 -->
	<c:choose>
		<c:when test="${page != totalPages}">
			<a href="${ctx}/user/list?page=${page+1}"> <input type="button"
				name="nextPage" value="下一页" />
			</a>
		</c:when>
		<c:otherwise>
			<input type="button" disabled=true name="nextPage" value="下一页" />
			<!-- 为了要那个灰掉的button -->
		</c:otherwise>
	</c:choose>
	<!-- 直接跳转 -->
	共${totalPages}页 -向<input type="text" id="jumpTo" />页 <input
		type="button" value="跳转" onclick="jumpTo(${totalPages})" />
	<c:choose>
		<c:when test="${item == page}">
			<a href="${ctx}/user/list?page=${item}" class="currentPage">${item}</a>
		</c:when>
	</c:choose>
</div>

