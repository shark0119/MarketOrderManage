<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
共${pager.totalCount }条记录&nbsp;&nbsp; ${pager.pageIndex }/
<span id="totalPage">${pager.totalPage }</span>页
<c:choose>
	<c:when test="${pager.pageIndex == 1 }">首页&nbsp;	上一页</c:when>
	<c:otherwise>
		<a href="javascript:jump(1, ${pager.pageSize })">首页</a>&nbsp;	
				<a href="javascript:jump(${pager.pageIndex }-1, ${pager.pageSize })">上一页</a>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${pager.pageIndex == pager.totalPage }">下一页&nbsp;	末页</c:when>
	<c:otherwise>
		<a href="javascript:jump(${pager.pageIndex }-0+1, ${pager.pageSize })">下一页</a>&nbsp;	
				<a href="javascript:jump(${pager.totalPage }, ${pager.pageSize })">末页</a>
	</c:otherwise>
</c:choose>
页数:
<input type="text" id="page" name="page" size="1" />
<input type="button" id="GO" value="GO" />
<input type="hidden" value="${pager.pageIndex }" name="pageIndex" />
<input type="hidden" value="${pager.pageSize }" name="pageSize" />

<script src="/SuperMarket/jsp/js/jquery.js"></script>
<script src="/SuperMarket/jsp/js/userList.js"></script>