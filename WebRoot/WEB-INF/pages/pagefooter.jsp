<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	var isChanged = false;
	
	function queryFormSubmit(pagenow){
		if(isChanged){
			$("#forpagenumidididi").val(1);
		}else{
			$("#forpagenumidididi").val(pagenow);
		}
		$("#queryForm").submit();
	}
	function bindEvent() {  
	    var temp;  
	    $("#queryForm").find("input:not(:hidden)").bind({  
	                focusin : function() {  
	                    temp = $.trim($(this).val());  
	                },  
	                focusout : function() {  
	                    var lastValue = $.trim($(this).val());  
	                        if (temp != lastValue) {  
	                        	isChanged = true;
	                        }  
	                    }
	            });  
	}  
	bindEvent();
</script>
<div>
		<input name="pageNow" value="<c:out value='${pages.pageNum }'/>" type="hidden"/>
		当前第<c:out value="${pages.pageNum }"/>页 ，共<c:out value="${pages.pages}"/>页
		每页显示
			<select name="pageSize" onchange="queryFormSubmit(1);">
				<option value="10" <c:if test="${pages.pageSize==10}">selected</c:if>>10</option>
				<option value="20" <c:if test="${pages.pageSize==20}">selected</c:if>>20</option>
				<option value="50" <c:if test="${pages.pageSize==50}">selected</c:if>>50</option>
				<option value="100" <c:if test="${pages.pageSize==100}">selected</c:if>>100</option>
			</select>
		条,
		共<c:out value="${pages.total }"/>条记录
		<c:set var="nowpageNumTmp" value="${pages.pageNum }"></c:set>
		<c:set var="allpagenuminpage" value="${pages.pages}"></c:set>
		<c:if test="${pages.pages>1}">
			<c:forEach var="pageIndexNow" items="${pages.navigatepageNums }">
				<c:if test="${pageIndexNow==nowpageNumTmp}">
					<c:out value="${pageIndexNow}"/>
				</c:if>
				<c:if test="${pageIndexNow!=nowpageNumTmp}">
					<a href="#" onClick="queryFormSubmit(<c:out value="${pageIndexNow}"/>);">
						<c:out value="${pageIndexNow}"/>
					</a>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${nowpageNumTmp!=1}">
			<a href="#" onClick="queryFormSubmit('1');">首页</a>
		</c:if>
		<c:if test="${nowpageNumTmp!=allpagenuminpage}">
				<a href="#" onClick='queryFormSubmit(<c:out value="${pages.pages}"/>);'>尾页</a>
		</c:if>
		<c:if test="${nowpageNumTmp!=1}">
				<a href="#" onClick="queryFormSubmit(<c:out value="${pages.prePage}"/>);">上一页</a>
		</c:if>
		<c:if test="${nowpageNumTmp!=allpagenuminpage}">
			<a href="#" onClick="queryFormSubmit(<c:out value="${pages.nextPage}"/>);">下一页</a>
		</c:if>
		<input type="hidden" name="pages" id="forpagenumidididi"/>
</div>